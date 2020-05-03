package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Prix;

public interface PrixService {
	Prix findByid(Long id);
	List<Prix> findByLibelle(String libelle);
	List<Prix> findAll();
	int save(Prix prix);
	int deleteById(Long id);

}
