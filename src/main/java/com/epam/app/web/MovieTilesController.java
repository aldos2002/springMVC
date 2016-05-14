package com.epam.app.web;

import com.epam.app.model.Movie;
import com.epam.app.model.Role;
import com.epam.app.service.MovieService;
import com.epam.app.validator.MovieFormValidator;
import eu.bitwalker.useragentutils.UserAgent;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
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

    @RequestMapping(value = "/warning")
    public ModelAndView warning(Model model, HttpServletRequest req) {
        logBrowser(req);
        //add session variable
        return new ModelAndView("warning");
    }

    @RequestMapping(value = "/movies")
    public ModelAndView info(Model model, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        //add session variable
        UserAgent userAgent = UserAgent.
                parseUserAgentString(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("User-Agent"));
        if(!userAgent.getBrowser().toString().equals("FIREFOX")) {
            return new ModelAndView("warning");
        }

        req.getSession().setAttribute("role","Administrator");
        model.addAttribute("role", req.getSession().getAttribute("role"));
        model.addAttribute("message", "Message from main controller to info page!");
        model.addAttribute("movies", movieService.findAll());
        logger.info("\"info\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return new ModelAndView(INDEX_VIEW);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public ModelAndView infoMovie(@PathVariable("id") int id, Model model, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        logger.debug("showMovie() id: {}", id);
        Movie movie = movieService.findById(id);
        if (movie == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Movie not found");
        }
        model.addAttribute("movie", movie);
        model.addAttribute("role", req.getSession().getAttribute("role"));
        logger.info("\"infoMovie\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return new ModelAndView(INFO_VIEW);
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public String saveOrUpdateMovie(@ModelAttribute("movieForm") @Validated Movie movie,
                                    @RequestHeader(value="User-Agent", defaultValue="foo") String userAgent,
                                    BindingResult result, Model model, final RedirectAttributes redirectAttributes,
                                    HttpServletRequest req) {
        logBrowser(req);
        long startTime = System.currentTimeMillis();
        logger.debug("saveOrUpdateMovie() : {}", movie);
        model.addAttribute("role", req.getSession().getAttribute("role"));
        if (result.hasErrors()) {
            return "movies/movieform";
        } else {
            if (movie.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "Movie added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Movie updated successfully!");
            }
            movieService.saveOrUpdate(movie);
            logger.info("\"saveOrUpdateMovie\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
            return "redirect:/movies/" + movie.getId();
        }

    }

    @RequestMapping(value = "/movies/add", method = RequestMethod.GET)
    public String showAddMovieForm(Model model, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        model.addAttribute("role", req.getSession().getAttribute("role"));
        logger.debug("showAddMovieForm()");
        Movie movie = new Movie();
        movie.setName("Movie_name");
        movie.setDirector("Movie director");
        movie.setYear("2016");
        model.addAttribute("movieForm", movie);
        model.addAttribute("role", req.getSession().getAttribute("role"));
        logger.info("\"showAddMovieForm\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return "movieform";
    }

    @RequestMapping(value = "/movies/{id}/update", method = RequestMethod.GET)
    public String showUpdateMovieForm(@PathVariable("id") int id, Model model, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        model.addAttribute("role", req.getSession().getAttribute("role"));
        logger.debug("showUpdateMovieForm() : {}", id);
        Movie movie = movieService.findById(id);
        model.addAttribute("movieForm", movie);
        logger.info("\"showUpdateMovieForm\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return "movieform";
    }

    @RequestMapping(value = "/movies/{id}/delete", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") int id, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        logger.debug("deleteMovie() : {}", id);
        movieService.delete(id);
        logger.info("\"deleteMovie\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return "redirect:/movies";
    }

    public String addPoster(@ModelAttribute Movie movie, @RequestParam("poster") MultipartFile poster,
                            HttpServletRequest req) {


        return "redirect:/movies";
    }

    @RequestMapping(value="/view/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public  @ResponseBody Movie getMovie(@PathVariable("id") int id, HttpServletRequest req) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        Movie movie =   movieService.findById(id);
        logger.info("\"getMovie\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return movie;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {
        long startTime = System.currentTimeMillis();
        logBrowser(req);
        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);
        ModelAndView model = new ModelAndView();
        model.setViewName("movie/show");
        model.addObject("msg", "movie not found");
        logger.info("\"handleEmptyData\" implemented in "+ (System.currentTimeMillis()-startTime) + "ms");
        return model;
    }

    //log browser
    private void logBrowser(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        logger.info(userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion());
    }
}