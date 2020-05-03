package com.example.stock.service.facade;

import java.util.Date;
import java.util.List;

import com.example.stock.bean.GradeEmploye;

public interface GradeEmployeService {
	List<GradeEmploye> findByEmployeid(Long id);

	List<GradeEmploye> findByEmployeEmail(String email);

	List<GradeEmploye> findByEmployeDoti(Integer doti);

	List<GradeEmploye> findByDateDeAffectation(Date dateAfectation);

	List<GradeEmploye> findAll();

	int save(GradeEmploye gradeEmploye);

	int deleteById(Long id);

	public GradeEmploye findByid(Long id);

}
