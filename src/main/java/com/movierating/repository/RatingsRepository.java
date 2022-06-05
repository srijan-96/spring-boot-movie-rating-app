package com.movierating.repository;

import com.movierating.model.Ratings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingsRepository extends CrudRepository<Ratings, Integer> {
    @Query(value = "SELECT * FROM RATINGS WHERE MOVIE_ID = ?1", nativeQuery = true)
    List<Ratings> findByMovieId(int movieId);

    @Query(value = "SELECT * FROM RATINGS WHERE USER_ID = ?1", nativeQuery = true)
    List<Ratings> findByUserId(int userId);


}
