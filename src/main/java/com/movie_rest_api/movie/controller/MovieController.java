package com.movie_rest_api.movie.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movie_rest_api.movie.entity.*;
import com.movie_rest_api.movie.entity.Theater;
import com.movie_rest_api.movie.exception.ResourceNotFoundException;
import com.movie_rest_api.movie.service.*;


@RestController
public class MovieController {
	
	@Autowired
	private TheaterService theaterService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("Hello Rest API!! ",HttpStatus.OK);
	}
	
	@PostMapping("/theater/save")
	public ResponseEntity<?> saveTheater(@RequestBody Theater theater){
		try {
			theaterService.saveTheater(theater);
			return new ResponseEntity<Theater>(theater,HttpStatus.OK);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/movie/save")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
		try {
			
			movie.setTitle(movie.getTitle());
			movie.setGenre(movie.getGenre());
			Theater theater = theaterService.getTheaterById(movie.getTheater().getTheaterId());
			if(theater ==null) {
				throw new ResourceNotFoundException("Theater not found");
			}
			movie.setTheater(theater);
			List<Movie> movies=new ArrayList<>();
			movies.add(movie);
			theater.setMovie(movies);
			movieService.saveMovie(movie);
			return new ResponseEntity<Movie>(movie,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/customer/save")
	public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
		try {
			customer.setCustomerName(customer.getCustomerName());
			customer.setEmail(customer.getEmail());
			customerService.saveCustomer(customer);
			return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/bookings/save")
	public ResponseEntity<?> saveBookings(@RequestBody Booking booking){
		try {
			booking.setBookingDate(booking.getBookingDate());
			booking.setSeats(booking.getSeats());
			
			Customer customer = customerService.getCustomerById(booking.getCustomer().getCustomerId());
			if(customer ==null) {
				throw new ResourceNotFoundException("Customer not found");
			}
			booking.setCustomer(customer);
			List<Booking> bookings=new ArrayList<>();
			bookings.add(booking);
			customer.setBookings(bookings);
			
			Movie movie=movieService.getMovieById(booking.getMovie().getMovieId());
			if(movie ==null) {
				throw new ResourceNotFoundException("Movie not found");
			}
			booking.setMovie(movie);
			
			Theater theater = theaterService.getTheaterById(booking.getTheater().getTheaterId());
			if(theater ==null) {
				throw new ResourceNotFoundException("Theater not found");
			}
			booking.setTheater(theater);
			
			bookingService.saveBooking(booking);
			return new ResponseEntity<Booking>(booking,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/theater/all")
	public ResponseEntity<List<Theater>> getAllTheaters(){
		List<Theater> theaters=theaterService.getAllTheater();
		return new ResponseEntity<List<Theater>>(theaters,HttpStatus.OK);
	}
	@GetMapping("/movie/all")
	public ResponseEntity<List<Movie>> getAllMovies(){
		List<Movie> movies=movieService.getAllMovies();
		return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
	}
	@GetMapping("/customer/all")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers=customerService.getAllCustomer();
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	@GetMapping("/bookings/all")
	public ResponseEntity<List<Booking>> getAllBookings(){
		List<Booking> bookinglist=bookingService.getAllBooking();
		return new ResponseEntity<List<Booking>>(bookinglist,HttpStatus.OK);
	}
	

	@GetMapping("/theater/{id}")
	public ResponseEntity<?> getTheaterById(@PathVariable("id") int theaterid){
		Theater theaterbyid=theaterService.getTheaterById(theaterid);
		if(theaterbyid ==null) {
			throw new ResourceNotFoundException("Theater ID not found");
		}
		else {
		return new ResponseEntity<>(theaterbyid,HttpStatus.OK);
		}
	}
	@GetMapping("/movie/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") int movieid){
		Movie moviebyid=movieService.getMovieById(movieid);
		if(moviebyid ==null) {
			throw new ResourceNotFoundException("Movie ID not found");
		}
		else {
		return new ResponseEntity<Movie>(moviebyid,HttpStatus.OK);
		}
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int customerid){
		Customer customerbyid=customerService.getCustomerById(customerid);
		if(customerbyid ==null) {
			throw new ResourceNotFoundException("Customer ID not found");
		}
		else {
		return new ResponseEntity<Customer>(customerbyid,HttpStatus.OK);
		}
	}
	
	@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id") int bookingid){
		Booking bookingbyid=bookingService.getBookingById(bookingid);
		if(bookingbyid ==null) {
			throw new ResourceNotFoundException("Booking ID not found");
		}
		else {
		return new ResponseEntity<Booking>(bookingbyid,HttpStatus.OK);
		}
	}
	
	@PutMapping("/theater/update/{id}")
	public ResponseEntity<Theater> updateTheater(@PathVariable("id") int id,@RequestBody Theater theater){
		Theater theaterbyid = theaterService.getTheaterById(id);
		if(theaterbyid ==null) {
			throw new ResourceNotFoundException("Theater ID not found");
		}
		else {
		theaterbyid.setTheatername(theater.getTheatername());
		theaterbyid.setLocation(theater.getLocation());
		
		theaterService.saveTheater(theaterbyid);
		return new ResponseEntity<Theater>(theaterbyid,HttpStatus.OK);
		}
	}
	
	@PutMapping("/movie/update/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") int id,@RequestBody Movie movie){
		Movie moviebyid=movieService.getMovieById(id);
		if(moviebyid ==null) {
			throw new ResourceNotFoundException("Theater ID not found");
		}
		
		moviebyid.setTitle(movie.getTitle());
		moviebyid.setGenre(movie.getGenre());
		
		Theater theater = theaterService.getTheaterById(movie.getTheater().getTheaterId());
		if(theater ==null) {
			throw new ResourceNotFoundException("Theater ID not found");
		}
		moviebyid.setTheater(theater);
		movieService.saveMovie(moviebyid);
		return new ResponseEntity<Movie>(moviebyid,HttpStatus.OK);
	}
	
	@PutMapping("/customer/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,@RequestBody Customer customer){
		Customer customerbyid=customerService.getCustomerById(id);
		if(customerbyid ==null) {
			throw new ResourceNotFoundException("Customer ID not found");
		}
		customerbyid.setCustomerName(customer.getCustomerName());
		customerbyid.setEmail(customer.getEmail());
		customerService.saveCustomer(customerbyid);
		return new ResponseEntity<Customer>(customerbyid,HttpStatus.OK);
	}
	
	@PutMapping("/bookings/update/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable("id") int id,@RequestBody Booking booking){
		Booking bookingbyid=bookingService.getBookingById(id);
		if(bookingbyid ==null) {
			throw new ResourceNotFoundException("Booking ID not found");
		}
		bookingbyid.setBookingDate(booking.getBookingDate());
		bookingbyid.setSeats(booking.getSeats());
		
		Customer customerbyid=customerService.getCustomerById(booking.getCustomer().getCustomerId());
		if(customerbyid ==null) {
			throw new ResourceNotFoundException("Customer ID not found");
		}
		bookingbyid.setCustomer(customerbyid);
		
		Movie moviebyid=movieService.getMovieById(booking.getMovie().getMovieId());
		if(moviebyid ==null) {
			throw new ResourceNotFoundException("Movie ID not found");
		}
		bookingbyid.setMovie(moviebyid);
		
		Theater theaterbyid=theaterService.getTheaterById(booking.getTheater().getTheaterId());
		if(theaterbyid ==null) {
			throw new ResourceNotFoundException("Theater ID not found");
		}
		bookingbyid.setTheater(theaterbyid);
		
		bookingService.saveBooking(bookingbyid);
		return new ResponseEntity<Booking>(bookingbyid,HttpStatus.OK);

	}
	
	@DeleteMapping("/theater/delete/{id}")
	public ResponseEntity<String> deleteTheater(@PathVariable("id") int id){
		theaterService.deleteTheaterById(id);
		return new ResponseEntity<String>("Theater Deleted",HttpStatus.OK);
	}
	
	@DeleteMapping("/movie/delete/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable("id") int id){
		movieService.deleteMovieById(id);
		return new ResponseEntity<String>("Movie Deleted",HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id){
		customerService.deleteCustomerById(id);
		return new ResponseEntity<String>("Customer Deleted",HttpStatus.OK);
	}
	
	@DeleteMapping("/bookings/delete/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable("id") int id){
		bookingService.deleteBookingById(id);
		return new ResponseEntity<String>("Booking Deleted",HttpStatus.OK);
	}
}
