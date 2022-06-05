package com.movierating.service;

import com.movierating.exception.InvalidPincodeException;
import com.movierating.model.Movie;
import com.movierating.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public Movie getByMovieId(int id) {
        return movieRepository.findById(id).get();
    }

    public void saveOrUpdate(Movie movie) {
        movieRepository.save(movie);

    }

    public void delete(int id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getMoviesByLocation(List<String> locations){
        return movieRepository.findByMoviePostOffice(locations);
    }

    public List<Movie> getMoviesByPin(String pincode) throws InvalidPincodeException {

        String url = "https://api.postalpincode.in/pincode/" + pincode;
        RestTemplate restTemplate = new RestTemplate();
        Object[] response = restTemplate.getForObject(url,Object[].class);
        LinkedHashMap<String,Object> responseBody = (LinkedHashMap<String, Object>) Arrays.asList(response).get(0);

        String responseCode = (String) responseBody.get("Status");
        if(responseCode.equalsIgnoreCase("success")) {

            ArrayList<LinkedHashMap<String, String>> postOfficeList = (ArrayList<LinkedHashMap<String, String>>) responseBody.get("PostOffice");
            List<String> postOfficeNames = new ArrayList<>();
            postOfficeList.forEach(mp -> postOfficeNames.add(mp.get("Name")));
            return movieRepository.findByMoviePostOffice(postOfficeNames);
        }
        else {
            throw new InvalidPincodeException("No address details found for this pincode");
        }
    }
}
