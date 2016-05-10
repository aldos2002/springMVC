package com.epam.app.web;

import com.epam.app.model.Movie;
import com.epam.app.service.MovieService;
import com.epam.app.validator.MovieFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Almas_Doskozhin
 * on 5/2/2016.
 */
@Controller
public class MovieTilesController {
    public static final String INDEX_VIEW = "show";
    public static final String INFO_VIEW = "info";
    private final Logger logger = LoggerFactory.getLogger(MovieTilesController.class);

    @Autowired
    MovieFormValidator movieFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(movieFormValidator);
    }

    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping(value = "/movies")
    public ModelAndView info(Model model) {
        model.addAttribute("message", "Message from main controller to info page!");
        model.addAttribute("movies", movieService.findAll());
        return new ModelAndView(INDEX_VIEW);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public ModelAndView infoMovie(@PathVariable("id") int id, Model model) {
        logger.debug("showMovie() id: {}", id);
        Movie movie = movieService.findById(id);
        if (movie == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Movie not found");
        }
        model.addAttribute("movie", movie);

        return new ModelAndView(INFO_VIEW);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public String saveOrUpdateMovie(@ModelAttribute("movieForm") @Validated Movie movie,
                                    BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        logger.debug("saveOrUpdateMovie() : {}", movie);

        if (result.hasErrors()) {
            return "movies/movieform";
        } else {
            if (movie.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "Movie added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Movie updated successfully!");
            }
            movieService.saveOrUpdate(movie);
            return "redirect:/movies/" + movie.getId();
        }

    }

    @RequestMapping(value = "/movies/add", method = RequestMethod.GET)
    public String showAddMovieForm(Model model) {
        logger.debug("showAddMovieForm()");
        Movie movie = new Movie();
        movie.setName("Movie_name");
        movie.setDirector("Movie director");
        movie.setYear("2016");
        model.addAttribute("movieForm", movie);

        return "movieform";
    }

    @RequestMapping(value = "/movies/{id}/update", method = RequestMethod.GET)
    public String showUpdateMovieForm(@PathVariable("id") int id, Model model) {
        logger.debug("showUpdateMovieForm() : {}", id);
        Movie movie = movieService.findById(id);
        model.addAttribute("movieForm", movie);

        return "movieform";
    }

    @RequestMapping(value = "/movies/{id}/delete", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") int id) {
        logger.debug("deleteMovie() : {}", id);
        movieService.delete(id);
        return "redirect:/movies";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {
        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);
        ModelAndView model = new ModelAndView();
        model.setViewName("movie/show");
        model.addObject("msg", "movie not found");

        return model;
    }
}