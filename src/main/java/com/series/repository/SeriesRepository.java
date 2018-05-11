package com.series.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.series.model.Series;

public interface SeriesRepository extends JpaRepository<Series, Long>{

	
}
