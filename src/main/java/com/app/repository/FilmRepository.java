package com.app.repository;

import com.app.entity.Film;
import com.app.entity.Film$;
import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Film> getFilmById(short filmId){
       return jpaStreamer.stream(Film.class).filter(Film$.id.equal(filmId)).findFirst();
    }


    public Stream<Film> getFilms(long page, long size){
        Stream<Film> limit = jpaStreamer.stream(Projection.select(Film$.id, Film$.title, Film$.description)).sorted(Film$.title).skip(page * size).limit(size);
        return limit;
    }

    @Transactional
    public void updateFilms(String title, String value){
        AtomicInteger i= new AtomicInteger();
        jpaStreamer.stream(Film.class).filter(Film$.title.startsWith(title)).forEach(film -> {
            i.getAndIncrement();
            film.setTitle(value + i);
        });
    }

    public Stream<Film> findFilmsByName(String str) {
        return jpaStreamer.stream(Projection.select(Film$.id,Film$.title,Film$.description)).filter(Film$.title.startsWith(str)).sorted(Film$.title);
    }
}
