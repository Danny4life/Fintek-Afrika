package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.model.MailServiceModel;
import com.osiki.finteckafrika.service.MailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class MailServiceImpl implements MailService {




    private final JavaMailSender javaMailSender;



    @Override
    public void sendNotification(MailServiceModel mailServiceModel) throws MailException {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailServiceModel.getEmailAddress());
        mailMessage.setSubject(mailMessage.getSubject());
        mailMessage.setText("Hi " + mailServiceModel.getName() + "!\n" + mailServiceModel.getMessage());
        javaMailSender.send(mailMessage);

    }
}
