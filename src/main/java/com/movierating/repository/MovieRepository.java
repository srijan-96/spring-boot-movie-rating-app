package com.movierating.repository;

import com.movierating.model.Movie;
import com.movierating.model.Ratings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer>
{
    @Query(value = "select m.id,m.name,m.description,m.release_year,m.overall_rating from movie m INNER JOIN SCREENING s ON m.id = s.movie_id INNER JOIN cinema c ON s.cinema_id = c.id WHERE c.post_office  IN ?1", nativeQuery = true)
    List<Movie> findByMoviePostOffice(List<String> postOffices);
}
