package com.movierating.model;
import javax.persistence.*;
import javax.validation.constraints.*;

@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"movieId", "userId"})
})
@Entity
public class Ratings {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    @NotNull(message = "Movie id cannot be null")
    private String movieId;
    @Column
    private String review;
    @Column
    @NotNull(message = "User id cannot be null")
    private String userId;
    @Column
    @NotNull
    @Max(value = 10,message = "Max value allowed for rating is 10")
    @Min(value = 1,message = "Min value allowed for rating is 1")
    private int score;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
