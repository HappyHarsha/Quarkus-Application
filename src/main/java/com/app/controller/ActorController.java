package com.app.controller;


import com.app.entity.Film;
import com.app.repository.FilmRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.awt.*;
import java.util.Optional;

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
}
