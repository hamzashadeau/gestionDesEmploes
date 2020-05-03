package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.PunitionEmploye;

@Repository
public interface PunitionEmployeDao extends JpaRepository<PunitionEmploye, Long> {
List<PunitionEmploye> findByEmployeEmail(String email);
List<PunitionEmploye> findByPunitionLibelle(String libelle);
List<PunitionEmploye> findByPunitionType(String type);
List<PunitionEmploye> findByEmployeDoti(Integer doti);
List<PunitionEmploye> findByEmployeId(Long id);
}
