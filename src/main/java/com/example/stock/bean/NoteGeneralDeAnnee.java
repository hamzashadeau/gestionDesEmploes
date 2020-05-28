package com.example.stock.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class NoteGeneralDeAnnee {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private Double moyenGeneral;//a calculer
private String mention;
private String employeDoti;
private String fuulName;
@ManyToOne
private Note noteDeAffectationDesTachesLieeAuTravail;
@ManyToOne
private Note noteDeRentabilite;
@ManyToOne
private Note noteDeCapaciteDeOrganisation;
@ManyToOne
private Note noteDeCompotement;
@ManyToOne
private Note noteDeRechercheEtDeInnovation;
@Temporal(TemporalType.DATE)
private Date date;
private String etat;
public NoteGeneralDeAnnee() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}

public Note getNoteDeRentabilite() {
	return noteDeRentabilite;
}
public void setNoteDeRentabilite(Note noteDeRentabilite) {
	this.noteDeRentabilite = noteDeRentabilite;
}
public void setId(Long id) {
	this.id = id;
}
public Double getMoyenGeneral() {
	return moyenGeneral;
}
public void setMoyenGeneral(Double moyenGeneral) {
	this.moyenGeneral = moyenGeneral;
}
public String getMention() {
	return mention;
}
public void setMention(String mention) {
	this.mention = mention;
}

public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Note getNoteDeAffectationDesTachesLieeAuTravail() {
	return noteDeAffectationDesTachesLieeAuTravail;
}
public void setNoteDeAffectationDesTachesLieeAuTravail(Note noteDeAffectationDesTachesLieeAuTravail) {
	this.noteDeAffectationDesTachesLieeAuTravail = noteDeAffectationDesTachesLieeAuTravail;
}

public Note getNoteDeCapaciteDeOrganisation() {
	return noteDeCapaciteDeOrganisation;
}
public void setNoteDeCapaciteDeOrganisation(Note noteDeCapaciteDeOrganisation) {
	this.noteDeCapaciteDeOrganisation = noteDeCapaciteDeOrganisation;
}
public Note getNoteDeCompotement() {
	return noteDeCompotement;
}
public void setNoteDeCompotement(Note noteDeCompotement) {
	this.noteDeCompotement = noteDeCompotement;
}
public Note getNoteDeRechercheEtDeInnovation() {
	return noteDeRechercheEtDeInnovation;
}
public void setNoteDeRechercheEtDeInnovation(Note noteDeRechercheEtDeInnovation) {
	this.noteDeRechercheEtDeInnovation = noteDeRechercheEtDeInnovation;
}
public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}
public String getEmployeDoti() {
	return employeDoti;
}
public void setEmployeDoti(String employeDoti) {
	this.employeDoti = employeDoti;
}
public String getFuulName() {
	return fuulName;
}
public void setFuulName(String fuulName) {
	this.fuulName = fuulName;
}
public NoteGeneralDeAnnee(Long id, Double moyenGeneral, String mention, String employeDoti, String fuulName,
		Note noteDeAffectationDesTachesLieeAuTravail, Note noteDeRentabilite, Note noteDeCapaciteDeOrganisation,
		Note noteDeCompotement, Note noteDeRechercheEtDeInnovation, Date date, String etat) {
	super();
	this.id = id;
	this.moyenGeneral = moyenGeneral;
	this.mention = mention;
	this.employeDoti = employeDoti;
	this.fuulName = fuulName;
	this.noteDeAffectationDesTachesLieeAuTravail = noteDeAffectationDesTachesLieeAuTravail;
	this.noteDeRentabilite = noteDeRentabilite;
	this.noteDeCapaciteDeOrganisation = noteDeCapaciteDeOrganisation;
	this.noteDeCompotement = noteDeCompotement;
	this.noteDeRechercheEtDeInnovation = noteDeRechercheEtDeInnovation;
	this.date = date;
	this.etat = etat;
}
@Override
public String toString() {
	return "NoteGeneralDeAnnee [id=" + id + ", moyenGeneral=" + moyenGeneral + ", mention=" + mention + ", employeDoti="
			+ employeDoti + ", fuulName=" + fuulName + ", noteDeAffectationDesTachesLieeAuTravail="
			+ noteDeAffectationDesTachesLieeAuTravail + ", noteDeRentabilite=" + noteDeRentabilite
			+ ", noteDeCapaciteDeOrganisation=" + noteDeCapaciteDeOrganisation + ", noteDeCompotement="
			+ noteDeCompotement + ", noteDeRechercheEtDeInnovation=" + noteDeRechercheEtDeInnovation + ", date=" + date
			+ ", etat=" + etat + "]";
}




}
