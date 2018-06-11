package com.series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.series.model.Genre;
import com.series.model.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long>, JpaSpecificationExecutor<Series>{
	
	List<Series> findByTitle(String title);
	
	List<Series> findByGenre(Genre genre);
	
	List<Series> findByYearStarted(Short year);
	
}
