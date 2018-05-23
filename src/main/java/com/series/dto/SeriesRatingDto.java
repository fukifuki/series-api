package com.series.dto;

import org.hibernate.validator.constraints.Range;

public class SeriesRatingDto {
	
	@Range(min = 1, max = 5)
	private int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
