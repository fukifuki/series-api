package com.series.service;

import java.util.List;

import com.series.dto.SeriesDto;
import com.series.model.Series;

public interface SeriesService {
	
//	TODO List or Set?
	List<SeriesDto> getAllSeries();
		
	SeriesDto findById(Long seriesId);

	List<SeriesDto> findByTitle(String title);
	
	List<SeriesDto> findByGenre(String genre);
	
	Series saveSeries(SeriesDto seriesDto);
	
	Series updateSeries(Long seriesId, SeriesDto seriesDto);
	
//	TODO findNewerThen
//	TODO findOlderThen
}
