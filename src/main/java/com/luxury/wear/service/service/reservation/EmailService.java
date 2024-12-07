package com.luxury.wear.service.service.reservation;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class EmailService {

/*    private static final String email = "Luxurywear2024@gmail.com";
    private static final String code = "iafo alwe xned utip";*/
    private static final String email = "carol280696@gmail.com";
    private static final String code = "hzzt whkn kmir elny";

    public void sendEmail(String to, String subject, String body) {
        String from = email; // Correo de origen
        String host = "smtp.gmail.com"; // Servidor SMTP de Gmail

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Autenticación con el servidor SMTP
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, code); // Credenciales de Gmail
            }
        });

        try {
            // Crear el mensaje de correo
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Establecer el destinatario
            message.setSubject(subject); // Establecer el asunto
            message.setContent(body, "text/html"); // Establecer el contenido del mensaje

            // Enviar el mensaje
            Transport.send(message);
            System.out.println("Email sent successfully...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            throw new RuntimeException("Failed to send email", mex);
        }

    }

    public String loadEmailTemplate(String templateName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(templateName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load email template", e);
        }
        return contentBuilder.toString();
    }
}