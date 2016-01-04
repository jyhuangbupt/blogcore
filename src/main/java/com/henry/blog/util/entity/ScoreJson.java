package com.henry.blog.util.entity;

public class ScoreJson {
	private String index;
	private String desc;
	private String uuid;
	private String score;
	
	public ScoreJson(){
		
	}
	
	public ScoreJson(String index, String desc, String uuid) {
		this.index = index;
		this.desc = desc;
		this.uuid = uuid;
	}
	
	public ScoreJson(String index, String desc, String uuid, String score) {
		this.index = index;
		this.desc = desc;
		this.uuid = uuid;
		this.score = score;
	}
	
	public void set(String index, String desc, String uuid) {
		this.index = index;
		this.desc = desc;
		this.uuid = uuid;
	}
	
	public void set(String index, String desc, String uuid, String score) {
		this.index = index;
		this.desc = desc;
		this.uuid = uuid;
		this.score = score;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
