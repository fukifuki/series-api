package com.series.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.series.dto.UserDto;
import com.series.model.Series;
import com.series.model.User;
import com.series.repository.UserRepository;
import com.series.service.SeriesService;
import com.series.service.UserService;
import com.series.service.WatchlistsService;

@RestController
@RequestMapping
public class WatchListsController {
			
	@Autowired
	private UserService userService;
	
	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private WatchlistsService watchlistsService;	
		
//	Is there a way get principal object to use it here without having to inject it in every method?
//	@PathVariable(value = "username") String username
	@GetMapping("/watchlist")
	public Set<Series> getWatchListForUsername(Principal principal) {
		
//		Tried to get the user this way, by calling service method that calls getName() on principal object, but got NullPointer exception
//		User user = userService.getLoggedInUser();
		
		UserDto user = userService.findByUsername(principal.getName()); 
		Long userId = user.getId();
		
		return watchlistsService.getWatchList(userId);
	}
	
//	return value type?
	@PostMapping("/watchlist/{seriesId}")
	public Series addSeriesToWatchList(@PathVariable("seriesId") Long seriesId, Principal principal) {

//		TODO This should be done before any method call in this controller 
		UserDto user = userService.findByUsername(principal.getName());
		Long userId = user.getId();
		
		return watchlistsService.saveToWatchlist(userId, seriesId);
	}
	
	@GetMapping("/watchedlist")
	public Set<Series> getWatchedListForUsername(Principal principal) {
		
		UserDto user = userService.findByUsername(principal.getName());
		Long userId = user.getId();
		
		return watchlistsService.getWatchedSeriesList(userId);	
	}
	
	public Series addSeriesToWatchedList(@PathVariable("seriesId") Long seriesId, Principal principal) {
		
		UserDto user = userService.findByUsername(principal.getName());
		Long userId = user.getId();
		
		return watchlistsService.saveToWatchedSeriesList(userId, seriesId);
	}

}
