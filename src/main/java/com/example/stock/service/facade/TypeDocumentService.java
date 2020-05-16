package com.example.stock.service.facade;

import java.io.FileNotFoundException;
import java.util.List;

import com.example.stock.bean.TypeDocument;
import com.itextpdf.text.DocumentException;

public interface TypeDocumentService {
	TypeDocument findByid(Long id);
	TypeDocument findByLibelle(String type);
	List<TypeDocument> findAll();
	int save(TypeDocument employe);
	int deleteById(Long id);
	public int creeDocument(String titre,String body) throws DocumentException, FileNotFoundException;
}
