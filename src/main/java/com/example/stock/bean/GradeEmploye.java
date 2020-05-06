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
@JsonProperty(access = Access.WRITE_ONLY)
@ManyToOne()
private Employe employe;
@ManyToOne()
private Grade grade;
@Temporal(TemporalType.DATE)
private Date dateDeAffectation;
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
@Override
public String toString() {
	return "GradeEmploye [id=" + id + ", employe=" + employe + ", grade=" + grade + ", dateDeAffectation="
			+ dateDeAffectation + "]";
}
public GradeEmploye(Employe employe, Grade grade, Date dateDeAffectation) {
	super();
	this.employe = employe;
	this.grade = grade;
	this.dateDeAffectation = dateDeAffectation;
}
public Grade getGrade() {
	return grade;
}
public void setGrade(Grade grade) {
	this.grade = grade;
}

}
