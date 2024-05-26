package util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class correoUtil {
    public static void sendEmail(String[] toAddress, String subject, MimeBodyPart... messageBodyParts)
            throws AddressException, MessagingException, IOException {

        final String username = "correo";
        final String password = "codigo";

        Message msg = new MimeMessage(getCreateSession(username,password));

        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddresses = new InternetAddress[toAddress.length];
        for (int i = 0; i < toAddresses.length; i++) {
            toAddresses[i] = new InternetAddress(toAddress[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        Multipart multipart = new MimeMultipart();
        for (MimeBodyPart messageBodyPart : messageBodyParts) {
            multipart.addBodyPart(messageBodyPart);
        }

        msg.setContent(multipart);

        Transport.send(msg, username, password);
    }

    private static Session getCreateSession(String mailFrom, String mailPassword) {
        Session session = Session.getInstance(getGmailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, mailPassword);
            }
        });
        return session;
    }

    private static Properties getGmailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return prop;
    }

    public void enviarCorreo(String correo,String nombre, String codigoRecuperacion) throws MessagingException, IOException {
        String[] listaCorreos = {correo};
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        StringBuilder sb = new StringBuilder();
        sb.append("<p>");
        sb.append("Estimado/a, ");
        sb.append("<b>");
        sb.append(nombre);
        sb.append("</b>");
        sb.append("<br><br>");
        sb.append("Este es tu para recuperar tu cuenta ");
        sb.append(codigoRecuperacion);

        messageBodyPart.setContent(sb.toString(), "text/html");
        sendEmail(listaCorreos,"Codigo de recuperacion",messageBodyPart);
    }
}
