package com.series.converter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;
import com.series.model.UserSeriesId;


@Component
public class SeriesRatingConverterImpl implements SeriesRatingConverter {
	
	@Override
	public SeriesRating createFromDto(SeriesRatingDto dto, User user, Series series) {
		SeriesRating seriesRating = new SeriesRating();
		UserSeriesId primaryKey = new UserSeriesId();
		
		primaryKey.setUser(user);
		primaryKey.setSeries(series);
		seriesRating.setPrimaryKey(primaryKey);
		seriesRating.setRating(dto.getRating());
		
		return seriesRating;
	}
	
	public Set<SeriesRatingDto> createFromEntities(Set<SeriesRating> seriesRatings) {
		Set<SeriesRatingDto> seriesRatingsDtos = new HashSet<SeriesRatingDto>();
		
		seriesRatings.forEach((seriesRating) -> { 
			SeriesRatingDto seriesRatingDto = new SeriesRatingDto();
			seriesRatingDto.setRating(seriesRating.getRating());
			seriesRatingsDtos.add(seriesRatingDto);
		});
		
		return seriesRatingsDtos;
	}
	
}
