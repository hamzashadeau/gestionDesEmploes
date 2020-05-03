package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SalaireEmployeMensuel {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private SalaireEmploye salaireEmploye;
private Double   deductions;
private Integer year;
private Integer mois;
private Double monatntModifie;
@ManyToOne
private Employe employe;
public SalaireEmployeMensuel() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public SalaireEmploye getSalaireEmploye() {
	return salaireEmploye;
}
public void setSalaireEmploye(SalaireEmploye salaireEmploye) {
	this.salaireEmploye = salaireEmploye;
}
public Integer getYear() {
	return year;
}
public void setYear(Integer year) {
	this.year = year;
}
public Integer getMois() {
	return mois;
}
public void setMois(Integer mois) {
	this.mois = mois;
}
public Double getMonatntModifie() {
	return monatntModifie;
}
public void setMonatntModifie(Double monatntModifie) {
	this.monatntModifie = monatntModifie;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public Double getDeductions() {
	return deductions;
}
public void setDeductions(Double deductions) {
	this.deductions = deductions;
}
public SalaireEmployeMensuel(SalaireEmploye salaireEmploye, Double deductions, Integer year, Integer mois,
		Double monatntModifie, Employe employe) {
	super();
	this.salaireEmploye = salaireEmploye;
	this.deductions = deductions;
	this.year = year;
	this.mois = mois;
	this.monatntModifie = monatntModifie;
	this.employe = employe;
}
@Override
public String toString() {
	return "SalaireEmployeMensuel [id=" + id + ", salaireEmploye=" + salaireEmploye + ", deductions=" + deductions
			+ ", year=" + year + ", mois=" + mois + ", monatntModifie=" + monatntModifie + ", employe=" + employe + "]";
}

}
