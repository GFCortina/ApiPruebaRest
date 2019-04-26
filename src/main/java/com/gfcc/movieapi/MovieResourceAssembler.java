package com.gfcc.movieapi;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class MovieResourceAssembler implements ResourceAssembler<Movie, Resource<Movie>>
{
    @Override
    public Resource<Movie> toResource(Movie movie) {

        return new Resource<>(movie,
                linkTo(methodOn(MovieController.class).one(movie.getId())).withSelfRel(),
                linkTo(methodOn(MovieController.class).all()).withRel("movies"));
    }
}
