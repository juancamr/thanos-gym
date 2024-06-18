package com.uni.thanosgym.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.uni.thanosgym.model.Payment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
            message.setFrom(new InternetAddress("jcmrojas29@gmail.com"));

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

    public static boolean sendMailWithPdf(String messageEmail, String para, String titulo, String pdfPath) {
        try {
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
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jcmrojas29@gmail.com"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
            message.setSubject(titulo);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(messageEmail);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File(pdfPath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            // Establecer el contenido del mensaje
            message.setContent(multipart);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            System.out.println(e);
            return false;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void generatePaymentPDF(Payment payment, String pdfPath) {
        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(String.format("Detalle de boleta para %s", payment.getCliente().getFullName())));
            Table table = new Table(4);

            table.addHeaderCell("Nro boleta");
            table.addHeaderCell("Fecha");
            table.addHeaderCell("Plan");
            table.addHeaderCell("Monto");

            table.addCell(StringUtils.parseIdBoleta(payment.getId()));
            table.addCell(StringUtils.parseSpanishDate(payment.getCreatedAt()));
            table.addCell(payment.getPlan().getName());
            table.addCell(String.format("S/ %f", payment.getPlan().getPrice()));

            document.add(table);
            document.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
