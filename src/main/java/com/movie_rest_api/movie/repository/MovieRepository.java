package com.movie_rest_api.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie_rest_api.movie.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
