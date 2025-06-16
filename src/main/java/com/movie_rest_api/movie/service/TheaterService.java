package com.movie_rest_api.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie_rest_api.movie.entity.Theater;
import com.movie_rest_api.movie.repository.TheaterRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TheaterService {
	
	@Autowired
	private TheaterRepository theaterrepository;
	
	public Theater saveTheater(Theater theater) {
		return theaterrepository.save(theater);
	}
	
	public Theater getTheaterById(Integer id) {
		return theaterrepository.findById(id).orElse(null);
	}
	
	public List<Theater> getAllTheater(){
		return theaterrepository.findAll();
	}
	
	public void deleteTheaterById(Integer id) {
		theaterrepository.deleteById(id);
	}
}
