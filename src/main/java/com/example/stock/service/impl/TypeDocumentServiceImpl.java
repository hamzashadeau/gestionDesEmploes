package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.TypeDocumentDao;
import com.example.stock.bean.TypeDocument;
import com.example.stock.service.facade.TypeDocumentService;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService {
@Autowired
private TypeDocumentDao typeDocumentDao;


@Override
public int save(TypeDocument typeDocument) {
	if(findByid(typeDocument.getId())!= null) {
return -1;
}else {
	typeDocumentDao.save(typeDocument);
		return 1;
}
	}

@Override
public TypeDocument findByid(Long id) {
	if (typeDocumentDao.findById(id).isPresent()) {
		return typeDocumentDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	typeDocumentDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public TypeDocument findByLibelle(String type) {
	return typeDocumentDao.findByLibelle(type);
}

@Override
public List<TypeDocument> findAll() {
	return typeDocumentDao.findAll();
}




}
