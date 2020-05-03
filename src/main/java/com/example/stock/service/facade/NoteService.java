package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Note;

public interface NoteService {
	List<Note> findByMention(Double metion);


	List<Note> findAll();

	int save(Note congé);

	int deleteById(Long id);

	public Note findByid(Long id);

}
