package com.nl.sgfut.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nl.sgfut.back.model.SgfutUser;

@Repository
public interface SgfutUserRepository extends JpaRepository<SgfutUser, Integer> {
	List<SgfutUser> findByName(String name);
}