package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PermanenceAdministrative {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private Integer periode;
private Boolean recuperation;
private Integer periodeDeRecuperation;
@ManyToOne()
private Employe employe;
public Integer getPeriode() {
	return periode;
}
public void setPeriode(Integer periode) {
	this.periode = periode;
}
public Boolean getRecuperation() {
	return recuperation;
}
public void setRecuperation(Boolean recuperation) {
	this.recuperation = recuperation;
}
public Integer getPeriodeDeRecuperation() {
	return periodeDeRecuperation;
}
public void setPeriodeDeRecuperation(Integer periodeDeRecuperation) {
	this.periodeDeRecuperation = periodeDeRecuperation;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public PermanenceAdministrative() {
	super();
	// TODO Auto-generated constructor stub
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public PermanenceAdministrative(Integer periode, Boolean recuperation, Integer periodeDeRecuperation, Employe employe) {
	super();
	this.periode = periode;
	this.recuperation = recuperation;
	this.periodeDeRecuperation = periodeDeRecuperation;
	this.employe = employe;
}
@Override
public String toString() {
	return "PermanenceAdministrative [id=" + id + ", periode=" + periode + ", recuperation=" + recuperation
			+ ", periodeDeRecuperation=" + periodeDeRecuperation + ", employe=" + employe + "]";
}

}
