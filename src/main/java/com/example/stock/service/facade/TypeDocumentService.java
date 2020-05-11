package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.TypeDocument;

public interface TypeDocumentService {
	TypeDocument findByid(Long id);
	TypeDocument findByLibelle(String type);
	List<TypeDocument> findAll();
	int save(TypeDocument employe);
	int deleteById(Long id);

}
