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
private Integer chefdoti;
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
public Integer getChefdoti() {
	return chefdoti;
}
public void setChefdoti(Integer chefdoti) {
	this.chefdoti = chefdoti;
}
public Departement(Long id, String nom, Integer chefdoti) {
	super();
	this.id = id;
	this.nom = nom;
	this.chefdoti = chefdoti;
}
public Departement() {
	super();
	// TODO Auto-generated constructor stub
}

}
