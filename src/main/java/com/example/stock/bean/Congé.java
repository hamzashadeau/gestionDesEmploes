package com.example.stock.bean;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Congé {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@Temporal(TemporalType.DATE )
private Date dateDeDebut;
@Temporal(TemporalType.DATE )
private Date dateDeFin;
@ManyToOne
private Employe employe;
private String doti;
@ManyToOne
private TypeCongee congee;
private Integer periode;
private String raison;
@OneToMany
List<CongéEmployeSalaire> congéEmployeSalaires;
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
public String getRaison() {
	return raison;
}
public void setRaison(String raison) {
	this.raison = raison;
}
public Date getDateDeFin() {
	return dateDeFin;
}
public void setDateDeFin(Date dateDeFin) {
	this.dateDeFin = dateDeFin;
}
public String getDoti() {
	return doti;
}
public void setDoti(String doti) {
	this.doti = doti;
}
public List<CongéEmployeSalaire> getCongéEmployeSalaires() {
	return congéEmployeSalaires;
}
public void setCongéEmployeSalaires(List<CongéEmployeSalaire> congéEmployeSalaires) {
	this.congéEmployeSalaires = congéEmployeSalaires;
}
public Congé(Long id, Date dateDeDebut, Date dateDeFin, Employe employe, String doti, TypeCongee congee,
		Integer periode, String raison, List<CongéEmployeSalaire> congéEmployeSalaires) {
	super();
	this.id = id;
	this.dateDeDebut = dateDeDebut;
	this.dateDeFin = dateDeFin;
	this.employe = employe;
	this.doti = doti;
	this.congee = congee;
	this.periode = periode;
	this.raison = raison;
	this.congéEmployeSalaires = congéEmployeSalaires;
}
@Override
public String toString() {
	return "Congé [id=" + id + ", dateDeDebut=" + dateDeDebut + ", dateDeFin=" + dateDeFin + ", employe=" + employe
			+ ", doti=" + doti + ", congee=" + congee + ", periode=" + periode + ", raison=" + raison
			+ ", congéEmployeSalaires=" + congéEmployeSalaires + "]";
}








}
