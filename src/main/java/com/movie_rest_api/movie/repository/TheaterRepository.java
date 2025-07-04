package com.movie_rest_api.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie_rest_api.movie.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

}
