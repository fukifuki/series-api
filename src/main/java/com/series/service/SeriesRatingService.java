package com.series.service;

import java.util.Set;

import com.series.dto.SeriesRatingDto;
import com.series.model.SeriesRating;
import com.series.model.User;

public interface SeriesRatingService {
	
//	Should this method expect Series or seriesId argument
//	Actually, what I am not sure about is if I should user SeriesService even inside SeriesRatingController
	Set<SeriesRatingDto> getSeriesRatings(Long seriesId);
	
	SeriesRating rateSeries(Long userId, Long seriesId, SeriesRatingDto seriesRatingDto);

}
