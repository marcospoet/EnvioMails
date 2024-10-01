package com.mail.sender.EnvioMails.domain;
//Con los record de java 14 podemos crear clases que solo tengan atributos y no metodos, me crea solo los getter y setter pq la idea es que son obj inmutables
public record EmailDto (String[] toUser,
                       String subject,
                       String message){
}
