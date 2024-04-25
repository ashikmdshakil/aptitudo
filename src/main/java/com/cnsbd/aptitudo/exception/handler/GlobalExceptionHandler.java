package com.cnsbd.aptitudo.exception.handler;

import com.cnsbd.aptitudo.exception.*;
import com.cnsbd.aptitudo.exception.data.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    // handling specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(exception.getMessage(), request, null);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handling specific exception
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundExceptionHandler(NoDataFoundException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handling specific exception
    @ExceptionHandler(ArgumentNotValidException.class)
    public ResponseEntity<?> argumentNotValidHandling(ArgumentNotValidException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), exception.getStatusCode(), exception.getErrorDetails());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling field exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ex.printStackTrace();
        ObjectError objectError = ex.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        String message = objectError != null ? objectError.getDefaultMessage() : "Invalid request";
        ErrorDetails errorDetails = new ErrorDetails(message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling global exception
    @ExceptionHandler(CommonServerException.class)
    public ResponseEntity<?> serverExceptionHandling(CommonServerException exception, WebRequest request) {
        exception.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(exception.getMessage(), request, exception.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // handling global exception
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<?> usernameNotFoundExceptionHandling(UsernameNotFoundException exception) {
//        exception.printStackTrace();
//        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage());
//        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<?> badCredentialsExceptionHandling(BadCredentialsException exception) {
//        exception.printStackTrace();
//        ErrorDetails errorDetails = new ErrorDetails("INVALID USERNAME OR PASSWORD");
//        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(com.cnsbd.cpapmis.exception.UnauthorizedUserException.class)
    public ResponseEntity<?> unauthorizedUserExceptionHandling(com.cnsbd.cpapmis.exception.UnauthorizedUserException exception) {
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), exception.getStatusCode());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseEntity<?> customUnauthorizedExceptionHandling(CustomUnauthorizedException exception) {
        exception.printStackTrace();
        ErrorDetails errorDetails = exception.getStatusCode() != null ? new ErrorDetails(exception.getMessage(), exception.getStatusCode())
                : new ErrorDetails(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> dateTimeParseExceptionHandling(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(ex.getMessage(), request, ex.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // handling NumberFormatException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> numberFormatExceptionHandler(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ex.printStackTrace();
        String details = "[Field: " + ex.getName() + "], [Error: " + ex.getMessage() + "]";
        ErrorDetails errorDetails = getErrorDetails("INVALID REQUEST!", request, details);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling file upload exception
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<?> fileUploadExceptionHandler(FileUploadException ex, WebRequest request) {
        ex.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails(ex.getMessage(), request, ex.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling bind exception
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindExceptionHandling(BindException ex, WebRequest request) {
        ex.printStackTrace();
        String defaultMessage = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();
        defaultMessage = (!StringUtils.hasText(defaultMessage)) ? "REQUIRED FIELD CAN'T BE EMPTY!" : defaultMessage;
        ErrorDetails errorDetails = getErrorDetails(defaultMessage, request, ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception ex, WebRequest request) {
        ex.printStackTrace();
        ErrorDetails errorDetails = getErrorDetails("SOMETHING WENT WRONG! PLEASE TRY AGAIN LATER.", request, ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ErrorDetails getErrorDetails(String description, WebRequest request, String details) {
        String additionalDesc = details + " [" + request.getDescription(false) + "]";
        return new ErrorDetails(description, additionalDesc);
    }


}
