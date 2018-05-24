package com.series.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.series.dto.UserDto;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable, UserDetails {
	
	public User() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotBlank
	@Size(min = 4, max = 15, 
			message = "Username must be between 2 and 15 characters long")
	private String username;
	
	
	@NotBlank
	@Size(min = 6,
			message = "Password must be at least 6 characters long")
	private String password;
	
	@NotBlank
	@Size(max = 255,
			message = "Email cant't be longer than 255 characters")
	@Email(message = "Email should be of appropriate format")
	private String email;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@ManyToMany
	@JoinTable(name = "watched_series", 
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "series_id"))
	private Set<Series> watchedSeries = new HashSet<Series>();

	@ManyToMany
	@JoinTable(name = "series_to_watch", 
			   joinColumns = @JoinColumn(name = "user_id"),
			   inverseJoinColumns = @JoinColumn(name = "series_id"))
	private Set<Series> seriesToWatch = new HashSet<Series>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Comment> comments = new HashSet<Comment>();

	@JsonIgnore
	@OneToMany(mappedBy = "primaryKey.user",
			   cascade = CascadeType.ALL)
	private Set<SeriesRating> seriesRatings = new HashSet<SeriesRating>();
	
//	@Column(nullable = false, updatable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	@CreatedDate
//	private Date createdAt;
//	
//	@Column(nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	@LastModifiedDate
//	private Date updatedAt;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}; 
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Series> getWatchedSeries() {
		return watchedSeries;
	}

	public void setWatchedSeries(Set<Series> watchedSeries) {
		this.watchedSeries = watchedSeries;
	}

	public Set<Series> getSeriesToWatch() {
		return seriesToWatch;
	}

	public void setSeriesToWatch(Set<Series> seriesToWatch) {
		this.seriesToWatch = seriesToWatch;
	}
	
	public void addSeriesToWatchedList(Series series) {
		this.watchedSeries.add(series);
		series.getUsersWhoWatchedSeries().add(this);
	}
	
	public void removeSeriesFromWatchedList(Series series) {
		this.watchedSeries.remove(series);
		series.getUsersWhoWatchedSeries().remove(this);
	}
	
	public void addSeriesToWatchList(Series series) {
		this.seriesToWatch.add(series);
		series.getUsersWhoWillWatchSeries().add(this);
	}
	
	public void removeSeriesFromWatchList(Series series) {
		this.seriesToWatch.remove(series);
		series.getUsersWhoWillWatchSeries().remove(this);
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<SeriesRating> getSeriesRatings() {
		return seriesRatings;
	}

	public void setSeriesRatings(Set<SeriesRating> seriesRatings) {
		this.seriesRatings = seriesRatings;
	}
		
}
