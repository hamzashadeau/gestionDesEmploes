package com.example.stock.bean;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DemaneDeDocument {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private Employe employe;
@Temporal(TemporalType.DATE)
private Date dateDemande;
private String typeDeDocument;
private String maniereDeRetrait;
private String etat;
public DemaneDeDocument() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public Date getDateDemande() {
	return dateDemande;
}
public void setDateDemande(Date dateDemande) {
	this.dateDemande = dateDemande;
}
public String getTypeDeDocument() {
	return typeDeDocument;
}
public void setTypeDeDocument(String typeDeDocument) {
	this.typeDeDocument = typeDeDocument;
}
public String getManiereDeRetrait() {
	return maniereDeRetrait;
}
public void setManiereDeRetrait(String maniereDeRetrait) {
	this.maniereDeRetrait = maniereDeRetrait;
}
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}
public DemaneDeDocument(Employe employe, Date dateDemande, String typeDeDocument, String maniereDeRetrait,
		String etat) {
	super();
	this.employe = employe;
	this.dateDemande = dateDemande;
	this.typeDeDocument = typeDeDocument;
	this.maniereDeRetrait = maniereDeRetrait;
	this.etat = etat;
}
@Override
public String toString() {
	return "DemaneDeDocument [id=" + id + ", employe=" + employe + ", dateDemande=" + dateDemande + ", typeDeDocument="
			+ typeDeDocument + ", maniereDeRetrait=" + maniereDeRetrait + ", etat=" + etat + "]";
}

}
