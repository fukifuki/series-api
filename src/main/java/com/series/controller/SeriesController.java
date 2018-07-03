package com.series.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.SeriesDto;
import com.series.model.Series;
import com.series.service.SeriesService;
import com.series.web.util.SearchCriterion;

@RestController
@RequestMapping
public class SeriesController {

	@Autowired
	SeriesService seriesService;
		
//	TODO See how and where to handle non-String types before working with search 
	@GetMapping("/series")
	public List<SeriesDto> getAllSeries(@RequestParam(value = "search", required = false) String searchParams) {
//		create params list, than pass it to SeriesService search method
		List<SearchCriterion> searchCriteria = new ArrayList<SearchCriterion>();
		if (searchParams != null) { 
			//		String searchParamForm = "(\\w+?)(:|<|>)(\\w+?),";
			String searchParamForm = "(\\w+?)(:|<|>)([a-zA-Z0-9\\- ]*)";
			Pattern pattern = Pattern.compile(searchParamForm);
			Matcher matcher = pattern.matcher(searchParams);
		
			while(matcher.find()) {
				SearchCriterion crit = new SearchCriterion(matcher.group(1), matcher.group(2), matcher.group(3));
				searchCriteria.add(crit);
			}
		}

		return seriesService.getAllSeries(searchCriteria);
	}
	
	@PostMapping("/series")
	public SeriesDto createSeries(@Valid @RequestBody SeriesDto seriesDto) {
		System.out.println("Ctrl method called");
		return seriesService.saveSeries(seriesDto);
	}
	
//	What about exception handling here?
	@GetMapping("/series/{id}")
	public SeriesDto getSeriesById(@PathVariable(value = "id") Long seriesId) {		
		return seriesService.findById(seriesId);
	}
	
//	TODO use service instead of repository
	@PutMapping("/series/{id}")
	public SeriesDto updateSeries(@PathVariable(value = "id") Long seriesId, @Valid @RequestBody SeriesDto seriesDto) {

		return seriesService.updateSeries(seriesId, seriesDto);
	}
	
	@DeleteMapping("/series/{id}")
	public void deleteSeries(@PathVariable(value = "id") Long seriesId) {
		seriesService.deleteSeries(seriesId);
	}
}
