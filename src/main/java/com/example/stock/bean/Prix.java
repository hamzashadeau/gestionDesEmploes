package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prix {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String libelle;

public Prix() {
	super();
	// TODO Auto-generated constructor stub
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Prix(String libelle, Double montant) {
	super();
	this.libelle = libelle;
}

@Override
public String toString() {
	return "Prime [id=" + id + ", libelle=" + libelle + ", montant=" + "]";
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}



}
