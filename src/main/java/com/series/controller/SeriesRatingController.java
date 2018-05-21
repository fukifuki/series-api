package com.series.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.SeriesRatingDto;
import com.series.model.Series;
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
	
	@PostMapping("/{seriesId}/ratings")
	public void rateSeries(@PathVariable("seriesId") Long seriesId,
						   @Valid @RequestBody SeriesRatingDto seriesRatingDto,
						   Principal principal) {
		User user = userService.findByUsername(principal.getName()); 
		Series series = seriesService.findById(seriesId);
		
		seriesRatingService.rateSeries(user, series, seriesRatingDto);
	}
	
}
