package com.example.stock.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.PrixEmploye;

@Repository
public interface PrixEmployeDao extends JpaRepository<PrixEmploye, Long> {
List<PrixEmploye> findByEmployeEmail(String email);
List<PrixEmploye> findByPrixLibelle(String libelle);

}
