package com.series.converter;

import java.util.Set;

import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;

public interface SeriesRatingConverter {
	
	SeriesRating createFromDto(SeriesRatingDto dto, User user, Series series);
	
	Set<SeriesRatingDto> createFromEntities(Set<SeriesRating> seriesRatings);
}
