package com.example.stock.Dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.GradeEmploye;

@Repository
public interface GradeEmployeDao extends JpaRepository<GradeEmploye, Long> {
List<GradeEmploye> findByEmployeId(Long id);
List<GradeEmploye> findByEmployeEmail(String email);
List<GradeEmploye> findByEmployeDoti(Integer doti);
List<GradeEmploye> findByDateDeAffectation(Date dateAfectation);
}
