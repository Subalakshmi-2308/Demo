package com.movie_rest_api.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie_rest_api.movie.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
