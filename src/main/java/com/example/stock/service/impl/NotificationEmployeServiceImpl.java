package com.example.stock.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.NotificationEmployeDao;
import com.example.stock.Utilis.DateUlils;
import com.example.stock.bean.NotificationEmploye;
import com.example.stock.service.facade.NotificationEmployeService;

@Service
public class NotificationEmployeServiceImpl implements NotificationEmployeService {
@Autowired
private NotificationEmployeDao notificationEmployeDao;


@Override
public int save(NotificationEmploye notificationEmploye) {
	if(notificationEmploye.getId()!= null) {
return -1;
}else {
	notificationEmployeDao.save(notificationEmploye);
		return 1;
}
	}
@Override
public int update(NotificationEmploye notificationEmploye) {
	if(notificationEmploye.getId() == null) {
return -1;
}else {
	notificationEmployeDao.save(notificationEmploye);
		return 1;
}
	}

@Override
public NotificationEmploye findByid(Long id) {
	if (notificationEmployeDao.findById(id).isPresent()) {
		return notificationEmployeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	notificationEmployeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<NotificationEmploye> findAll() {
	return notificationEmployeDao.findAll();
}


@Override
public List<NotificationEmploye> findByEmployeId(Long id) {
	return notificationEmployeDao.findByEmployeId(id);
}

@Override
public List<NotificationEmploye> findByEmployeEmail(String email) {
	return notificationEmployeDao.findByEmployeEmail(email);
}

@Override
public List<NotificationEmploye> findByemployeDoti(String doti) {
	return notificationEmployeDao.findByemployeDoti(doti);
}

@Override
public List<NotificationEmploye> findByDateDeNotification(Date dateDeObtenation) {
	return notificationEmployeDao.findByDateDeNotification(dateDeObtenation);
}

@Override
public int sendmail(String email, String subject,String content) throws AddressException, MessagingException, IOException {
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
	   messageBodyPart.setContent(content, "text/html");

	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(messageBodyPart);
	  // MimeBodyPart attachPart = new MimeBodyPart();
	//   multipart.addBodyPart(attachPart);
	   msg.setContent(multipart);

	 //  attachPart.attachFile("/var/tmp/image19.png");
	   Transport.send(msg);  
	   return 1;
	}

@Override
public List<NotificationEmploye> findNotificationAujourdhui() {
	String pattern = "yyyy-MM-dd";
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	List<NotificationEmploye> notificationEmployes = findAll();
	List<NotificationEmploye> resultat = new ArrayList<NotificationEmploye>();
	notificationEmployes.forEach( not ->{
		
		if(simpleDateFormat.format(not.getDateDeNotification()).equals(simpleDateFormat.format(new Date()))) {
			resultat.add(not);
		}
	});
	return resultat;
}
@Override
public List<NotificationEmploye> findNotificationPaDate(Date date) {
	String pattern = "yyyy-MM-dd";
	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	List<NotificationEmploye> notificationEmployes = findAll();
	List<NotificationEmploye> resultat = new ArrayList<NotificationEmploye>();
	notificationEmployes.forEach( not ->{
		if(simpleDateFormat.format(not.getDateDeNotification()).equals(simpleDateFormat.format(date))) {
			resultat.add(not);
		}
	});
	return resultat;
}
}
