package com.epam.app.service;

import com.epam.app.dao.MovieDao;
import com.epam.app.model.Movie;
import com.epam.app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
    MovieDao movieDao;

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie findById(Integer id) {
        return movieDao.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public void saveOrUpdate(Movie movie) {

        if (findById(movie.getId()) == null) {
            movieDao.save(movie);
        } else {
            movieDao.update(movie);
        }

    }

    @Override
    public void delete(int id) {
        movieDao.delete(id);
    }

}