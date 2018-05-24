package com.series.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.SeriesDto;
import com.series.model.Series;
import com.series.service.SeriesService;

@RestController
@RequestMapping
public class SeriesController {

	@Autowired
	SeriesService seriesService;
		
	@GetMapping("/series")
	public List<SeriesDto> getAllSeries() {
		return seriesService.getAllSeries();
	}
	
	@PostMapping("/series")
	public Series createSeries(@Valid @RequestBody SeriesDto seriesDto) {
		return seriesService.saveSeries(seriesDto);
	}
	
//	What about exception handling here?
	@GetMapping("/series/{id}")
	public SeriesDto getSeriesById(@PathVariable(value = "id") Long seriesId) {		
		return seriesService.findById(seriesId);
	}
	
//	TODO use service instead of repository
	@PutMapping("/series/{id}")
	public Series updateSeries(@PathVariable(value = "id") Long seriesId, @Valid @RequestBody SeriesDto seriesDto) {
//		or should I call findById and than saveSeries service methods here in updateSeries method???
//		In that case separate updateSeries in seriesService wouldn't be needed
		return seriesService.updateSeries(seriesId, seriesDto);
	}
	
}
