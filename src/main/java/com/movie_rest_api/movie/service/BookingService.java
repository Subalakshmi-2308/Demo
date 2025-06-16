package com.movie_rest_api.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie_rest_api.movie.entity.Booking;
import com.movie_rest_api.movie.repository.BookingRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class BookingService {

	@Autowired
	private BookingRepository bookingrepository;
	
	public Booking saveBooking(Booking booking) {
		return bookingrepository.save(booking);
	}
	
	public Booking getBookingById(Integer id) {
		return bookingrepository.findById(id).orElse(null);
	}
	
	public List<Booking> getAllBooking(){
		return bookingrepository.findAll();
	}
	
	public void deleteBookingById(Integer id) {
		bookingrepository.deleteById(id);
	}
}
