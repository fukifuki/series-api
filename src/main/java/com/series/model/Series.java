package com.series.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "series")
@EntityListeners(AuditingEntityListener.class)
public class Series {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;

	@ManyToMany
	@JoinTable(name = "series_to_watch", 
			   joinColumns = @JoinColumn(name = "series_id"),
			   inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> usersWhoWillWatchSeries = new HashSet<User>();

	@ManyToMany
	@JoinTable(name = "watched_series", 
			   joinColumns = @JoinColumn(name = "series_id"),
			   inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> usersWhoWatchedSeries = new HashSet<User>();

	@OneToMany(mappedBy = "series")
	private Set<Comment> comments = new HashSet<Comment>();
	
	@OneToMany(mappedBy = "primaryKey.series",
			   cascade = CascadeType.ALL)
	private Set<SeriesRating> seriesRatings = new HashSet<SeriesRating>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Set<User> getUsersWhoWillWatchSeries() {
		return usersWhoWillWatchSeries;
	}

	public void setUsersWhoWillWatchSeries(Set<User> usersWhoWillWatchSeries) {
		this.usersWhoWillWatchSeries = usersWhoWillWatchSeries;
	}

	public Set<User> getUsersWhoWatchedSeries() {
		return usersWhoWatchedSeries;
	}

	public void setUsersWhoWatchedSeries(Set<User> usersWhoWatchedSeries) {
		this.usersWhoWatchedSeries = usersWhoWatchedSeries;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment() {
		
	}
}
