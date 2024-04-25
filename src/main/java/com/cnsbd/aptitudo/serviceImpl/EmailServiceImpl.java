package com.cnsbd.aptitudo.serviceImpl;

import com.cnsbd.aptitudo.data.request.EmailRequestData;
import com.cnsbd.aptitudo.data.response.CommonResponseData;
import com.cnsbd.aptitudo.exception.ArgumentNotValidException;
import com.cnsbd.aptitudo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public CommonResponseData sendSubscriptionEmail(EmailRequestData emailRequestData) {
        try {
            SimpleMailMessage subscriberMessage = new SimpleMailMessage();
            subscriberMessage.setTo(emailRequestData.getSubscriberEmailAddress());
            subscriberMessage.setSubject("Welcome Message");
            subscriberMessage.setText("Thanks for subscribing with us !");
            emailSender.send(subscriberMessage);

            SimpleMailMessage authorityMessage = new SimpleMailMessage();
            authorityMessage.setTo("contactus@aptitudo.ca");
            authorityMessage.setSubject("New Subscription !!");
            authorityMessage.setText("An user with email ("+emailRequestData.getSubscriberEmailAddress()+") has been subscribed.");
            emailSender.send(authorityMessage);

            return new CommonResponseData("Email has been sent successfully.");

        } catch (MailException e) {
            throw new ArgumentNotValidException("Error occured while sending email.","Something went worng while sending subscribers email info.");
        }
    }

}
