package com.uni.thanosgym.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class Utils {

    public static boolean areAllTrue(boolean[] array) {
        for (boolean b : array)
            if (!b)
                return false;
        return true;
    }

    public static boolean sendMail(String messageEmail, String para, String titulo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        String username = "jcmrojas29@gmail.com";
        String password = EnvVariables.getInstance().get("EMAIL_PASSWORD");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jcmrojas29@gmail.com"));;
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
            message.setSubject(titulo);
            message.setText(messageEmail);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void generateInvoice() {
    }
}
