package com.example.stock.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CongéEmployeSalaire {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private Integer mois;
private String typeDeSalaire;
@ManyToOne
private Congé conge;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Integer getMois() {
	return mois;
}
public void setMois(Integer mois) {
	this.mois = mois;
}
public String getTypeDeSalaire() {
	return typeDeSalaire;
}
public void setTypeDeSalaire(String typeDeSalaire) {
	this.typeDeSalaire = typeDeSalaire;
}
public Congé getConge() {
	return conge;
}
public void setConge(Congé conge) {
	this.conge = conge;
}
public CongéEmployeSalaire(Long id, Integer mois, String typeDeSalaire, Congé conge) {
	super();
	this.id = id;
	this.mois = mois;
	this.typeDeSalaire = typeDeSalaire;
	this.conge = conge;
}
@Override
public String toString() {
	return "CongéEmployeSalaire [id=" + id + ", mois=" + mois + ", typeDeSalaire=" + typeDeSalaire + ", conge=" + conge
			+ "]";
}
public CongéEmployeSalaire() {
	super();
	// TODO Auto-generated constructor stub
}




}
