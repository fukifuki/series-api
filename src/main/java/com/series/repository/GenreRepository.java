package com.series.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.series.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

//	I'll probably use only case ignoring method below...
//	Genre findOneByName(String name);
	
	Genre findOneByNameIgnoreCase(String name);
}
