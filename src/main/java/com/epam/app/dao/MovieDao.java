package com.epam.app.dao;

import com.epam.app.model.Movie;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MovieDao {

    Movie findById(Integer id);

    List<Movie> findAll();

    void save(Movie movie);

    void update(Movie movie);

    void delete(Integer id);

//    void addMovie(Movie movie, HttpServletRequest req);
}