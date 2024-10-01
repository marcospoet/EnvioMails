package com.mail.sender.EnvioMails.domain;
//spring trabaja los archivos con MultipartFile
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailFileDto{
    private String[] toUser;
    private String subject;
    private String message;
    private MultipartFile file;
}
