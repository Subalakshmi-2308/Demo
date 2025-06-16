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

import com.movie_rest_api.movie.entity.Theater;
import com.movie_rest_api.movie.repository.TheaterRepository;


@ExtendWith(MockitoExtension.class)
class TheaterServiceTest {

	@Mock
	private TheaterRepository theaterRepository;
	
	@InjectMocks
	private TheaterService theaterService;
	
	private Theater theater;
	
	@BeforeEach
	public void setUp() {
		theater=new Theater();
		theater.setTheatername("PVR");
		theater.setLocation("Vellore");
	}
	@Test
	public void saveTheater() {
		when(theaterRepository.save(theater)).thenReturn(theater);
		
		Theater savetheater = theaterService.saveTheater(theater);
		
		assertThat(savetheater).isNotNull();
		assertThat(savetheater.getTheatername()).isEqualTo("PVR");
		verify(theaterRepository,times(1)).save(theater);
		
	}
	
	@Test
	public void getAllTheater() {
		when(theaterRepository.findAll()).thenReturn(Arrays.asList(theater));
		
		List<Theater> theaters = theaterService.getAllTheater();
		
		assertThat(theaters).isNotNull();
		assertThat(theaters).hasSize(1);
		verify(theaterRepository,times(1)).findAll();
	}
	@Test
	public void getTheater() {
		when(theaterRepository.findById(1)).thenReturn(Optional.of(theater));
		
		Theater theater = theaterService.getTheaterById(1);
		
		assertThat(theater).isNotNull();
		assertThat(theater.getTheatername()).isEqualTo("PVR");
		verify(theaterRepository,times(1)).findById(1);
	}
	
	@Test
	public void deleteTheater() {
		doNothing().when(theaterRepository).deleteById(1);
		
		theaterService.deleteTheaterById(1);
		verify(theaterRepository,times(1)).deleteById(1);;
	}

}
