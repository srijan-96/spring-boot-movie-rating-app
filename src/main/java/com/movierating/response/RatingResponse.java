package com.movierating.response;

import com.movierating.model.Ratings;

import java.util.List;

public class RatingResponse {
    private int id;
    private String name;
    private double overallRating;
    private List<Ratings> ratings;

    public RatingResponse(int id, String name, double overallRating, List<Ratings> ratings) {
        this.id = id;
        this.name = name;
        this.overallRating = overallRating;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }
}
