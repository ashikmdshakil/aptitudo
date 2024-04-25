package com.cnsbd.aptitudo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String smtpHost;
    @Value("${spring.mail.port}")
    private int smtpPort;
    @Value("${spring.mail.username}")
    private String smtpUser;
    @Value("${spring.mail.password}")
    private String smtpPass;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean hasAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean hasStartTLS;

    @Value("${spring.mail.properties.mail.debug}")
    private boolean mailDebug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpHost);
        mailSender.setPort(smtpPort);
        mailSender.setUsername(smtpUser);
        mailSender.setPassword(smtpPass);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", String.valueOf(hasAuth));
        props.put("mail.smtp.starttls.enable", String.valueOf(hasStartTLS));
        props.put("mail.debug", String.valueOf(mailDebug));
        return mailSender;
    }

}
