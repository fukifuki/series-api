package com.series.service;

import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;

public interface SeriesRatingService {
	SeriesRating rateSeries(User user, Series series, SeriesRatingDto seriesRatingDto);
}
