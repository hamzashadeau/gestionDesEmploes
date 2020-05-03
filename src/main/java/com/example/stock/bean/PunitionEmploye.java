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
public class PunitionEmploye {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
private Long id; 
@ManyToOne
	private Employe	employe;
@ManyToOne 
private Punition punition;
@Temporal(TemporalType.DATE)
private Date dateObtenation;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public PunitionEmploye() {
	super();
	// TODO Auto-generated constructor stub
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public Punition getPunition() {
	return punition;
}
public void setPunition(Punition punition) {
	this.punition = punition;
}
public Date getDateObtenation() {
	return dateObtenation;
}
public void setDateObtenation(Date dateObtenation) {
	this.dateObtenation = dateObtenation;
}
public PunitionEmploye(Employe employe, Punition punition, Date dateObtenation) {
	super();
	this.employe = employe;
	this.punition = punition;
	this.dateObtenation = dateObtenation;
}
@Override
public String toString() {
	return "PunitionEmploye [id=" + id + ", employe=" + employe + ", punition=" + punition + ", dateObtenation="
			+ dateObtenation + "]";
}

}
