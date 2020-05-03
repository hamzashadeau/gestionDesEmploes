package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Grade;

public interface GradeService {
	Grade findByLibelle(String libelle);

	List<Grade> findAll();

	int save(Grade congé);

	int deleteById(Long id);

	public Grade findByid(Long id);

}
