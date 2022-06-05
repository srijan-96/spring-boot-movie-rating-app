package com.movierating.service;

import com.movierating.model.Movie;
import com.movierating.model.Ratings;
import com.movierating.model.User;
import com.movierating.repository.RatingsRepository;
import com.movierating.repository.UserRepository;
import com.movierating.response.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RatingsService {
    @Autowired
    RatingsRepository ratingsRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;
    public List<Ratings> getAllRatings()
    {
        List<Ratings> ratings = new ArrayList<Ratings>();
        ratingsRepository.findAll().forEach(rating -> ratings.add(rating));
        return ratings;
    }

    public Ratings getRatingById(int id) {
        return ratingsRepository.findById(id).get();
    }

    @Transactional
    public String saveOrUpdate(Ratings rating) {

        int totalRatings = getAllRatingsByMovie(Integer.parseInt(rating.getMovieId())).size();
        Movie movie;
        try {
            movie = movieService.getByMovieId(Integer.parseInt(rating.getMovieId()));
        }catch (Exception e){
            throw new NoSuchElementException("No record found with this movie-id");
        }
        User user;
        try {
            user = userService.getUserById(Integer.parseInt(rating.getUserId()));
        }catch (Exception e){
            throw new NoSuchElementException("No record found with this user-id");
        }

        movie.setOverallRating((movie.getOverallRating()+rating.getScore())/(totalRatings+1));
        movieService.saveOrUpdate(movie);
        ratingsRepository.save(rating);
        return "Rating saved successfully";
    }

    public void delete(int id) {
        ratingsRepository.deleteById(id);
    }

    public List<Ratings> getAllRatingsByUser(int id)
    {
        List<Ratings> ratings = new ArrayList<Ratings>();
        ratingsRepository.findByUserId(id).forEach(rating -> ratings.add(rating));
        return ratings;
    }

    public List<Ratings> getAllRatingsByMovie(int id)
    {
        List<Ratings> ratings = new ArrayList<Ratings>();
        ratingsRepository.findByMovieId(id).forEach(rating -> ratings.add(rating));
        return ratings;
    }

    public RatingResponse getAllRatingsForMovie(int id){
        Movie movie;
        try {
            movie = movieService.getByMovieId(id);
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("No records found with this movie-id");
        }
        return  new RatingResponse(movie.getId(),movie.getName(),movie.getOverallRating(),getAllRatingsByMovie(id));
    }

}
