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
public class Congé {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@Temporal(TemporalType.DATE )
private Date dateDeDebut;
@ManyToOne
private Employe employe;
@ManyToOne
private TypeCongee congee;
private Integer periode;
private String raison;
private String etat;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Date getDateDeDebut() {
	return dateDeDebut;
}
public void setDateDeDebut(Date dateDeDebut) {
	this.dateDeDebut = dateDeDebut;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public Integer getPeriode() {
	return periode;
}
public void setPeriode(Integer periode) {
	this.periode = periode;
}
public String getRaison() {
	return raison;
}
public void setRaison(String raison) {
	this.raison = raison;
}
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}
public Congé() {
	super();
	// TODO Auto-generated constructor stub
}
public TypeCongee getCongee() {
	return congee;
}
public void setCongee(TypeCongee congee) {
	this.congee = congee;
}
public Congé(Date dateDeDebut, Employe employe, TypeCongee congee, Integer periode, String raison, String etat) {
	super();
	this.dateDeDebut = dateDeDebut;
	this.employe = employe;
	this.congee = congee;
	this.periode = periode;
	this.raison = raison;
	this.etat = etat;
}
@Override
public String toString() {
	return "Congé [id=" + id + ", dateDeDebut=" + dateDeDebut + ", employe=" + employe + ", congee=" + congee
			+ ", periode=" + periode + ", raison=" + raison + ", etat=" + etat + "]";
}



}
