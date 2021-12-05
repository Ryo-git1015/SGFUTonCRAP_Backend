package com.nl.sgfut.back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nl.sgfut.back.model.SgfutUser;
import com.nl.sgfut.back.repository.SgfutUserRepository;

@Service
@Transactional
public class SgfutUserService {

	@Autowired
	SgfutUserRepository sgfutUserRepository;

	/**
	 * レコードを全件取得する。
	 * 
	 * @return
	 */
	public List<SgfutUser> findAllSgfutUserData() {

		return sgfutUserRepository.findAll();
	}

	/**
	 * 指定したユーザー名のレコードを取得する。
	 * 
	 * @param user_name
	 * @return
	 */
	public List<SgfutUser> findSgfutUserDataListByName(String name) {

		return sgfutUserRepository.findByName(name);
	}
}