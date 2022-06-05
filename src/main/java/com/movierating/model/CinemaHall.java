package com.movierating.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class CinemaHall {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull(message = "Serial number cannot be null")
    private int hallSerialNumber;
    @Column
    @Min(value = 10, message = "Minimum number of seats is 10")
    private int totalSeats;
    @Column
    @NotNull(message = "Cinema id cannot be null")
    private String cinemaId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHallSerialNumber() {
        return hallSerialNumber;
    }

    public void setHallSerialNumber(int hallSerialNumber) {
        this.hallSerialNumber = hallSerialNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }
}
