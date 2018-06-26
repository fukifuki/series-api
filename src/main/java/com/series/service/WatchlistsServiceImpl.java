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
	public Series saveToWatchlist(Long userId, Long seriesId) {
		Series series = seriesRepository.getOne(seriesId);
//		TODO if (series != null)...
		User user = userRepository.getOne(userId);
		user.getSeriesToWatch().add(series);
		userRepository.save(user);
		
		
		return series;
	}

	@Override
	public Set<Series> getWatchList(Long userId) {
		User user = userRepository.getOne(userId);
		return user.getSeriesToWatch();
	}

	@Override
	public Series saveToWatchedSeriesList(Long userId, Long seriesId) {
		Series series = seriesRepository.getOne(seriesId);
//		TODO if (series != null)...
		User user = userRepository.getOne(userId);
		user.getWatchedSeries().add(series);
		userRepository.save(user);
		
		return series;
	}

	@Override
	public Set<Series> getWatchedSeriesList(Long userId) {
		User user = userRepository.getOne(userId);
		return user.getWatchedSeries();
	}
	
}
