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
public class PrixEmploye {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private Prix prix;
@ManyToOne
private Employe employe;
@Temporal(TemporalType.DATE)
private Date dateDeObtenation;
private String remarque;
private String doti;
public PrixEmploye() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Prix getPrix() {
	return prix;
}
public void setPrix(Prix prix) {
	this.prix = prix;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public Date getDateDeObtenation() {
	return dateDeObtenation;
}
public void setDateDeObtenation(Date dateDeObtenation) {
	this.dateDeObtenation = dateDeObtenation;
}
public String getRemarque() {
	return remarque;
}
public void setRemarque(String remarque) {
	this.remarque = remarque;
}
public String getDoti() {
	return doti;
}
public void setDoti(String doti) {
	this.doti = doti;
}
public PrixEmploye(Long id, Prix prix, Employe employe, Date dateDeObtenation, String remarque) {
	super();
	this.id = id;
	this.prix = prix;
	this.employe = employe;
	this.dateDeObtenation = dateDeObtenation;
	this.remarque = remarque;
}
@Override
public String toString() {
	return "PrixEmploye [id=" + id + ", prix=" + prix + ", employe=" + employe + ", dateDeObtenation="
			+ dateDeObtenation + ", remarque=" + remarque + ", doti=" + doti + "]";
}




}
