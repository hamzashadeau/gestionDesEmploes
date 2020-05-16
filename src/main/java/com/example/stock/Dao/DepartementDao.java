package com.example.stock.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Departement;

@Repository
public interface DepartementDao extends JpaRepository<Departement, Long> {
Departement findByNom(String nom);
Departement findByChefdoti(Integer matricule);
}
