package com.nl.sgfut.back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nl.sgfut.back.model.SgfutEvent;
import com.nl.sgfut.back.repository.SgfutEventRepository;

@Service
@Transactional
public class SgfutEventService {

	@Autowired
	SgfutEventRepository sgfutEventRepository;

	/**
	 * レコードを全件取得する。
	 * 
	 * @return
	 */
	public List<SgfutEvent> findAllSgfutEventData() {

		return sgfutEventRepository.findAll();
	}

	/**
	 * 指定したイベントIDのレコードを取得する。
	 * 
	 * @param name
	 * @return
	 */
	public List<SgfutEvent> findSgfutEventDataListByEventId(int id) {

		return sgfutEventRepository.findByName(id);
	}
}