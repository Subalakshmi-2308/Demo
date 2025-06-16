package com.movie_rest_api.movie.entity;

import jakarta.persistence.*;

@Entity
public class Booking {

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int bookingId; 
    private String bookingDate; 
    private int seats; 
    
    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Movie movie;
    
    @ManyToOne
    private Theater theater;

	public Booking() {
		super();
	}

	public Booking(int bookingId, String bookingDate, int seats) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.seats = seats;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", seats=" + seats + "]";
	}
    
    
}
