package com.app.repository;

import com.app.entity.Film;
import com.app.entity.Film$;
import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Film> getFilmById(short filmId){
       return jpaStreamer.stream(Film.class).filter(Film$.id.equal(filmId)).findFirst();
    }
}
