package com.example.stock.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
public class GradeEmploye {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private Integer doti;
@ManyToOne
private Grade grade;
@Temporal(TemporalType.DATE)
private Date dateDeAffectation;
private String etat;
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Date getDateDeAffectation() {
	return dateDeAffectation;
}
public void setDateDeAffectation(Date dateDeAffectation) {
	this.dateDeAffectation = dateDeAffectation;
}
public GradeEmploye() {
	super();
	// TODO Auto-generated constructor stub
}

public Grade getGrade() {
	return grade;
}
public void setGrade(Grade grade) {
	this.grade = grade;
}

public String getEtat() {
	return etat;
}

public void setEtat(String etat) {
	this.etat = etat;
}

public Integer getDoti() {
	return doti;
}

public void setDoti(Integer doti) {
	this.doti = doti;
}

public GradeEmploye(Long id, Integer doti, Grade grade, Date dateDeAffectation, String etat) {
	super();
	this.id = id;
	this.doti = doti;
	this.grade = grade;
	this.dateDeAffectation = dateDeAffectation;
	this.etat = etat;
}

@Override
public String toString() {
	return "GradeEmploye [id=" + id + ", doti=" + doti + ", grade=" + grade + ", dateDeAffectation=" + dateDeAffectation
			+ ", etat=" + etat + "]";
}




}
