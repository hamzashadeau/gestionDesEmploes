package com.example.stock.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NoteGeneralDeAnnee {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private Note noteDeAffectationDesTachesLiéeAuTravail ;
@ManyToOne
private Note noteDeRentabilité;
@ManyToOne
private Note noteDeCapacitéDeOrganisation;
@ManyToOne
private Note noteDeCompotement;
@ManyToOne
private Note noteDeRechercheEtDeInnovation;
private Double moyenGeneral;//a calculer
private String mention;
@ManyToOne
private Employe employé;
private Date date;
public NoteGeneralDeAnnee() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Note getNoteDeAffectationDesTachesLiéeAuTravail() {
	return noteDeAffectationDesTachesLiéeAuTravail;
}
public void setNoteDeAffectationDesTachesLiéeAuTravail(Note noteDeAffectationDesTachesLiéeAuTravail) {
	this.noteDeAffectationDesTachesLiéeAuTravail = noteDeAffectationDesTachesLiéeAuTravail;
}
public Note getNoteDeRentabilité() {
	return noteDeRentabilité;
}
public void setNoteDeRentabilité(Note noteDeRentabilité) {
	this.noteDeRentabilité = noteDeRentabilité;
}
public Note getNoteDeCapacitéDeOrganisation() {
	return noteDeCapacitéDeOrganisation;
}
public void setNoteDeCapacitéDeOrganisation(Note noteDeCapacitéDeOrganisation) {
	this.noteDeCapacitéDeOrganisation = noteDeCapacitéDeOrganisation;
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
public Employe getEmployé() {
	return employé;
}
public void setEmployé(Employe employé) {
	this.employé = employé;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public NoteGeneralDeAnnee(Note noteDeAffectationDesTachesLiéeAuTravail, Note noteDeRentabilité,
		Note noteDeCapacitéDeOrganisation, Note noteDeCompotement, Note noteDeRechercheEtDeInnovation,
		Double moyenGeneral, String mention, Employe employé, Date date) {
	super();
	this.noteDeAffectationDesTachesLiéeAuTravail = noteDeAffectationDesTachesLiéeAuTravail;
	this.noteDeRentabilité = noteDeRentabilité;
	this.noteDeCapacitéDeOrganisation = noteDeCapacitéDeOrganisation;
	this.noteDeCompotement = noteDeCompotement;
	this.noteDeRechercheEtDeInnovation = noteDeRechercheEtDeInnovation;
	this.moyenGeneral = moyenGeneral;
	this.mention = mention;
	this.employé = employé;
	this.date = date;
}
@Override
public String toString() {
	return "NoteGeneralDeAnnee [id=" + id + ", noteDeAffectationDesTachesLiéeAuTravail="
			+ noteDeAffectationDesTachesLiéeAuTravail + ", noteDeRentabilité=" + noteDeRentabilité
			+ ", noteDeCapacitéDeOrganisation=" + noteDeCapacitéDeOrganisation + ", noteDeCompotement="
			+ noteDeCompotement + ", noteDeRechercheEtDeInnovation=" + noteDeRechercheEtDeInnovation + ", moyenGeneral="
			+ moyenGeneral + ", mention=" + mention + ", employé=" + employé + ", date=" + date + "]";
}




}
