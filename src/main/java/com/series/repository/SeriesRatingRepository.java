package com.series.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.series.model.SeriesRating;
import com.series.model.UserSeriesId;

@Repository
public interface SeriesRatingRepository extends JpaRepository<SeriesRating, UserSeriesId> {

}
