package com.series.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;
import com.series.model.UserSeriesId;
import com.series.repository.SeriesRatingRepository;

@Service
public class SeriesRatingServiceImpl implements SeriesRatingService {

	@Autowired
	SeriesRatingRepository seriesRatingRepository;
	
	@Override
	public SeriesRating rateSeries(User user, Series series, SeriesRatingDto seriesRatingDto) {
		SeriesRating seriesRating = new SeriesRating();
		UserSeriesId primaryKey = new UserSeriesId();
		primaryKey.setUser(user);
		primaryKey.setSeries(series);
		seriesRating.setPrimaryKey(primaryKey);
		seriesRating.setRating(seriesRatingDto.getRating());
		
		return seriesRatingRepository.save(seriesRating);
	}

}
