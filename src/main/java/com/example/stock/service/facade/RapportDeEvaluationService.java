package com.example.stock.service.facade;

import java.util.Date;
import java.util.List;

import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.bean.RapportDeEvaluation;

public interface RapportDeEvaluationService {
	RapportDeEvaluation findByid(Long id);
	List<RapportDeEvaluation> findByEmployeEmail(String email);
	List<RapportDeEvaluation> findByNouveauGradeId(Long id);
	List<RapportDeEvaluation> findByEmployeDoti(Integer doti);
	List<RapportDeEvaluation> findByEmployeId(Long id);
	List<RapportDeEvaluation> findAll();
	int save(RapportDeEvaluation rapportDeEvaluation);
	int deleteById(Long id);

}
