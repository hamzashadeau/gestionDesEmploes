package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.TypeCongee;

public interface TypeCongeeService {
	TypeCongee findByid(Long id);
	TypeCongee findByLibelle(String type);
	List<TypeCongee> findAll();
	int save(TypeCongee employe);
	int deleteById(Long id);

}
