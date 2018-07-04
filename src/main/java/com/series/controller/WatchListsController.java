package com.series.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.model.Series;
import com.series.service.WatchlistsService;

@RestController
@RequestMapping("/users/{userId}")
public class WatchListsController {
	
	@Autowired
	private WatchlistsService watchlistsService;	
	
	
//  TODO Return dtos instead of enities
 	
	
	@GetMapping("/watchlist")
	public Set<Series> getWatchlistForUserId(@PathVariable("userId") Long userId) {
		
		return watchlistsService.getWatchList(userId);
	}
	
	@PostMapping("/watchlist/{seriesId}")
	public Series addSeriesToWatchList(@PathVariable("userId") Long userId, @PathVariable("seriesId") Long seriesId) {
		
		return watchlistsService.saveToWatchlist(userId, seriesId);
	}
	
	@GetMapping("/history")
	public Set<Series> getWatchedListForUsername(@PathVariable("userId") Long userId) {
		
		return watchlistsService.getWatchedSeriesList(userId);	
	}
	
	@PostMapping("/history/{seriesId}")
	public Series addSeriesToWatchedList(@PathVariable("userId") Long userId, @PathVariable("seriesId") Long seriesId) {
		
		return watchlistsService.saveToWatchedSeriesList(userId, seriesId);
	}
}
