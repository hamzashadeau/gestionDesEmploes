package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Punition {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
private Long id; 
private String	type;
private String	libelle;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Punition(String type, String libelle) {
	super();
	this.type = type;
	this.libelle = libelle;
}
public Punition() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Punition [id=" + id + ", type=" + type + ", libelle=" + libelle + "]";
}

}
