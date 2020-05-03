package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TypeCongee {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String libelle;
public TypeCongee() {
	super();
	// TODO Auto-generated constructor stub
}
public TypeCongee(String type) {
	super();
	this.libelle = type;
}
@Override
public String toString() {
	return "Congee [id=" + id + ", type=" + libelle + "]";
}
public String getType() {
	return libelle;
}
public void setType(String type) {
	this.libelle = type;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}



}
