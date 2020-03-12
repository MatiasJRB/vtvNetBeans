/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.util.Properties;
import com.sun.mail.handlers.*;
import com.sun.mail.auth.*;
import com.sun.mail.iap.*;
import com.sun.mail.imap.*;
import com.sun.mail.pop3.*;
import com.sun.mail.smtp.*;
import com.sun.mail.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mjrca
 */
public class gmail {

    private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "matiasjriosb";  //Para la dirección nomcuenta@gmail.com

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "miClaveDeGMail");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "Matiasjrb95!");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Mail enviado");
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
        public static void main(String[] args) {
            String destinatario =  "matiasjriosb@gmail.com"; //A quien le quieres escribir.
            String asunto = "Correo de prueba enviado desde Java";
            String cuerpo = "Esta es una prueba de correo...";

            enviarConGMail(destinatario, asunto, cuerpo);
        }
}

