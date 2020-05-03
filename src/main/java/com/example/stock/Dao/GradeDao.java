package com.example.stock.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Grade;

@Repository
public interface GradeDao extends JpaRepository<Grade, Long> {
Grade findByLibelle(String libelle);

}
