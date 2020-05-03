package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private String type;//Type de notification

public Notification() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Notification(String type) {
	super();
	this.type = type;
}
@Override
public String toString() {
	return "Notification [id=" + id + ", type=" + type + "]";
}



}
