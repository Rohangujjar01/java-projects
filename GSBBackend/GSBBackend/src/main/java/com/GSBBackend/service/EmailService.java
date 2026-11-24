package com.GSBBackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendEmailWithTemplate(String to, String subject, String templateName, Context context) throws MessagingException {
        // Process the template with context
        String emailContent = templateEngine.process(templateName, context);

        // Prepare the email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(emailContent, true); // Enable HTML
        helper.setFrom("yashavanthmr4@gmail.com"); // explicitly
        mailSender.send(message);
    }
}

