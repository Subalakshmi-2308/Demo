package com.movie_rest_api.movie.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.movie_rest_api.movie.entity.Booking;
import com.movie_rest_api.movie.entity.Customer;
import com.movie_rest_api.movie.repository.BookingRepository;
import com.movie_rest_api.movie.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {


	@Mock
	private BookingRepository bookingRepository;
	
	
	@InjectMocks
	private BookingService bookingService;
	
	private Booking booking;
	
	@BeforeEach
	public void setUp() {
		booking=new Booking();
		booking.setBookingDate("1/5/25");
		booking.setSeats(4);
	}
	@Test
	public void saveBooking() {
		when(bookingRepository.save(booking)).thenReturn(booking);
		
		Booking saveBooking = bookingService.saveBooking(booking);
		
		assertThat(saveBooking).isNotNull();
		assertThat(saveBooking.getBookingDate()).isEqualTo("1/5/25");
		verify(bookingRepository,times(1)).save(saveBooking);
		
	}
	
	@Test
	public void getAllBooking() {
		when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));
		
		List<Booking> bookings = bookingService.getAllBooking();
		
		assertThat(bookings).isNotNull();
		assertThat(bookings).hasSize(1);
		verify(bookingRepository,times(1)).findAll();
	}
	@Test
	public void getBooking() {
		when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
		
		Booking bookingid = bookingService.getBookingById(1);
		
		assertThat(bookingid).isNotNull();
		assertThat(bookingid.getBookingDate()).isEqualTo("1/5/25");
		verify(bookingRepository,times(1)).findById(1);
	}
	
	@Test
	public void deleteBooking() {
		doNothing().when(bookingRepository).deleteById(1);
		
		bookingService.deleteBookingById(1);
		verify(bookingRepository,times(1)).deleteById(1);;
	}

}
