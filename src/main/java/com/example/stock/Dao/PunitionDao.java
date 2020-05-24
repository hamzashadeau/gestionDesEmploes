package com.example.stock.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Punition;

@Repository
public interface PunitionDao extends JpaRepository<Punition, Long> {
List<Punition> findByType(String type);
Punition findByLibelle(String libelle);

}
