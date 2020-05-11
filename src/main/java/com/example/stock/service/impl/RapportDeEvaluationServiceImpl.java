package com.example.stock.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.RapportDeEvaluationDao;
import com.example.stock.bean.PermanenceAdministrative;
import com.example.stock.bean.RapportDeEvaluation;
import com.example.stock.service.facade.RapportDeEvaluationService;

@Service
public class RapportDeEvaluationServiceImpl implements RapportDeEvaluationService {
@Autowired
private RapportDeEvaluationDao rapportDeEvaluationDao;


@Override
public int save(RapportDeEvaluation rapportDeEvaluation) {
	if(findByid(rapportDeEvaluation.getId())!= null) {
return -1;
}else {
	rapportDeEvaluationDao.save(rapportDeEvaluation);
		return 1;
}
	}

@Override
public RapportDeEvaluation findByid(Long id) {
	if (rapportDeEvaluationDao.findById(id).isPresent()) {
		return rapportDeEvaluationDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	rapportDeEvaluationDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<RapportDeEvaluation> findAll() {
	return rapportDeEvaluationDao.findAll();
}

@Override
public List<RapportDeEvaluation> findByEmployeEmail(String email) {
	return rapportDeEvaluationDao.findByEmployeEmail(email);
}


@Override
public List<RapportDeEvaluation> findByEmployeDoti(Integer doti) {
	return rapportDeEvaluationDao.findByEmployeDoti(doti);
}

@Override
public List<RapportDeEvaluation> findByEmployeId(Long id) {
	return rapportDeEvaluationDao.findByEmployeId(id);
}

@Override
public List<RapportDeEvaluation> findByNouveauGradeId(Long id) {
	return rapportDeEvaluationDao.findByNouveauGradeId(id);
}

@Override
public RapportDeEvaluation findByNouveauGradeIdAndEmployeDoti(Long id, Integer doti) {
	return rapportDeEvaluationDao.findByNouveauGradeIdAndEmployeDoti(id, doti);
}





}
