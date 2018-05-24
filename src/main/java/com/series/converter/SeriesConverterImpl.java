package com.series.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.series.dto.SeriesDto;
import com.series.model.Genre;
import com.series.model.Series;
import com.series.repository.GenreRepository;

@Component
public class SeriesConverterImpl implements SeriesConverter {

//	TODO Remove the reference to GenreRepository here if it is possible
	@Autowired
	GenreRepository genreRepository;
	
	@Override
	public Series createFromDto(SeriesDto dto) {
		Series series = new Series();
	
		series.setTitle(dto.getTitle());
		series.setDescription(dto.getDescription());
		series.setYearStarted(dto.getYearStarted());
		Genre genre = genreRepository.findOneByNameIgnoreCase(dto.getGenreName());
		series.setGenre(genre);
		
		return series;
	}

	@Override
	public Series updateFromDto(Series series, SeriesDto dto) {
		series.setTitle(dto.getTitle());
		series.setDescription(dto.getDescription());
		series.setYearStarted(dto.getYearStarted());
		Genre genre = genreRepository.findOneByNameIgnoreCase(dto.getGenreName());
		series.setGenre(genre);
		
		return series;
	}

	@Override
	public SeriesDto createFromEntity(Series series) {
		SeriesDto dto = new SeriesDto();
		dto.setTitle(series.getTitle());
		dto.setDescription(series.getDescription());
		dto.setYearStarted(series.getYearStarted());
		dto.setGenreName(series.getGenre().getName());
		
		return dto;
	}

	@Override
	public List<SeriesDto> createFromEntities(List<Series> series) {
		List<SeriesDto> dtos = new ArrayList<SeriesDto>();
		series.forEach(s -> { 
			dtos.add(createFromEntity(s)); 
		});
		
		return dtos;
	}
	
	
}
