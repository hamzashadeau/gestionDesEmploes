package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.DepFonction;
import com.example.stock.bean.Emoluments;
import com.example.stock.bean.Revenu;

@Repository
public interface RevenuDao extends JpaRepository<Revenu, Long> {
List<Revenu> findByMontant(Double montant);
Revenu findByLibelle(String libelle);
}
