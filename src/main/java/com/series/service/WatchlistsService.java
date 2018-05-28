package com.series.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.series.model.Series;
import com.series.model.User;

public interface WatchlistsService {
	
//	TODO I should move searching for logged in user into series repository
	Series saveToWatchlist(User user, Long seriesId);
	
	Set<Series> getWatchList(User user);
	
//	TODO 
	Series saveToWatchedSeriesList(User user, Long seriesId);
	
	Set<Series> getWatchedSeriesList(User user);
	
}
