package com.movie_rest_api.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie_rest_api.movie.entity.Movie;
import com.movie_rest_api.movie.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieService {
	
	@Autowired
	private MovieRepository movierepository;
	
	public Movie saveMovie(Movie movie) {
		return movierepository.save(movie);
	}
	
	public Movie getMovieById(Integer id) {
		return movierepository.findById(id).orElse(null);
	}
	
	public List<Movie> getAllMovies(){
		return movierepository.findAll();
	}
	
	public void deleteMovieById(Integer id) {
		movierepository.deleteById(id);
	}

}
