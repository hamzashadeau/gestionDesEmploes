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
RapportDeEvaluation findByNouveauGradeIdAndEmployeDoti(Long id,String doti);
List<RapportDeEvaluation> findByEmployeDoti(String doti);
List<RapportDeEvaluation> findByEmployeId(Long id);
List<RapportDeEvaluation> findByNouveauGradeId(Long id);

}
