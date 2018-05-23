package com.series.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.converter.SeriesRatingConverter;
import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;
import com.series.model.UserSeriesId;
import com.series.repository.SeriesRatingRepository;
import com.series.repository.SeriesRepository;

@Service
public class SeriesRatingServiceImpl implements SeriesRatingService {

	@Autowired
	SeriesRatingConverter seriesRatingConverter;
	
	@Autowired
	SeriesRatingRepository seriesRatingRepository;
	
	@Autowired
	SeriesRepository seriesRepository;
	
	@Override
	public Set<SeriesRatingDto> getSeriesRatings(Series series) {
		
		Set<SeriesRating> seriesRatings = series.getSeriesRatings();
		
		return seriesRatingConverter.createFromEntities(seriesRatings);
	}
	
//	Change arguments order here!
	@Override
	public SeriesRating rateSeries(User user, Series series, SeriesRatingDto seriesRatingDto) {
		
		SeriesRating seriesRating = seriesRatingConverter.createFromDto(seriesRatingDto, user, series);
		
		return seriesRatingRepository.save(seriesRating);
	}

}
