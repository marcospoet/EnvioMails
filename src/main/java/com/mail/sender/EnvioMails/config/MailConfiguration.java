package com.mail.sender.EnvioMails.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Value("${mail.sender}")
    private String emailUser;
    @Value("${spring.mail.password}")
    private String password;

    @Bean // Bean para configurar el envio de correos
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); //Este es el host para gmail, si usas otro proveedor de correo, deberás buscar el host correspondiente
        mailSender.setPort(587); //Este es el puerto estandar para este protocolo, el smtp
        mailSender.setUsername(emailUser);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties(); // Obtenemos las propiedades del mailSender
        props.put("mail.transport.protocol", "smtp"); // Protocolo de envio
        props.put("mail.smtp.auth", "true"); // Habilitamos la autenticación
        props.put("mail.smtp.starttls.enable", "true"); // Habilitamos el TLS, estamos habilitando el cifrado
        props.put("mail.debug", "true"); // Habilitamos la depuración, en la consola veremos info de la conexión

        return mailSender;
    }
}
