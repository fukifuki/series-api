package com.series.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.series.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
	
	Genre findOneByName(String name);
	
}
