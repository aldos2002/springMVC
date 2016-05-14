package com.epam.app.dao;

import com.epam.app.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieDaoImpl implements MovieDao {
    private final Logger logger = LoggerFactory.getLogger(MovieDaoImpl.class);
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Movie findById(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        String sql = "SELECT * FROM movies WHERE id=:id";

        Movie result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, new MovieMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("No result found", e);
        }
        return result;

    }

    @Override
    public List<Movie> findAll() {
        String sql = "SELECT * FROM movies";
        List<Movie> result = namedParameterJdbcTemplate.query(sql, new MovieMapper());

        return result;
    }

    @Override
    public void save(Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO MOVIES(NAME, DIRECTOR, YEAR) "
                + "VALUES ( :name, :director, :year)";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(movie), keyHolder);
        movie.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Movie movie) {
        String sql = "UPDATE MOVIES SET NAME=:name, DIRECTOR=:director, YEAR =:year " + "WHERE id=:id";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(movie));
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM MOVIES WHERE id= :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private SqlParameterSource getSqlParameterByModel(Movie movie) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", movie.getId());
        paramSource.addValue("name", movie.getName());
        paramSource.addValue("director", movie.getDirector());
        paramSource.addValue("year", movie.getYear());

        return paramSource;
    }

    private static final class MovieMapper implements RowMapper<Movie> {

        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setName(rs.getString("name"));
            movie.setDirector(rs.getString("director"));
            movie.setYear(rs.getString("year"));
            return movie;
        }
    }

//    @Override
//    public Movie addMovie(Movie movie, MultipartFile multipartFile) {
//        if (!multipartFile.isEmpty()) {
//            try {
//                byte[] bytes = multipartFile.getBytes();
//                File dir = new File(picsDir);
//                if (!dir.exists()) {
//                    dir.mkdir();
//                }
//                File file = new File(picsDir + File.separator + multipartFile.getOriginalFilename());
//                FileOutputStream fos = new FileOutputStream(file);
//                fos.write(bytes);
//                fos.close();
//                movie.setImagePath(picsUrl + multipartFile.getOriginalFilename());
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
//        em.persist(meal);
//        return meal;
//    }
}