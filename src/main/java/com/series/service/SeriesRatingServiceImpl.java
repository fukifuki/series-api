package com.series.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;
import com.series.model.UserSeriesId;
import com.series.repository.SeriesRatingRepository;

public class SeriesRatingServiceImpl implements SeriesRatingService {

	@Autowired
	SeriesRatingRepository seriesRatingRepository;
	
	@Override
	public SeriesRating rateSeries(User user, Series series, int rating) {
		SeriesRating seriesRating = new SeriesRating();
		UserSeriesId primaryKey = new UserSeriesId();
		primaryKey.setUser(user);
		primaryKey.setSeries(series);
		seriesRating.setPrimaryKey(primaryKey);
		seriesRating.setRating(rating);
		
		return seriesRatingRepository.save(seriesRating);
	}

}
