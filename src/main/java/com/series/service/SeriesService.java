package com.series.service;

import java.util.List;

import com.series.dto.SeriesDto;
import com.series.web.util.SearchCriterion;

public interface SeriesService {
	
//	TODO List or Set?
	List<SeriesDto> getAllSeries(List<SearchCriterion> searchCriteria);
		
	SeriesDto findById(Long seriesId);

	List<SeriesDto> findByTitle(String title);
	
	List<SeriesDto> findByGenre(String genre);
	
	SeriesDto saveSeries(SeriesDto seriesDto);
	
	SeriesDto updateSeries(Long seriesId, SeriesDto seriesDto);
	
//	TODO findNewerThen
//	TODO findOlderThen
//	TODO findTopRated
}
