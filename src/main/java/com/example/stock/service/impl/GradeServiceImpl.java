package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.GradeDao;
import com.example.stock.bean.Grade;
import com.example.stock.service.facade.GradeService;

@Service
public class GradeServiceImpl implements GradeService {
@Autowired
private GradeDao gradeDao;


@Override
public int save(Grade grade) {
	if(grade.getId() != null) {
return -1;
}else {
	gradeDao.save(grade);
		return 1;
}
	}

@Override
public Grade findByid(Long id) {
	if (gradeDao.findById(id).isPresent()) {
		return gradeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	gradeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<Grade> findAll() {
	return gradeDao.findAll();
}

@Override
public Grade findByLibelle(String libelle) {
	return gradeDao.findByLibelle(libelle);
}


}
