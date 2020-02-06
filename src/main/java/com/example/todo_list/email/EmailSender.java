package com.example.todo_list.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;

@Service
public class EmailSender {

    @Autowired private JavaMailSender emailSender;

    public void sendEmailWithAttachment(EmailRequest emailRequest) throws MessagingException, FileNotFoundException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(emailRequest.getToAddress());
        messageHelper.setSubject(emailRequest.getSubject());
        messageHelper.setText(emailRequest.getMessage());
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(emailRequest.getAttachment()));
        messageHelper.addAttachment("Report", file);
        emailSender.send(mimeMessage);
    }

}
