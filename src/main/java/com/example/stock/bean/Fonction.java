package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fonction {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String libelle;
public Fonction() {
	super();
	// TODO Auto-generated constructor stub
}
public Fonction(Long id, String libelle) {
	super();
	this.id = id;
	this.libelle = libelle;
}
@Override
public String toString() {
	return "Fonction [id=" + id + ", libelle=" + libelle + "]";
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Fonction(String libelle) {
	super();
	this.libelle = libelle;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}



}
