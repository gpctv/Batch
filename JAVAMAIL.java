package com.stan.exce;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JAVAMAIL {

	public static void main(String args[]) {
		JAVAMAIL javamail=new JAVAMAIL();
		javamail.sendEmail();
	}

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private static final String SERVIDOR_SMTP = "smtp-mail.outlook.com";
    private static final String PORTA_SERVIDOR_SMTP = "587";
    private static final String CONTA_PADRAO = "test@hotmail.com";
    private static final String SENHA_CONTA_PADRAO = "password";

    private final String from = "test@hotmail.com";
    private final String to = "test@gmail.com";

    private final String subject = "Teste";
    private final String messageContent = "Teste 1234";

    public void sendEmail() {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONTA_PADRAO, SENHA_CONTA_PADRAO);
            }

        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
        }
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SERVIDOR_SMTP);
        config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        config.put("mail.transport.protocol", "smtp");
        config.put("mail.debug", "true");
        return config;
    }
}
