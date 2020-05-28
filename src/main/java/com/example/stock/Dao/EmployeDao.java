package com.example.stock.Dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Employe;

@Repository
public interface EmployeDao extends JpaRepository<Employe, Long> {
Employe findByCin(String cin);
Employe findByDoti(String doti);
Employe findByEmail(String email);
List<Employe> findByDernierGradeGradeLibelle(String libelle);
List<Employe> findBySupId(Long id);
List<Employe> findByDateAvancementPrevue(Date dateAvancementPrevue);
List<Employe> findByDepNom(String nomDepartement);
List<Employe> findByDateDeProchainNote(Date dateDeProchainNote);
List<Employe> findByDateProchainEvaluation(Date dateProchainEvaluation);
List<Employe> findBySoldeRestantesCongeExceptionnel(Integer soldeRestantesCongeExceptionnel);
}
