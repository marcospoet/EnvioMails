package com.mail.sender.EnvioMails.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.sender}")
    private String emailUser;

    @Override
    public void sendEmail(String[] toUser, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(); //Este es el objeto que nos ayuda a construir el mensaje
        mailMessage.setFrom(emailUser); //Quien envia el correo
        mailMessage.setTo(toUser); //A quien va dirigido, puede ser un arreglo de correos o uno solo
        mailMessage.setSubject(subject); //Asunto del correo
        mailMessage.setText(message); //Cuerpo del correo

        mailSender.send(mailMessage); //Enviamos el correo

    }

    @Override
    public void sendEmailWithFile(String[] toUser, String subject, String message, File file) {
        // Enviar correo con archivo adjunto
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name()); //si adjuntas archivo pones true o sea multipart

            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.addAttachment(file.getName(), file); //Agregamos el archivo adjunto

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
