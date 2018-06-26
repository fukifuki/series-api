package com.series.converter;

import java.util.List;

import com.series.dto.SeriesDto;
import com.series.model.Series;

public interface SeriesConverter {
	
	Series createFromDto(SeriesDto dto);
	
	Series updateFromDto(Series series, SeriesDto dto);
	
	SeriesDto createFromEntity(Series series);

//	I don't need this method, at least for now
//	List<Series> createFromDtos(Set<SeriesDto> dtos);
	
//	TODO Be more consistent when it comes to returning types of lists of resources...
	List<SeriesDto> createFromEntities(List<Series> series);
}
