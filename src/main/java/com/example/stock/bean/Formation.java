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
private String	établissement ;
private String	ville;
@Temporal(TemporalType.DATE)
private Date annee;
private String mention;
@ManyToOne()
private Employe	employé;
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
public String getÉtablissement() {
	return établissement;
}
public void setÉtablissement(String établissement) {
	this.établissement = établissement;
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
public Employe getEmployé() {
	return employé;
}
public void setEmployé(Employe employé) {
	this.employé = employé;
}
public Formation(String attestation, String domaine, String établissement, String ville, Date annee, String mention,
		Employe employé) {
	super();
	this.attestation = attestation;
	this.domaine = domaine;
	this.établissement = établissement;
	this.ville = ville;
	this.annee = annee;
	this.mention = mention;
	this.employé = employé;
}
@Override
public String toString() {
	return "Formation [id=" + id + ", attestation=" + attestation + ", domaine=" + domaine + ", établissement="
			+ établissement + ", ville=" + ville + ", annee=" + annee + ", mention=" + mention + ", employé=" + employé
			+ "]";
}
public Formation() {
	super();
	// TODO Auto-generated constructor stub
}

}
