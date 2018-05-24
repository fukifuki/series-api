package com.series.dto;

import javax.validation.constraints.NotBlank;

public class SeriesDto {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	private short yearStarted;

	@NotBlank
	private String genreName;
	
//	getters/setters
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
