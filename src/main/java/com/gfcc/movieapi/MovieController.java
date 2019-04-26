package com.gfcc.movieapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


@RestController
public class MovieController
{

    private final MovieRepository repository;

    private final MovieResourceAssembler assembler;

    @Autowired
    MovieController(MovieRepository repository, MovieResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/movies")
    Resources<Resource<Movie>> all(){

        List<Resource<Movie>> movies = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(movies,
                linkTo(methodOn(MovieController.class).all()).withSelfRel());
    }


    @PostMapping("/movies")
    Movie newMovie(@RequestBody Movie newMovie) {
        return repository.save(newMovie);
    }

    // Single item

    @GetMapping("/movies/{id}")
    Resource<Movie> one(@PathVariable Long id) {

        Movie movie = repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        return assembler.toResource(movie);
    }

    @PutMapping("/movies/{id}")
    Movie replaceMovie(@RequestBody Movie newMovie, @PathVariable Long id) {

        return repository.findById(id)
                .map(movie -> {
                    movie.setName(newMovie.getName());
                    movie.setDirector(newMovie.getDirector());
                    movie.setGenre(newMovie.getGenre());
                    movie.setSynopsis(newMovie.getSynopsis());
                    return repository.save(movie);
                })
                .orElseGet(() -> {
                    newMovie.setId(id);
                    return repository.save(newMovie);
                });
    }

    @DeleteMapping("/movies/{id}")
    void deltemovie(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
