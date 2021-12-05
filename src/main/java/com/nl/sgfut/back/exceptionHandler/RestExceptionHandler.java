package com.nl.sgfut.back.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nl.sgfut.back.mapper.JsonMapper;

/**
 * RestController横断での例外ハンドラ
 * 
 * @author Administrator
 *
 */
@RestControllerAdvice
public class RestExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler
	private ResponseEntity<String> onError(Exception ex) {
		log.error(ex.getMessage(), ex);

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String json = JsonMapper.map().put("message", "API エラー").put("detail", ex.getMessage())
				.put("status", status.value()).stringify();

		return new ResponseEntity<String>(json, status);
	}
}
