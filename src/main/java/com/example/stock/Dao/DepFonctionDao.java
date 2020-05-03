package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.DepFonction;

@Repository
public interface DepFonctionDao extends JpaRepository<DepFonction, Long> {
List<DepFonction> findByDepartemantNom(String nomDepartemant);
List<DepFonction> findByFonctionLibelle(String libelle);
}
