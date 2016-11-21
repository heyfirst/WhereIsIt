/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author KS
 */
public class Email {
  
  public static void main(String[] args) {
      // เข้า Assessment -> กด ส่ง Email
      sending("","","firstziiz.k@gmail.com");
  }
  
  public static void sending(String subject,String messages,String to){
        final String FROM = "Kanisorn Sutham <firstziiz.k@gmail.com>";
        final String username = "postmaster@sandbox4642fa3bdddf495796f8319523771971.mailgun.org";
        final String password = "6aac52e0213eeca8515de7dae3ae0ef6";

        String sb = subject;
        String ms = messages;
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mailgun.org");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM)); // sengding from
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to)); // sending to
            message.setSubject(sb);
            message.setText(ms);
            

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
