package com.example.stock.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stock.bean.TypeCongee;
import com.example.stock.bean.TypeDocument;

@Repository
public interface TypeDocumentDao extends JpaRepository<TypeDocument, Long> {
TypeDocument findByLibelle(String type);

}
