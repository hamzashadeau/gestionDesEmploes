package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Departement {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String nom;
@JsonProperty(access = Access.WRITE_ONLY)
@ManyToOne
private Employe chef;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public Employe getChef() {
	return chef;
}
public void setChef(Employe chef) {
	this.chef = chef;
}
public Departement() {
	super();
	// TODO Auto-generated constructor stub
}
public Departement(String nom, Employe chef) {
	super();
	this.nom = nom;
	this.chef = chef;
}
@Override
public String toString() {
	return "Departement [id=" + id + ", nom=" + nom + ", chef=" + chef + "]";
}


}
