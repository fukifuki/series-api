package com.series.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.series.model.Series;
import com.series.model.User;
import com.series.repository.SeriesRepository;
import com.series.repository.UserRepository;

@Service
public class WatchlistsServiceImpl implements WatchlistsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SeriesRepository seriesRepository;
	
//	Maybe this method should be called with long seriesId instead of Series object
	@Override
	public Series saveToWatchlist(User user, Long seriesId) {
		Series series = seriesRepository.getOne(seriesId);
//		TODO if (series != null)...
		user.getSeriesToWatch().add(series);
		userRepository.save(user);
		
		
		return series;
	}

	@Override
	public Set<Series> getWatchList(User user) {
		return user.getSeriesToWatch();
	}

	@Override
	public Series saveToWatchedSeriesList(User user, Long seriesId) {
		Series series = seriesRepository.getOne(seriesId);
//		TODO if (series != null)...
		user.getWatchedSeries().add(series);
		userRepository.save(user);
		
		return series;
	}

	@Override
	public Set<Series> getWatchedSeriesList(User user) {
		return user.getWatchedSeries();
	}
	
}
