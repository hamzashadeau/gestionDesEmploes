package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Employe;
import com.example.stock.bean.Formation;

public interface FormationService {
	List<Formation> findByemployéId(Long id);

	List<Formation> findByemployéEmail(String email);

	List<Formation> findByemployéDoti(Integer doti);

	List<Formation> findAll();

	int save(Formation congé);

	int deleteById(Long id);

	public Formation findByid(Long id);
	public List<Formation> findFormationDeEmploye(Employe employe);

}
