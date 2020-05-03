package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.DepFonction;
import com.example.stock.bean.Emoluments;

@Repository
public interface EmolumentsDao extends JpaRepository<Emoluments, Long> {
List<Emoluments> findByMontant(Double montant);
List<Emoluments> findByLibelle(String libelle);
}
