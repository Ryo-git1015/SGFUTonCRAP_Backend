package com.nl.sgfut.back.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class SgfutEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String summary;

	private String tag1;

	private String tag2;

	private String tag3;

	private Integer crap;

	private Timestamp registered;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventSummary() {
		return summary;
	}

	public void setEventSummary(String summary) {
		this.summary = summary;
	}

	public String getTag1() {
		return tag1;
	}

	public void setEventTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setEventTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getTag3() {
		return tag3;
	}

	public void setEventTag3(String tag3) {
		this.tag3 = tag3;
	}

	public Integer getCrapTotal() {
		return crap;
	}

	public void setCrapTotal(Integer crap) {
		this.crap = crap;
	}

	public Timestamp getRegisteredDt() {
		return registered;
	}

	public void setRegisteredDt(Timestamp registered) {
		this.registered = registered;
	}
}