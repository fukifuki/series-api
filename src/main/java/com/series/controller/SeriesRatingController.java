package com.series.controller;

import java.security.Principal;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
import com.series.model.SeriesRating;
import com.series.model.User;
import com.series.service.SeriesRatingService;
import com.series.service.SeriesService;
import com.series.service.UserService;

@RestController
@RequestMapping("/series")
public class SeriesRatingController {
	
	@Autowired
	SeriesRatingService seriesRatingService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SeriesService seriesService;
	
	@GetMapping("/{seriesId}/ratings")
	public Set<SeriesRatingDto> getSeriesRatings(@PathVariable("seriesId") Long seriesId) {

//		TODO catch exception here
		return seriesRatingService.getSeriesRatings(seriesId);
	}
	
	@PostMapping("/{seriesId}/ratings")
	public void rateSeries(@PathVariable("seriesId") Long seriesId,
						   @Valid @RequestBody SeriesRatingDto seriesRatingDto,
						   Principal principal) {
		
		User user = userService.findByUsername(principal.getName()); 
		
		seriesRatingService.rateSeries(user, seriesId, seriesRatingDto);
	}
	
}
