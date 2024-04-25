package com.cnsbd.aptitudo.controller;

import com.cnsbd.aptitudo.constant.WebApiUrlConstants;
import com.cnsbd.aptitudo.data.request.EmailRequestData;
import com.cnsbd.aptitudo.exception.ArgumentNotValidException;
import com.cnsbd.aptitudo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private EmailService emailService;

    @PostMapping(value = WebApiUrlConstants.SUBSCRIBTION_MAIL, consumes = "application/json")
    public ResponseEntity<?> emailSubcribersInfo(@RequestBody EmailRequestData emailRequestData){

        // email address validation whether it is empty or not
        if(emailRequestData.getSubscriberEmailAddress().isEmpty() || emailRequestData.getSubscriberEmailAddress() == null){
            throw new ArgumentNotValidException("Subscriber's email address is required!","Email Address can not be empty.");
        }

        return new ResponseEntity<>(emailService.sendSubscriptionEmail(emailRequestData), HttpStatus.OK);
    }
}
