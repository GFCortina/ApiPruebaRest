package com.gfcc.movieapi;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Movie
{
    private @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator="sec_id_movies")
    Long id;

    private String name;

    private String director;

    private String genre;

    private String synopsis;

    Movie(){ }

    Movie(String name, String director, String genre, String synopsis) {
        this.name = name;
        this.director = director;
        this.genre =genre;
        this.synopsis = synopsis;
    }
}
