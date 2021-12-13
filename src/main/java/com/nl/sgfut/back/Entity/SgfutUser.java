package com.nl.sgfut.back.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sgfutuser")
public class SgfutUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String password;

	private Integer hosted;

	private Timestamp registered;

	public Integer getUserId() {
		return id;
	}

	public void setUserId(Integer user_id) {
		this.id = user_id;
	}

	public String getUserName() {
		return name;
	}

	public void setUserName(String user_name) {
		this.name = user_name;
	}

	public String getUserPass() {
		return password;
	}

	public void setUserPass(String user_pass) {
		this.password = user_pass;
	}

	public Integer getEventHosted() {
		return hosted;
	}

	public void setEventHosted(Integer event_hosted) {
		this.hosted = event_hosted;
	}

	public Timestamp getRegisteredDt() {
		return registered;
	}

	public void setRegisteredDt(Timestamp registered_dt) {
		this.registered = registered_dt;
	}
}