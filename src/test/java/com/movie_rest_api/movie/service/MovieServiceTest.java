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

import com.movie_rest_api.movie.entity.*;
import com.movie_rest_api.movie.repository.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

	@Mock
	private MovieRepository movieRepository;
	
	
	@InjectMocks
	private MovieService movieService;
	
	private Movie movie;
	
	@BeforeEach
	public void setUp() {
		movie=new Movie();
		movie.setTitle("GBU");
		movie.setGenre("Action");
	}
	@Test
	public void saveMovie() {
		when(movieRepository.save(movie)).thenReturn(movie);
		
		Movie savemovie = movieService.saveMovie(movie);
		
		assertThat(savemovie).isNotNull();
		assertThat(savemovie.getTitle()).isEqualTo("GBU");
		verify(movieRepository,times(1)).save(movie);
		
	}
	
	@Test
	public void getAllMovie() {
		when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));
		
		List<Movie> movies = movieService.getAllMovies();
		
		assertThat(movies).isNotNull();
		assertThat(movies).hasSize(1);
		verify(movieRepository,times(1)).findAll();
	}
	@Test
	public void getMovie() {
		when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
		
		Movie movie = movieService.getMovieById(1);
		
		assertThat(movie).isNotNull();
		assertThat(movie.getTitle()).isEqualTo("GBU");
		verify(movieRepository,times(1)).findById(1);
	}
	
	@Test
	public void deleteMovie() {
		doNothing().when(movieRepository).deleteById(1);
		
		movieService.deleteMovieById(1);
		verify(movieRepository,times(1)).deleteById(1);;
	}

}
