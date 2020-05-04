package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Note;
import com.example.stock.bean.NoteEmploye;
import com.example.stock.bean.Prix;

@Repository
public interface NoteEmployeDao extends JpaRepository<NoteEmploye, Long> {
List<NoteEmploye> findByEmployeIdAndNoteGeneralDeAnneeId(Long id1,Long id2);

}
