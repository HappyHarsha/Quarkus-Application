package com.app.controller;


import com.app.entity.Film;
import com.app.repository.FilmRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Path("/actor")
public class ActorController {

    @Inject
    private FilmRepository filmRepository;

    @GET
    @Path("/getActors")
    @Produces(MediaType.TEXT_PLAIN)
    public String getActor(){
        return "Hello World";
    }

    @GET
    @Path("film/{filmId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Film getFilm(short filmId){
        Optional<Film> filmById = filmRepository.getFilmById(filmId);
        return filmById.orElse(null);
    }

    @GET
    @Path("/getFilmLazy")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getFilmsLazy(@QueryParam("page") long page, @QueryParam("size") long size){
        Map<String, String> map = new HashMap<>();
        Stream<Film> films = filmRepository.getFilms(page, size);
        films.forEach(film -> {
            map.put(String.format("%s  %s", film.getId(), film.getTitle()), film.getDescription());
        });
        return map;
    }

    @PUT
    @Path("/update/{str}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getFilmsLazy(@QueryParam("str") String str, @QueryParam("value") String value){
        Map<String, String> map = new HashMap<>();
        filmRepository.updateFilms(str, value);
        Stream<Film> films = filmRepository.findFilmsByName(value);
        films.forEach(film -> {
            map.put(String.format("%s  %s", film.getId(), film.getTitle()), film.getDescription());
        });
        return map;
    }
}
