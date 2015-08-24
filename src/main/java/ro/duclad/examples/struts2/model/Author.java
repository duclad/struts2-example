package ro.duclad.examples.struts2.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by duclad on 8/23/15.
 */
public class Author implements Serializable{

    private String id;
    private String name;
    private String biography;
    private String miniBio;
    private String imgSrc;
    private String language;
    private Calendar joinedOn;


    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Calendar getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(Calendar joinedOn) {
        this.joinedOn = joinedOn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMiniBio() {
        return miniBio;
    }

    public void setMiniBio(String miniBio) {
        this.miniBio = miniBio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
