package com.epam.app.service;

import com.epam.app.model.Movie;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MovieService {

    Movie findById(Integer id);

    List<Movie> findAll();

    void saveOrUpdate(Movie movie);

    void delete(int id);

//    void addMeal(Movie movie, HttpServletRequest req);
}