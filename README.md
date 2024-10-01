# Envío de Mails

Este proyecto es una aplicación de envío de correos electrónicos desarrollada en **Spring Boot**, que permite enviar mensajes con o sin archivos adjuntos utilizando `JavaMailSender`. Está pensado para ser utilizado en cualquier proyecto que necesite enviar correos electrónicos de manera sencilla.

## Tecnologías utilizadas
- **Java 21**
- **Spring Boot 3.3.4**
- **Maven**
- **JavaMailSender** (para el envío de correos)
- **Lombok** (para reducir el código repetitivo)

## Configuración del proyecto

### Prerrequisitos
- **Java 21**
- **Maven** para gestionar las dependencias y construir el proyecto.

### Configuración del archivo .env

El proyecto requiere un archivo `.env` en el directorio raíz con las siguientes variables de entorno:

MAIL_SENDER=  
PASSWORD=

Estas variables deben contener los detalles de autenticación de tu cuenta de correo electrónico para poder enviar emails. No olvides mantener estos datos seguros.

### Ejecución del proyecto

1. Clona el repositorio:  
   git clone https://github.com/marcospoet/EnvioMails.git

2. Ingresa al directorio del proyecto:  
   cd EnvioMails

3. Compila y ejecuta el proyecto:  
   mvn spring-boot:run

4. El proyecto estará disponible en `http://localhost:8080`.

## Uso de la API

### Enviar correo sin adjunto  
`POST /sendEmail`

**Request Body:**  
{  
    "toUser":[  
        "usuario@gmail.com"  
    ],  
    "subject":"Prueba de Mail Sender Usando Spring y Postman",  
    "message":"Esta es la prueba del primer metodo el cual solo recibe Texto"  
}

## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún problema o tienes sugerencias de mejora, crea un *issue* o un *pull request*.
