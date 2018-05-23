package com.series.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "series_ratings")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.user", 
				      	 joinColumns = @JoinColumn(name = "user_id")),
	@AssociationOverride(name = "primaryKey.series",
						 joinColumns = @JoinColumn(name = "series_id")) })
public class SeriesRating {


	@EmbeddedId
	@Column(unique = true)
	private UserSeriesId primaryKey = new UserSeriesId();
	
	@Range(min = 1, max = 5)
	private int rating;

	public UserSeriesId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(UserSeriesId primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Transient
	public User getUser() {
		return getPrimaryKey().getUser();
	}
	
	public void setUser(User user) {
		getPrimaryKey().setUser(user);
	}
	
	@Transient
	public Series getSeries() {
		return getPrimaryKey().getSeries();
	}
	
	public void setSeries(Series series) {
		getPrimaryKey().setSeries(series);
	}
}
