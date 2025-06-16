package com.movie_rest_api.movie.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Theater {

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int theaterId; 
    private String theatername; 
    private String location; 
    
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Movie> movie=new ArrayList<>();
    
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings=new ArrayList<Booking>();

	public Theater() {
		super();
	}

	public Theater(int theaterId, String theatername, String location) {
		super();
		this.theaterId = theaterId;
		this.theatername = theatername;
		this.location = location;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheatername() {
		return theatername;
	}

	public void setTheatername(String theatername) {
		this.theatername = theatername;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Movie> getMovie() {
		return movie;
	}

	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Theater [theaterId=" + theaterId + ", theatername=" + theatername + ", location=" + location + "]";
	}
    
    
}
