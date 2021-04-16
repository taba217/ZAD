package com.taba217.zad.models;

public class LectureSeriesItem{
	private int lectureId;
	private String size;
	private String updatedAt;
	private String name;
	private String createdAt;
	private int id;
	private String url;
	private String status;

	public LectureSeriesItem(int lectureId, String size, String updatedAt, String name, String createdAt, int id, String url, String status) {
		this.lectureId = lectureId;
		this.size = size;
		this.updatedAt = updatedAt;
		this.name = name;
		this.createdAt = createdAt;
		this.id = id;
		this.url = url;
		this.status = status;
	}

	public int getLectureId(){
		return lectureId;
	}

	public String getSize(){
		return size;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}

	public String getStatus(){
		return status;
	}
}
