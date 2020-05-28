package com.example.stock.Dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.Formation;
import com.example.stock.bean.PermanenceAdministrative;

@Repository
public interface FormationDao extends JpaRepository<Formation, Long> {
	List<Formation> findByEmployeId(Long id);
List<Formation> findByEmployeEmail(String email);
List<Formation> findByEmployeDoti(String doti);

}
