package com.uni.thanosgym.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.uni.thanosgym.model.Contrato;
import java.io.FileOutputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static final String RUC = "3423432";
    private static final String username = "jcmrojas29@gmail.com";
    // private static final String password = "jlvj fjdw sjfv wjwo ";
    private static final String password = "anstoehunt";

    public static boolean areAllTrue(boolean[] array) {
        for (boolean b : array)
            if (!b)
                return false;
        return true;
    }

    public static boolean sendSimpleMail(String messageEmail, String para, String titulo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

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

    public static boolean sendMailWithPdf(String para, String titulo, String messageEmail, String pdfPath) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

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

    public static void generatePaymentPDF(Contrato payment, String pdfPath) {
        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(
                    String.format("Detalle de contrato a nombre de %s", payment.getCliente().getFullName())));
            Table table = new Table(4);

            table.addHeaderCell("Nro boleta");
            table.addHeaderCell("Fecha");
            table.addHeaderCell("Plan");
            table.addHeaderCell("Monto");

            table.addCell(StringUtils.parseIdBoleta(payment.getId()));
            table.addCell(StringUtils.parseSpanishDate(payment.getCreatedAt()));
            table.addCell(payment.getPlan().getName());
            table.addCell(String.valueOf(payment.getPlan().getPrice()));

            document.add(table);
            document.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void generarContratoPdf(Contrato contrato, String pdfPath) {
        // Define el HTML a partir del cual generar el PDF
        String htmlContent = String.format(
                "<html>" +
                        "<head><style>" +
                        "table { width: 100%%; border-collapse: collapse; }" +
                        "th, td { border: 1px solid black; padding: 8px; text-align: left; }" +
                        "th { background-color: #f2f2f2; }" +
                        "</style></head>" +
                        "<body>" +
                        "<h1>Detalle de contrato a nombre de %s</h1>" +
                        "<table>" +
                        "<thead>" +
                        "<tr><th>Nro de contrato</th><th>Fecha</th><th>Plan</th><th>Monto</th></tr>" +
                        "</thead>" +
                        "<tbody>" +
                        "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>" +
                        "</tbody>" +
                        "</table>" +
                        "</body>" +
                        "</html>",
                contrato.getCliente().getFullName(),
                StringUtils.parseIdBoleta(contrato.getId()),
                StringUtils.parseSpanishDate(contrato.getCreatedAt()),
                contrato.getPlan().getName(),
                contrato.getPlan().getPrice());

        try {
            // Crea un PdfWriter
            PdfWriter writer = new PdfWriter(new FileOutputStream(pdfPath));
            // Convierte HTML a PDF
            HtmlConverter.convertToPdf(htmlContent, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Color hexToRGB(String hex) {
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }

        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);

        return new Color(r, g, b);
    }

    public static void mostrarPantallaDeCarga(JFrame frame, Runnable function) {
        JDialog loadingDialog = new JDialog(frame, "Cargando", true);
        JLabel loadingLabel = new JLabel("Cargando, por favor espere...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingDialog.getContentPane().add(loadingLabel, BorderLayout.CENTER);
        loadingDialog.setSize(200, 100);
        loadingDialog.setLocationRelativeTo(frame);

        new Thread(() -> {
            SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));

            function.run();

            SwingUtilities.invokeLater(loadingDialog::dispose);
        }).start();
    }
}
