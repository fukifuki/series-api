package com.series.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.converter.SeriesRatingConverter;
import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;
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
	public Set<SeriesRatingDto> getSeriesRatings(Long seriesId) {
		
//		TODO handle exception here
//		     or rather move series finding into separate private method (because it's called more then once) and handle exception there
		Series series = seriesRepository.getOne(seriesId);
		Set<SeriesRating> seriesRatings = series.getSeriesRatings();
		
		return seriesRatingConverter.createFromEntities(seriesRatings);
	}
	
//	TODO Change arguments order here!
	@Override
	public SeriesRating rateSeries(User user, Long seriesId, SeriesRatingDto seriesRatingDto) {
		
//		TODO handle exception here
		Series series = seriesRepository.getOne(seriesId);
		SeriesRating seriesRating = seriesRatingConverter.createFromDto(seriesRatingDto, user, series);
		
		return seriesRatingRepository.save(seriesRating);
	}

}
