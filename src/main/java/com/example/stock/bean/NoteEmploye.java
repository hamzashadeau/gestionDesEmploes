package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NoteEmploye {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private Note note;
@ManyToOne
private NoteGeneralDeAnnee noteGeneralDeAnnee;
@ManyToOne
private Employe employe;
public NoteEmploye() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Note getNote() {
	return note;
}
public void setNote(Note note) {
	this.note = note;
}
public NoteGeneralDeAnnee getNoteGeneralDeAnnee() {
	return noteGeneralDeAnnee;
}
public void setNoteGeneralDeAnnee(NoteGeneralDeAnnee noteGeneralDeAnnee) {
	this.noteGeneralDeAnnee = noteGeneralDeAnnee;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
public NoteEmploye(Note note, NoteGeneralDeAnnee noteGeneralDeAnnee, Employe employe) {
	super();
	this.note = note;
	this.noteGeneralDeAnnee = noteGeneralDeAnnee;
	this.employe = employe;
}
@Override
public String toString() {
	return "NoteEmploye [id=" + id + ", note=" + note + ", noteGeneralDeAnnee=" + noteGeneralDeAnnee + ", employe="
			+ employe + "]";
}
}
