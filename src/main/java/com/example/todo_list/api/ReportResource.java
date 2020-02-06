package com.example.todo_list.api;

import com.example.todo_list.email.EmailRequest;
import com.example.todo_list.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/email")
public class ReportResource {

    @Autowired private EmailSender emailSender;

    @PostMapping("/send")
    public void send(@RequestBody EmailRequest emailRequest) throws FileNotFoundException, MessagingException {
        emailSender.sendEmailWithAttachment(emailRequest);
    }
}
