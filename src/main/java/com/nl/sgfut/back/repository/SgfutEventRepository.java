package com.nl.sgfut.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nl.sgfut.back.model.SgfutEvent;

@Repository
public interface SgfutEventRepository extends JpaRepository<SgfutEvent, Integer> {
	List<SgfutEvent> findByName(Integer id);
}