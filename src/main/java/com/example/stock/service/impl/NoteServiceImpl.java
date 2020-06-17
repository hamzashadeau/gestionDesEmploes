package com.example.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.Dao.NoteDao;
import com.example.stock.bean.Note;
import com.example.stock.service.facade.NoteService;

@Service
public class NoteServiceImpl implements NoteService {
@Autowired
private NoteDao noteDao;


@Override
public int save(Note note) {
//	if(findByid(note.getId())!= null) {
//return -1;
//}else {
	noteDao.save(note);
		return 1;
}
	//}

@Override
public Note findByid(Long id) {
	if (noteDao.findById(id).isPresent()) {
		return noteDao.findById(id).get();
	} else
		return null;
}

@Override
public int deleteById(Long id) {
	noteDao.deleteById(id);
	if (findByid(id) == null) {
		return 1;
	} else
		return -1;
}


@Override
public List<Note> findAll() {
	return noteDao.findAll();
}

@Override
public List<Note> findByMention(Double metion) {
	return noteDao.findByMention(metion);
}




}
