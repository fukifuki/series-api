package com.series.service;

import java.util.Set;

import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;

public interface SeriesRatingService {
	
//	Should this method expect Series or seriesId argument
//	Actually, what I am not sure about is if I should user SeriesService even inside SeriesRatingController
	Set<SeriesRatingDto> getSeriesRatings(Series series);
	
	SeriesRating rateSeries(User user, Series series, SeriesRatingDto seriesRatingDto);
}
