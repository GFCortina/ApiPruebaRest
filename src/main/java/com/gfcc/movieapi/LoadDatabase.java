package com.gfcc.movieapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase
{
    @Bean
    CommandLineRunner initDatabase(MovieRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Movie("Avengers Endgame", "Joe & Anthony Russo","Superhero","Latest installment in the MCU universe")));
            log.info("Preloading " + repository.save(new Movie("Toy Story", "John Lasseter","Animation","Story about toys when their owner ain´t around")));
        };
    }
}
