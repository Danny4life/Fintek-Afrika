package com.osiki.finteckafrika.service;

import com.osiki.finteckafrika.model.MailServiceModel;
import org.springframework.mail.MailException;

public interface MailService {

    public void sendNotification(MailServiceModel mailServiceModel) throws MailException;
}
