package com.example.stock.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class NotificationEmploye {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private Notification notification;
@ManyToOne
private Employe employe;
@Temporal(TemporalType.TIMESTAMP)
private Date dateDeNotification;
private String libelle;
//private String etat;//lus ou 
public NotificationEmploye() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}

public Notification getNotification() {
	return notification;
}
public void setNotification(Notification notification) {
	this.notification = notification;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Date getDateDeNotification() {
	return dateDeNotification;
}
public void setDateDeNotification(Date dateDeNotification) {
	this.dateDeNotification = dateDeNotification;
}
public NotificationEmploye(Notification notification, Employe employe, Date dateDeNotification, String libelle) {
	super();
	this.notification = notification;
	this.employe = employe;
	this.dateDeNotification = dateDeNotification;
	this.libelle = libelle;
}
@Override
public String toString() {
	return "NotificationEmploye [id=" + id + ", notification=" + notification + ", employe=" + employe
			+ ", dateDeNotification=" + dateDeNotification + ", libelle=" + libelle + "]";
}


}
