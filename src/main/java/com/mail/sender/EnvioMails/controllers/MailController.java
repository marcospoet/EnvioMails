package com.mail.sender.EnvioMails.controllers;

import com.mail.sender.EnvioMails.domain.EmailDto;
import com.mail.sender.EnvioMails.domain.EmailFileDto;
import com.mail.sender.EnvioMails.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDto emailDto) { //Aca recimos una peticion http desde nuestro front end
        System.out.println("Mensaje recibido: " + emailDto);

        emailService.sendEmail(emailDto.toUser(),emailDto.subject(),emailDto.message()); //Enviamos el email

        Map<String,String> response = new HashMap<>();
        response.put("estado","Mensaje enviado correctamente");

        return ResponseEntity.ok(response);
    }
    //uso de @ModelAttribute para recibir un archivo porque @RequestBody (o sea json) no es compatible con archivos
    //Basicamente me permite pasar los datos como un formData
    @PostMapping(value = "/sendMessageFile", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileDto emailFileDto){

        try{
            System.out.println("Archivo recibido: " + emailFileDto.getFile());
            String fileName = emailFileDto.getFile().getOriginalFilename();
            Path path = Paths.get("src/main/resources/files/"+ fileName); //aca es donde vamos a poner nuestro archivo
            Files.createDirectories(path.getParent());
            //el path indica a donde se copia y el replace existing significa si ya existe lo reemplaza
            Files.copy(emailFileDto.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.sendEmailWithFile(emailFileDto.getToUser(),emailFileDto.getSubject(),emailFileDto.getMessage(),file);
            Map<String,String> response = new HashMap<>();
            response.put("estado","Mensaje enviado correctamente");
            response.put("archivo",fileName);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            throw new RuntimeException("Error al enviar email con el archivo"+ e.getMessage());
        }

    }
}
