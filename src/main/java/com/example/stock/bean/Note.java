package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Note {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private Double mention;
private String remarque;
private String libelle;
public Note() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Double getMention() {
	return mention;
}
public void setMention(Double mention) {
	this.mention = mention;
}
public String getRemarque() {
	return remarque;
}
public void setRemarque(String remarque) {
	this.remarque = remarque;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Note(Double mention, String remarque, String libelle) {
	super();
	this.mention = mention;
	this.remarque = remarque;
	this.libelle = libelle;
}
@Override
public String toString() {
	return "Note [id=" + id + ", mention=" + mention + ", remarque=" + remarque + ", libelle=" + libelle + "]";
}




}
