
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.controlador;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

/**
 *
 * @author Liseth
 */
@ManagedBean
@Named
public class MailClient {

    @Resource(name = "Mail-Tedx")
    private Session mailSession;

    public synchronized boolean sendMail(String to, String subject, String body) throws Exception {
        boolean envio = false;
        InitialContext ctx = new InitialContext();
        mailSession = (Session) ctx.lookup("Mail-Tedx");

        try {

            //Preparamos el Mensaje
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from"), "TEDx ESPOCH Riobamba"));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("reivaj_oremor@hotmail.com, wilson@escobarycastro.com"));
            //message.setSender(new InternetAddress(mailSession.getProperty("mail.email")));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");
            message.setHeader("TEDX-Mailer", "TEDx");

            if (to.indexOf(',') > 0) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            } else {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            //envío del mensaje
            Transport.send(message);
            envio = true;

        } catch (Exception e) {
            envio = false;
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            return envio;
        }
    }

}
