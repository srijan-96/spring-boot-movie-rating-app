package com.movierating.controller;

import com.movierating.exception.InvalidPincodeException;
import com.movierating.model.Movie;
import com.movierating.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    @GetMapping("/movies")
    private List<Movie> getAllMovies()
    {
        return movieService.getAllMovies();
    }
    @GetMapping("/movie/{id}")
    private Movie getMovie(@PathVariable("id") int id)
    {
        return movieService.getByMovieId(id);
    }
    @DeleteMapping("/movie/{id}")
    private void deleteMovie(@PathVariable("id") int id)
    {
        movieService.delete(id);
    }
    @PostMapping("/movie")
    private ResponseEntity<String> saveMovie(@RequestBody @Valid Movie movie)
    {
        movieService.saveOrUpdate(movie);
        return new ResponseEntity<>("Movie added with id :" + movie.getId(), HttpStatus.OK);
    }

    @GetMapping("/movies/pin/{pin}")
    private List<Movie> getMovieForPin(@PathVariable("pin") String pin) throws InvalidPincodeException {
        return movieService.getMoviesByPin(pin);
    }
}
