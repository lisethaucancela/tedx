/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

/**
 *
 * @author ULVIER
 */
//import recursos.Globales;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class MailClient {

    public void sendMail(String from, String to,
            String subject, String messageBody,
            String[] attachments) throws
            MessagingException, AddressException {
        java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.mail");
        String mailServer = Configuracion.getString("mailServer");
        // Setup mail server
        Properties props = System.getProperties();
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.password", "htesil1234");
        

        // Get a mail session
        Session session = Session.getDefaultInstance(props, null);

        // Define a new mail message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);

        // Create a message part to represent the body text
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(messageBody);

        //use a MimeMultipart as we need to handle the file attachments
        Multipart multipart = new MimeMultipart();

        //add the message body to the mime message
        multipart.addBodyPart(messageBodyPart);

        // add any file attachments to the message
        addAtachments(attachments, multipart);

        // Put all message parts in the message
        message.setContent(multipart);

        // Send the message
        Transport.send(message);
    }

    protected void addAtachments(String[] attachments, Multipart multipart)
            throws MessagingException, AddressException {
        for (int i = 0; i <= attachments.length - 1; i++) {
            String filename = attachments[i];
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            //use a JAF FileDataSource as it does MIME type detection
            DataSource source = new FileDataSource(filename);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            //assume that the filename you want to send is the same as the
            //actual file name - could alter this to remove the file path
            attachmentBodyPart.setFileName(filename);

            //add the attachment
            multipart.addBodyPart(attachmentBodyPart);
        }
    }

    public static void enviaMailAdministrador(String mailUsuario, String mensaje) {
        java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.mail");
        MailClient client = new MailClient();
        String to = "liseth_dy@hotmail.com";
        String from = Configuracion.getString("masterMail");
        String[] filenames = {};
        try {
            client.sendMail(from, to, "Aviso de error eFinanciero mail: " + mailUsuario, mensaje, filenames);
        } catch (Exception ex) {
        }
    }

//    public static void main(String[] args) {
//        //------ Para enviar un Mail
//        try {
//            MailClient client = new MailClient();
//            String from = "dpalacios@espoch.edu.ec";
//            String to = "annibal85@hotmail.com";
//            String subject = "Pruebas desde Java";
//            String message = "Pruebas de mail ";
//            String[] filenames = {};
//            client.sendMail(from, to, subject, message, filenames);
//            System.out.print("envio");
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//            e.printStackTrace(System.out);
//        }
//
//    }
}
