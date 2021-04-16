package com.taba217.zad.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LectureItem{
	private Object image;
	@SerializedName("lecture_series")
	private List<LectureSeriesItem> lectureSeries;
	private String size;
	private Object updatedAt;
	private int typeId;
	private String name;
	private Object createdAt;
	private int id;
	private int lecturerId;
	private String url;
	private String status;

	public Object getImage(){
		return image;
	}

	public List<LectureSeriesItem> getLectureSeries(){
		return lectureSeries;
	}

	public String getSize(){
		return size;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public int getTypeId(){
		return typeId;
	}

	public String getName(){
		return name;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public int getLecturerId(){
		return lecturerId;
	}

	public String getUrl(){
		return url;
	}

	public String getStatus(){
		return status;
	}
}