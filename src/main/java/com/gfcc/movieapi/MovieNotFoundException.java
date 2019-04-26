package com.gfcc.movieapi;

public class MovieNotFoundException extends RuntimeException
{
    MovieNotFoundException(Long id) {
        super("Could not find movie: " + id);
    }
}
