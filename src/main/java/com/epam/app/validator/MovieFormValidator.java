package com.epam.app.validator;

import com.epam.app.model.Movie;
import com.epam.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MovieFormValidator implements Validator {
    @Autowired
    MovieService movieService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Movie.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.movieForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "director", "NotEmpty.movieForm.director");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "NotEmpty.movieForm.year");
    }
}