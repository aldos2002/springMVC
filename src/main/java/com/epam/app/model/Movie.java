package com.epam.app.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "movie")
public class Movie {
    Integer id;
    String name;
    String director;
    String year;

    @XmlElement
    public boolean isNew() {
        return (this.id == null);
    }

    public Integer getId() {
        return id;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    @XmlElement
    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    @XmlElement
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", director=" + director
                + ", year=" + year + "]" + isNew();
    }
}
