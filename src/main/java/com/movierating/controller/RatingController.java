package com.movierating.controller;

import com.movierating.model.Ratings;
import com.movierating.response.RatingResponse;
import com.movierating.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RatingController {

    @Autowired
    RatingsService ratingsService;

    @GetMapping("/ratings")
    private List<Ratings> getAllRatings()
    {
        return ratingsService.getAllRatings();
    }

    @GetMapping("/rating/{id}")
    private Ratings getRating(@PathVariable("id") int id)
    {
        return ratingsService.getRatingById(id);
    }

    @DeleteMapping("/rating/{id}")
    private void deleteRating(@PathVariable("id") int id)
    {
        ratingsService.delete(id);
    }

    @PostMapping("/rating")
    private ResponseEntity<String> saveRating(@RequestBody @Valid Ratings rating){
        String response = ratingsService.saveOrUpdate(rating);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/ratings/user/{id}")
    private List<Ratings> getRatingsByUser(@PathVariable("id") int id)
    {
        try {
            return ratingsService.getAllRatingsByUser(id);
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("No records found with this user-id");
        }

    }

    @GetMapping("/ratings/movie/{id}")
    private RatingResponse getRatingsForMovie(@PathVariable("id") int id)
    {
        return ratingsService.getAllRatingsForMovie(id);
    }

}
