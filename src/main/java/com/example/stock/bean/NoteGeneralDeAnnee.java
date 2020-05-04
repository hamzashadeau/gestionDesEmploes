package com.example.stock.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NoteGeneralDeAnnee {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private Double moyenGeneral;//a calculer
private String mention;
@ManyToOne
private Employe employé;
private Date date;
@OneToMany
private List<NoteEmploye> noteEmployes;
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
public List<NoteEmploye> getNoteEmployes() {
	return noteEmployes;
}
public void setNoteEmployes(List<NoteEmploye> noteEmployes) {
	this.noteEmployes = noteEmployes;
}
public NoteGeneralDeAnnee(Double moyenGeneral, String mention, Employe employé, Date date,
		List<NoteEmploye> noteEmployes) {
	super();
	this.moyenGeneral = moyenGeneral;
	this.mention = mention;
	this.employé = employé;
	this.date = date;
	this.noteEmployes = noteEmployes;
}
@Override
public String toString() {
	return "NoteGeneralDeAnnee [id=" + id + ", moyenGeneral=" + moyenGeneral + ", mention=" + mention + ", employé="
			+ employé + ", date=" + date + ", noteEmployes=" + noteEmployes + "]";
}
}
