package com.taba217.zad.models;

import java.util.List;

public class Lecturer{
	private Object image;
	private Object updatedAt;
	private String name;
	private Object createdAt;
	private List<LectureItem> lecture;
	private int id;
	private String status;

	public Object getImage(){
		return image;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public List<LectureItem> getLecture(){
		return lecture;
	}

	public int getId(){
		return id;
	}

	public String getStatus(){
		return status;
	}
}