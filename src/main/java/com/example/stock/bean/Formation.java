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
public class Formation {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
private String 	attestation ; 
private String	domaine; 
private String	etablissement ;
private String	ville;
@Temporal(TemporalType.DATE)
private Date annee;
private String mention;
@ManyToOne()
private Employe	employe;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getAttestation() {
	return attestation;
}
public void setAttestation(String attestation) {
	this.attestation = attestation;
}
public String getDomaine() {
	return domaine;
}
public void setDomaine(String domaine) {
	this.domaine = domaine;
}

public String getVille() {
	return ville;
}
public void setVille(String ville) {
	this.ville = ville;
}
public Date getAnnee() {
	return annee;
}
public void setAnnee(Date annee) {
	this.annee = annee;
}
public String getMention() {
	return mention;
}
public void setMention(String mention) {
	this.mention = mention;
}

public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public Formation() {
	super();
	// TODO Auto-generated constructor stub
}
public String getEtablissement() {
	return etablissement;
}
public void setEtablissement(String etablissement) {
	this.etablissement = etablissement;
}
public Formation(Long id, String attestation, String domaine, String etablissement, String ville, Date annee,
		String mention, Employe employe) {
	super();
	this.id = id;
	this.attestation = attestation;
	this.domaine = domaine;
	this.etablissement = etablissement;
	this.ville = ville;
	this.annee = annee;
	this.mention = mention;
	this.employe = employe;
}
@Override
public String toString() {
	return "Formation [id=" + id + ", attestation=" + attestation + ", domaine=" + domaine + ", etablissement="
			+ etablissement + ", ville=" + ville + ", annee=" + annee + ", mention=" + mention + ", employe=" + employe
			+ "]";
}


}
