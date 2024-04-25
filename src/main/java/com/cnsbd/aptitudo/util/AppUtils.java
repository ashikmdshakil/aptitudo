package com.cnsbd.aptitudo.util;

import com.cnsbd.aptitudo.exception.ArgumentNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.hasText;

public class AppUtils {

    private static boolean regexPatternMatch(String regex, String input) {
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the input is empty
        // return false
        if (input == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given input
        // and regular expression.
        Matcher m = p.matcher(input);

        // Return if the input
        // matched the ReGex
        return m.matches();
    }

    /**
     * BD mobile number pattern: 01[1,3,4,5,6,7,8,9]XX-XXXXXX
     *
     * @param mobileNo
     * @return
     */
    public static boolean isValidMobileNumber(String mobileNo) {
        // Regex to check valid mobile number.
        String regex = "^(?:\\+88|88)?(01[3-9]\\d{8})$";
        return regexPatternMatch(regex, mobileNo);
    }

    /**
     * At least one upper case English letter, (?=.*?[A-Z])
     * At least one lower case English letter, (?=.*?[a-z])
     * At least one digit, (?=.*?[0-9])
     * At least one special character, (?=.*?[#?!@$%^&*-])
     * Minimum eight in length .{8,} (with the anchors)
     *
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        // special character from :: https://owasp.org/www-community/password-special-characters
        // " !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~"

        // regex with all constraint
        // String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        // with owasp special character
        // String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,}$";

        String uppercaseRegex = "^(?=.*?[A-Z]).{1,}$";
        String lowercaseRegex = "^(?=.*?[a-z]).+$";
        // String numberRegex = "^(?=.*?[0-9]).+$";
        String numberRegex = "^(?=.*?\\d).+$";
        String specialCharacterRegex = "^(?=.*?[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{1,}$";
        String lengthCountRegex = "^(?=.*?).{8,}$";

        if (!regexPatternMatch(uppercaseRegex, password)) {
            throw new ArgumentNotValidException("PASSWORD MUST CONTAIN AT LEAST ONE UPPER CASE CHARACTER!", "password");
        }

        if (!regexPatternMatch(lowercaseRegex, password)) {
            throw new ArgumentNotValidException("PASSWORD MUST CONTAIN AT LEAST ONE LOWER CASE CHARACTER!", "password");
        }

        if (!regexPatternMatch(numberRegex, password)) {
            throw new ArgumentNotValidException("PASSWORD MUST CONTAIN AT LEAST ONE NUMERIC VALUE!", "password");
        }

        if (!regexPatternMatch(specialCharacterRegex, password)) {
            throw new ArgumentNotValidException("PASSWORD MUST CONTAIN AT LEAST ONE SPECIAL CHARACTER!", "password");
        }

        if (!regexPatternMatch(lengthCountRegex, password)) {
            throw new ArgumentNotValidException("PASSWORD MUST BE AT LEAST 8 CHARACTERS!", "password");
        }

        return true;
    }

    public static String nullCheck(String input1, String input2) {
        if (!hasText(input1)) {
            return input2;
        }
        return input1;
    }

    public static Long nullCheck(Long input1, Long input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

    public static Integer nullCheck(Integer input1, Integer input2) {
        if (input1 == null) {
            return input2;
        }
        return input1;
    }

}
