package com.series.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.converter.SeriesConverter;
import com.series.dto.SeriesDto;
import com.series.exception.ResourceNotFoundException;
import com.series.model.Genre;
import com.series.model.Series;
import com.series.repository.GenreRepository;
import com.series.repository.SeriesRepository;

@Service
public class SeriesServiceImpl implements SeriesService {
	
	@Autowired
	SeriesConverter seriesConverter;
	
	@Autowired
	SeriesRepository seriesRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
//	there is also JpaRepository method that receives Sort object
//  Probably all the methods in this service should return ordred lists...	
	
	@Override
	public List<SeriesDto> getAllSeries() {
		List<Series> series = seriesRepository.findAll();
		
		return seriesConverter.createFromEntities(series);
	}
	
//	How to rely on implicit implementing of 'findBy...' JpaRepo interface methods and simultaneously be able to to throw custom exception?
//	Should 'throws' ...Exception be part of method definition here? 
	@Override
	public SeriesDto findById(Long seriesId) throws ResourceNotFoundException {
		Series series = seriesRepository.findById(seriesId)
				.orElseThrow(() -> new ResourceNotFoundException("Series", "id", seriesId));
		
		return seriesConverter.createFromEntity(series);
	}

//	Should I return an exception here if no series with a givent title is found or should I just return an empty list?
	@Override
	public List<SeriesDto> findByTitle(String title) {
		
		List<Series> series = seriesRepository.findByTitle(title);
			
		return seriesConverter.createFromEntities(series);
	}

//	TODO handle exceptions here
//	Igor said that I shouldn't call another service from the inside of a service
//	but where should I handle exception if no genre with a given genre name is found
	@Override
	public List<SeriesDto> findByGenre(String genreName) {
		
		Genre genre = genreRepository.findOneByNameIgnoreCase(genreName);
		List<Series> series = seriesRepository.findByGenre(genre);
		
		return seriesConverter.createFromEntities(series);
	}

//	TODO handle exceptions in create/update series methods
//	TODO those two methods have duplicate code which could be put into the separate method together with exception handling that corresponds to specific value assigning
	@Override
	public Series saveSeries(SeriesDto seriesDto) {
		
		Series series = seriesConverter.createFromDto(seriesDto);
		
		return seriesRepository.save(series);
	}

	@Override
	public Series updateSeries(Long seriesId, SeriesDto seriesDto) {
		
		Series series = seriesRepository.getOne(seriesId);
		seriesConverter.updateFromDto(series, seriesDto);

		return seriesRepository.save(series);
	}
	
}
