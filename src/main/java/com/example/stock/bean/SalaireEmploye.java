package com.example.stock.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SalaireEmploye {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private Long id;
private Double salaireNet;
@ManyToOne
private Emoluments idemFamialieleMarocaine;
@ManyToOne
private Emoluments idemDeLaResidence;
@ManyToOne
private Emoluments allocationDeEncadrement;
@ManyToOne
private Emoluments allocationDeEnseignement;
@ManyToOne
private Revenu     mutuelleCaisseRetraitEtDeces;
@ManyToOne
private Revenu impotSurLeRevenu;
@ManyToOne
private Revenu assuranceMaladieObligatoire;
@ManyToOne
private Revenu caisseMarocaineDeretrait;
private Double monatntModifie;
@ManyToOne
private Employe employe;
public SalaireEmploye() {
	super();
	// TODO Auto-generated constructor stub
}
public SalaireEmploye(Double salaireNet, Emoluments idemFamialieleMarocaine, Emoluments idemDeLaResidence,
		Emoluments allocationDeEncadrement, Emoluments allocationDeEnseignement, Revenu mutuelleCaisseRetraitEtDeces,
		Revenu impotSurLeRevenu, Revenu assuranceMaladieObligatoire, Revenu caisseMarocaineDeretrait,
		Double monatntModifie, Employe employe) {
	super();
	this.salaireNet = salaireNet;
	this.idemFamialieleMarocaine = idemFamialieleMarocaine;
	this.idemDeLaResidence = idemDeLaResidence;
	this.allocationDeEncadrement = allocationDeEncadrement;
	this.allocationDeEnseignement = allocationDeEnseignement;
	this.mutuelleCaisseRetraitEtDeces = mutuelleCaisseRetraitEtDeces;
	this.impotSurLeRevenu = impotSurLeRevenu;
	this.assuranceMaladieObligatoire = assuranceMaladieObligatoire;
	this.caisseMarocaineDeretrait = caisseMarocaineDeretrait;
	this.monatntModifie = monatntModifie;
	this.employe = employe;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Double getSalaireNet() {
	return salaireNet;
}
public void setSalaireNet(Double salaireNet) {
	this.salaireNet = salaireNet;
}
public Emoluments getIdemFamialieleMarocaine() {
	return idemFamialieleMarocaine;
}
public void setIdemFamialieleMarocaine(Emoluments idemFamialieleMarocaine) {
	this.idemFamialieleMarocaine = idemFamialieleMarocaine;
}
public Emoluments getIdemDeLaResidence() {
	return idemDeLaResidence;
}
public void setIdemDeLaResidence(Emoluments idemDeLaResidence) {
	this.idemDeLaResidence = idemDeLaResidence;
}
public Emoluments getAllocationDeEncadrement() {
	return allocationDeEncadrement;
}
public void setAllocationDeEncadrement(Emoluments allocationDeEncadrement) {
	this.allocationDeEncadrement = allocationDeEncadrement;
}
public Emoluments getAllocationDeEnseignement() {
	return allocationDeEnseignement;
}
public void setAllocationDeEnseignement(Emoluments allocationDeEnseignement) {
	this.allocationDeEnseignement = allocationDeEnseignement;
}
public Revenu getMutuelleCaisseRetraitEtDeces() {
	return mutuelleCaisseRetraitEtDeces;
}
public void setMutuelleCaisseRetraitEtDeces(Revenu mutuelleCaisseRetraitEtDeces) {
	this.mutuelleCaisseRetraitEtDeces = mutuelleCaisseRetraitEtDeces;
}
public Revenu getImpotSurLeRevenu() {
	return impotSurLeRevenu;
}
public void setImpotSurLeRevenu(Revenu impotSurLeRevenu) {
	this.impotSurLeRevenu = impotSurLeRevenu;
}
public Revenu getAssuranceMaladieObligatoire() {
	return assuranceMaladieObligatoire;
}
public void setAssuranceMaladieObligatoire(Revenu assuranceMaladieObligatoire) {
	this.assuranceMaladieObligatoire = assuranceMaladieObligatoire;
}
public Revenu getCaisseMarocaineDeretrait() {
	return caisseMarocaineDeretrait;
}
public void setCaisseMarocaineDeretrait(Revenu caisseMarocaineDeretrait) {
	this.caisseMarocaineDeretrait = caisseMarocaineDeretrait;
}
public Double getMonatntModifie() {
	return monatntModifie;
}
public void setMonatntModifie(Double monatntModifie) {
	this.monatntModifie = monatntModifie;
}
public Employe getEmploye() {
	return employe;
}
public void setEmploye(Employe employe) {
	this.employe = employe;
}
@Override
public String toString() {
	return "SalaireEmploye [id=" + id + ", salaireNet=" + salaireNet + ", idemFamialieleMarocaine="
			+ idemFamialieleMarocaine + ", idemDeLaResidence=" + idemDeLaResidence + ", allocationDeEncadrement="
			+ allocationDeEncadrement + ", allocationDeEnseignement=" + allocationDeEnseignement
			+ ", mutuelleCaisseRetraitEtDeces=" + mutuelleCaisseRetraitEtDeces + ", impotSurLeRevenu="
			+ impotSurLeRevenu + ", assuranceMaladieObligatoire=" + assuranceMaladieObligatoire
			+ ", caisseMarocaineDeretrait=" + caisseMarocaineDeretrait + ", monatntModifie=" + monatntModifie
			+ ", employe=" + employe + "]";
}


}
