package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.CongeEmployeSalaireDao;
import com.example.stock.bean.CongéEmployeSalaire;
import com.example.stock.service.facade.CongeEmployeService;

@Service
public class CongeEmployeSalaireImpl implements CongeEmployeService {
@Autowired
private CongeEmployeSalaireDao congeEmployeSalaireDao; 
	@Override
	public List<CongéEmployeSalaire> findByCongeId(Long id) {
		return congeEmployeSalaireDao.findByCongeId(id);
	}

	@Override
	public CongéEmployeSalaire findByid(Long id) {
		if (congeEmployeSalaireDao.findById(id).isPresent()) {
			return congeEmployeSalaireDao.findById(id).get();
		} else
			return null;
	}

	@Override
	public List<CongéEmployeSalaire> findAll() {
		return congeEmployeSalaireDao.findAll();
	}

	@Override
	public int save(CongéEmployeSalaire congéEmployeSalaire) {
		congeEmployeSalaireDao.save(congéEmployeSalaire);
		return 1;
	}

	@Override
	public int deleteById(Long id) {
congeEmployeSalaireDao.deleteById(id);
		return 1;
	}
}
