package com.series.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.dto.SeriesDto;
import com.series.exception.ResourceNotFoundException;
import com.series.model.Genre;
import com.series.model.Series;
import com.series.repository.GenreRepository;
import com.series.repository.SeriesRepository;

@Service
public class SeriesServiceImpl implements SeriesService {
	
	@Autowired
	SeriesRepository seriesRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
//	there is also JpaRepository method that receives Sort object
//  Probably all the methods in this service should return ordred lists...	
	
	@Override
	public List<Series> getAllSeries() {
		return seriesRepository.findAll();
	}
	
//	How to rely on implicit implementing of 'findBy...' JpaRepo interface methods and simultaneously be able to to throw custom exception?
//	Should 'throws' ...Exception be part of method definition here? 
	@Override
	public Series findById(Long seriesId) throws ResourceNotFoundException {
		return seriesRepository.findById(seriesId)
				.orElseThrow(() -> new ResourceNotFoundException("Series", "id", seriesId));
	}

//	Should I return an exception here if no series with a givent title is found or should I just return an empty list?
	@Override
	public List<Series> findByTitle(String title) {
		return seriesRepository.findByTitle(title);
	}

//	TODO handle exceptions here
//	Igor said that I shouldn't call another service from the inside of a service
//	but where should I handle exception if no genre with a given genre name is found
	@Override
	public List<Series> findByGenre(String genreName) {
		Genre genre = genreRepository.findOneByNameIgnoreCase(genreName);
		return seriesRepository.findByGenre(genre); 
	}

//	TODO handle exceptions in create/update series methods
//	TODO those two methods have duplicate code which could be put into the separate method together with exception handling that corresponds to specific value assigning
	@Override
	public Series saveSeries(SeriesDto seriesDto) {
		Series series = new Series();
		series.setTitle(seriesDto.getTitle());
		series.setDescription(seriesDto.getDescription());
		series.setYearStarted(seriesDto.getYearStarted());
		Genre genre = genreRepository.findOneByNameIgnoreCase(seriesDto.getGenreName());
		series.setGenre(genre);
		
		return seriesRepository.save(series);
	}

	@Override
	public Series updateSeries(Long seriesId, SeriesDto seriesDto) {
		Series series = seriesRepository.getOne(seriesId);
		series.setTitle(seriesDto.getTitle());
		series.setDescription(seriesDto.getDescription());
		series.setYearStarted(seriesDto.getYearStarted());
		Genre genre = genreRepository.findOneByNameIgnoreCase(seriesDto.getGenreName());
		series.setGenre(genre);

		return seriesRepository.save(series);
	}
	
}
