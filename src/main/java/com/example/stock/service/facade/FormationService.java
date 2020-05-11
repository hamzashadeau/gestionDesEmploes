package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;

public interface FormationService {
	List<Formation> findByemployeId(Long id);

	List<Formation> findByemployeEmail(String email);

	List<Formation> findByemployeDoti(Integer doti);

	List<Formation> findAll();

	int save(Formation congé);

	int deleteById(Long id);

	public Formation findByid(Long id);
	public List<Formation> findFormationDeEmploye(Employe employe);

}
