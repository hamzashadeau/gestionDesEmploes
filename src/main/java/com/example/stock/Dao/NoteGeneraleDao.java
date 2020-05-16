package com.example.stock.Dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.NoteGeneralDeAnnee;

@Repository
public interface NoteGeneraleDao extends JpaRepository<NoteGeneralDeAnnee, Long> {
List<NoteGeneralDeAnnee> findByEmployeId(Long id);
List<NoteGeneralDeAnnee> findByEmployeEmail(String email);
List<NoteGeneralDeAnnee> findByEmployeDoti(Integer doti);
NoteGeneralDeAnnee findByDateAndEmployeDoti(Date date,Integer doti);
List<NoteGeneralDeAnnee> findByEtat(String etat);
}
