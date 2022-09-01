package com.ams.likepost.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;


    public static int noOfQuickServiceThreads = 20;

    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

    public Boolean sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("game1storage@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        quickService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    mailSender.send(message);
                }catch(Exception e){
                   e.printStackTrace();
                }
            }
        });
       return true;
    }

}
