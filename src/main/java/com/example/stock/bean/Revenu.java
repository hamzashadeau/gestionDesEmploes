package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Revenu {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String libelle;
private Double montant;
public Revenu() {
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
public Revenu(String libelle, Double montant) {
	super();
	this.libelle = libelle;
	this.montant = montant;
}
@Override
public String toString() {
	return "Revenu [id=" + id + ", libelle=" + libelle + ", montant=" + montant + "]";
}
public Double getMontant() {
	return montant;
}
public void setMontant(Double montant) {
	this.montant = montant;
}



}
