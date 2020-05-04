package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.NoteEmployeDao;
import com.example.stock.bean.Note;
import com.example.stock.bean.NoteEmploye;
import com.example.stock.service.facade.NoteEmployeService;

@Service
public class NoteEmployeServiceImpl implements NoteEmployeService {
@Autowired
private NoteEmployeDao noteEmployeDao;


@Override
public int save(NoteEmploye noteEmploye) {
	if(findByid(noteEmploye.getId())!= null) {
return -1;
}else {
	noteEmployeDao.save(noteEmploye);
		return 1;
}
	}

@Override
public NoteEmploye findByid(Long id) {
	if (noteEmployeDao.findById(id).isPresent()) {
		return noteEmployeDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	noteEmployeDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}

@Override
public List<NoteEmploye> findByEmployeIdAndNoteGeneralDeAnneeId(Long id1, Long id2) {
	return noteEmployeDao.findByEmployeIdAndNoteGeneralDeAnneeId(id1, id2);
}

@Override
public List<NoteEmploye> findAll() {
	return noteEmployeDao.findAll();
}




}
