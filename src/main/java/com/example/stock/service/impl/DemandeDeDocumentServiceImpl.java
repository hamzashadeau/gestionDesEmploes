package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.DemaneDeDocumentDao;
import com.example.stock.bean.DemaneDeDocument;
import com.example.stock.service.facade.DemandeDeDocumentService;

@Service
public class DemandeDeDocumentServiceImpl implements DemandeDeDocumentService {
@Autowired
private DemaneDeDocumentDao demaneDeDocumentDao;


@Override
public int save(DemaneDeDocument demaneDeDocument) {
	if(findByid(demaneDeDocument.getId())!= null) {
return -1;
}else {
	demaneDeDocumentDao.save(demaneDeDocument);
		return 1;
}
	}

@Override
public DemaneDeDocument findByid(Long id) {
	if (demaneDeDocumentDao.findById(id).isPresent()) {
		return demaneDeDocumentDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	demaneDeDocumentDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<DemaneDeDocument> findAll() {
	return demaneDeDocumentDao.findAll();
}

@Override
public List<DemaneDeDocument> findByEmployeId(Long id) {
	return demaneDeDocumentDao.findByEmployeId(id);
}

@Override
public List<DemaneDeDocument> findByEmployeEmail(String email) {
	return demaneDeDocumentDao.findByEmployeEmail(email);
}

@Override
public List<DemaneDeDocument> findByEmployeDoti(Integer doti) {
	return demaneDeDocumentDao.findByEmployeDoti(doti);
}

@Override
public List<DemaneDeDocument> findByEtat(String etat) {
	return demaneDeDocumentDao.findByEtat(etat);
}


}
