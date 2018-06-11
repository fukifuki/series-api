package com.series.dto;

public class SeriesDto {
	
	private Long id;
	private String title;
	private String description;
	private short yearStarted;
	private String genreName;
	
//	getters/setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getYearStarted() {
		return yearStarted;
	}

	public void setYearStarted(short yearStarted) {
		this.yearStarted = yearStarted;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
}
