package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Departement;

public interface DepartementService {
	Departement findByid(Long id);
	Departement findByNom(String nom);
	Departement findByChefEmail(String email);
	Departement findByChefDoti(Integer doti);
	List<Departement> findAll();
	int save(Departement departement);
	int deleteById(Long id);
	int nombreDesDepartements();
}
