package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DepFonction {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private Departement departemant;
@ManyToOne
private Fonction fonction;
public DepFonction() {
	super();
	// TODO Auto-generated constructor stub
}
public Departement getDepartemant() {
	return departemant;
}
public void setDepartemant(Departement departemant) {
	this.departemant = departemant;
}
public Fonction getFonction() {
	return fonction;
}
public void setFonction(Fonction fonction) {
	this.fonction = fonction;
}

@Override
public String toString() {
	return "DepFonction [id=" + id + ", departemant=" + departemant + ", fonction=" + fonction + "]";
}
public DepFonction(Long id, Departement departemant, Fonction fonction) {
	super();
	this.id = id;
	this.departemant = departemant;
	this.fonction = fonction;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

}
