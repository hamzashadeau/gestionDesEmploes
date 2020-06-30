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
private String doti;
@Temporal(TemporalType.DATE)
private Date dateDemande;
@ManyToOne
private TypeDocument typeDeDocument;
private String copieEmail;
private String etat;
private int nbrDeDocument;
public DemaneDeDocument() {
	super();
	// TODO Auto-generated constructor stub
}


public int getNbrDeDocument() {
	return nbrDeDocument;
}


public void setNbrDeDocument(int nbrDeDocument) {
	this.nbrDeDocument = nbrDeDocument;
}


public TypeDocument getTypeDeDocument() {
	return typeDeDocument;
}


public void setTypeDeDocument(TypeDocument typeDeDocument) {
	this.typeDeDocument = typeDeDocument;
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
public String getCopieEmail() {
	return copieEmail;
}
public void setCopieEmail(String copieEmail) {
	this.copieEmail = copieEmail;
}
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}


public String getDoti() {
	return doti;
}


public void setDoti(String doti) {
	this.doti = doti;
}


public DemaneDeDocument(Long id, Employe employe,  Date dateDemande, TypeDocument typeDeDocument,
		String copieEmail, String etat, int nbrDeDocument) {
	super();
	this.id = id;
	this.employe = employe;
	this.dateDemande = dateDemande;
	this.typeDeDocument = typeDeDocument;
	this.copieEmail = copieEmail;
	this.etat = etat;
	this.nbrDeDocument = nbrDeDocument;
}


@Override
public String toString() {
	return "DemaneDeDocument [id=" + id + ", employe=" + employe + ", doti=" + doti + ", dateDemande=" + dateDemande
			+ ", typeDeDocument=" + typeDeDocument + ", copieEmail=" + copieEmail + ", etat=" + etat
			+ ", nbrDeDocument=" + nbrDeDocument + "]";
}



}
