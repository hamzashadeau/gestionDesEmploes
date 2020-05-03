package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Emoluments {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String libelle;
private Double montant;
public Emoluments() {
	super();
	// TODO Auto-generated constructor stub
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Double getMontant() {
	return montant;
}
public void setMontant(Double montant) {
	this.montant = montant;
}
public Emoluments(String libelle, Double montant) {
	super();
	this.libelle = libelle;
	this.montant = montant;
}
@Override
public String toString() {
	return "Emoluments [id=" + id + ", libelle=" + libelle + ", montant=" + montant + "]";
}

}
