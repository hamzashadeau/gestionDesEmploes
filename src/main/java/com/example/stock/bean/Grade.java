package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Grade {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String libelle;
private Integer nombreDePosteNonOccupe;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Integer getNombreDePosteNonOccupe() {
	return nombreDePosteNonOccupe;
}
public void setNombreDePosteNonOccupe(Integer nombreDePosteNonOccupe) {
	this.nombreDePosteNonOccupe = nombreDePosteNonOccupe;
}
@Override
public String toString() {
	return "Grade [id=" + id + ", libelle=" + libelle + ", nombreDePosteNonOccupe=" + nombreDePosteNonOccupe + "]";
}
public Grade() {
	super();
	// TODO Auto-generated constructor stub
}
public Grade(Long id, String libelle, Integer nombreDePosteNonOccupe) {
	super();
	this.id = id;
	this.libelle = libelle;
	this.nombreDePosteNonOccupe = nombreDePosteNonOccupe;
}

}
