package com.example.stock.Utilis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

import com.itextpdf.text.Document;

public class HashUtil {
	public static String hash(String mdp) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(mdp.getBytes());
		byte byteData[] = md.digest();
StringBuffer hexString = new StringBuffer();
for (int i = 0; i < byteData.length; i++) {
	String hex = Integer.toHexString(0xff & byteData[i]);
	if(hex.length()==1) hexString.append('0');
	hexString.append(hex);
}
	System.out.println("En format hexa:" + hexString.toString());
	return hexString.toString();
	}
	
	public static Long generateRandomIntIntRange(int min, int max) {
	    Random r = new Random();
	    	Long l=new Long(r.nextInt((max - min) + 1) + min);
	    		return  l;
	}
	public static void  sendmail(String email, String subject,String content,String url) throws AddressException, MessagingException, IOException, TransformerException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");

		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("etablissementfstg@gmail.com", "Fstg-2020/2021");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress(email, false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject(subject);
		   msg.setContent(content, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(content , "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();
		   attachPart.attachFile(new File(url));
		      multipart.addBodyPart(attachPart);
		   //MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		   //attachmentBodyPart.attachFile(new File("path/to/file"))
		   msg.setContent(multipart);

		  //attachPart.attachFile();
		   Transport.send(msg);  
		}
	public static void  sendCodeVerification(String email, String subject,String content,Long codeDeVerification) throws AddressException, MessagingException, IOException, TransformerException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");

		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("etablissementfstg@gmail.com", "Fstg-2020/2021");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress(email, false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		   msg.setSubject(subject);
		   msg.setContent(content, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(content + "voila votre code de verification: \n\r" + codeDeVerification , "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   //MimeBodyPart attachPart = new MimeBodyPart();
		   //attachPart.attachFile(new File(url));
		  //    multipart.addBodyPart(attachPart);
		   //MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		   //attachmentBodyPart.attachFile(new File("path/to/file"))
		   msg.setContent(multipart);

		  //attachPart.attachFile();
		   Transport.send(msg);  
		}
}
