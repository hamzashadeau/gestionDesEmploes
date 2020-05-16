package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Departement;

public interface DepartementService {
	Departement findByid(Long id);
	Departement findByNom(String nom);
	Departement findByChefdoti(Integer doti);
	List<Departement> findAll();
	int save(Departement departement);
	int deleteById(Long id);
	int nombreDesDepartements();
}
