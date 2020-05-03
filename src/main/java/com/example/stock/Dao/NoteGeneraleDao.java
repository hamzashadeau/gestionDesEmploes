package com.example.stock.Dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.NoteGeneralDeAnnee;

@Repository
public interface NoteGeneraleDao extends JpaRepository<NoteGeneralDeAnnee, Long> {
List<NoteGeneralDeAnnee> findByEmployéId(Long id);
List<NoteGeneralDeAnnee> findByEmployéEmail(String email);
List<NoteGeneralDeAnnee> findByEmployéDoti(Integer doti);
List<NoteGeneralDeAnnee> findByDate(Date date);
}
