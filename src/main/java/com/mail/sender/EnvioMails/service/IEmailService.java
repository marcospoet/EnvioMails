package com.mail.sender.EnvioMails.service;

import java.io.File;

public interface IEmailService {
    void sendEmail(String[] toUser,String subject,String message); // subject: Asunto del correo, message: Cuerpo del correo
    void sendEmailWithFile(String[] toUser, String subject, String message, File file);
}
