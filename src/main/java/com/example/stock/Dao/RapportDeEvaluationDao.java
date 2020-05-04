package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.PrixEmploye;
import com.example.stock.bean.RapportDeEvaluation;

@Repository
public interface RapportDeEvaluationDao extends JpaRepository<RapportDeEvaluation, Long> {
List<RapportDeEvaluation> findByEmployeEmail(String email);
List<RapportDeEvaluation> findByNouveauGradeId(Long id);
List<RapportDeEvaluation> findByEmployeDoti(Integer doti);
List<RapportDeEvaluation> findByEmployeId(Long id);

}
