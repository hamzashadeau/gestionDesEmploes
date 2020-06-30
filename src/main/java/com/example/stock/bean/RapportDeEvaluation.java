package com.example.stock.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RapportDeEvaluation {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
@ManyToOne
private Employe employe;
@ManyToOne
private GradeEmploye nouveauGrade;//nouveau Grade
private Double moyen;
private String mention;
private String remarques;
private String doti;
@OneToMany
private List<PrixEmploye> prix;
@OneToMany
private List<PunitionEmploye> punition;
@OneToMany
private List<Formation> formation;
@OneToMany
private List<NoteGeneralDeAnnee> noteGenerale;

public RapportDeEvaluation() {
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

public Double getMoyen() {
	return moyen;
}

public void setMoyen(Double moyen) {
	this.moyen = moyen;
}

public String getMention() {
	return mention;
}

public void setMention(String mention) {
	this.mention = mention;
}

public String getRemarques() {
	return remarques;
}

public void setRemarques(String remarques) {
	this.remarques = remarques;
}


public List<PrixEmploye> getPrix() {
	return prix;
}

public void setPrix(List<PrixEmploye> prix) {
	this.prix = prix;
}

public List<PunitionEmploye> getPunition() {
	return punition;
}

public void setPunition(List<PunitionEmploye> punition) {
	this.punition = punition;
}

public List<Formation> getFormation() {
	return formation;
}

public void setFormation(List<Formation> formation) {
	this.formation = formation;
}

public List<NoteGeneralDeAnnee> getNoteGenerale() {
	return noteGenerale;
}

public void setNoteGenerale(List<NoteGeneralDeAnnee> noteGenerale) {
	this.noteGenerale = noteGenerale;
}

public GradeEmploye getNouveauGrade() {
	return nouveauGrade;
}

public void setNouveauGrade(GradeEmploye nouveauGrade) {
	this.nouveauGrade = nouveauGrade;
}

public String getDoti() {
	return doti;
}

public void setDoti(String doti) {
	this.doti = doti;
}

public RapportDeEvaluation(Long id, Employe employe, GradeEmploye nouveauGrade, Double moyen, String mention,
		String remarques, List<PrixEmploye> prix, List<PunitionEmploye> punition,
		List<Formation> formation, List<NoteGeneralDeAnnee> noteGenerale) {
	super();
	this.id = id;
	this.employe = employe;
	this.nouveauGrade = nouveauGrade;
	this.moyen = moyen;
	this.mention = mention;
	this.remarques = remarques;
	this.prix = prix;
	this.punition = punition;
	this.formation = formation;
	this.noteGenerale = noteGenerale;
}

@Override
public String toString() {
	return "RapportDeEvaluation [id=" + id + ", employe=" + employe + ", nouveauGrade=" + nouveauGrade + ", moyen="
			+ moyen + ", mention=" + mention + ", remarques=" + remarques + ", doti=" + doti + ", prix=" + prix
			+ ", punition=" + punition + ", formation=" + formation + ", noteGenerale=" + noteGenerale + "]";
}




}
