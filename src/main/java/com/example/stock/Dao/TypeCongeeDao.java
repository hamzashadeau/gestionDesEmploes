package com.example.stock.Dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.TypeCongee;
import com.example.stock.bean.Employe;

@Repository
public interface TypeCongeeDao extends JpaRepository<TypeCongee, Long> {
TypeCongee findByLibelle(String type);

}
