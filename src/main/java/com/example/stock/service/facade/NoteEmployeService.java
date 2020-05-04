package com.example.stock.service.facade;

import java.util.List;

import com.example.stock.bean.Note;
import com.example.stock.bean.NoteEmploye;

public interface NoteEmployeService {
	
	List<NoteEmploye> findByEmployeIdAndNoteGeneralDeAnneeId(Long id1,Long id2);

	List<NoteEmploye> findAll();

	int save(NoteEmploye noteEmploye);

	int deleteById(Long id);

	public NoteEmploye findByid(Long id);

}
