package com.series.service;

import java.util.List;

import com.series.dto.SeriesDto;
import com.series.model.Series;

public interface SeriesService {
	
//	TODO List or Set?
	List<Series> getAllSeries();
		
	Series findById(Long seriesId);

	List<Series> findByTitle(String title);
	
	List<Series> findByGenre(String genre);
	
	Series saveSeries(SeriesDto seriesDto);
	
	Series updateSeries(Long seriesId, SeriesDto seriesDto);
	
//	TODO findNewerThen
//	TODO findOlderThen
}
