// This class manages movie objects
// Created on October 30th by Evangelia LaFontaine
// Programmed by Jonathan Stetler
// Last edited on Nov 12 by Jonathan Stetler
//Movies are formatted in Movie.csv by "Title, Director, Movie Actor [3], 
//Movie Tags [10], Movie Genre, Movie Blurb (ENSURE NO COMMAS), Movie Length (minutes), Release Year,"

import java.util.*;

public class Movie {
    private String movieTitle;
    private String movieDirector;
    private String[] movieCastTop3;
    private String[] movieTags;
    private Genre movieGenre; //= Genre.INDIE;
    private String movieBlurb;
    private int movieLength;
    private int releaseYear;

    private String imagePath;

    /**
     * Default onstructor that initializes all movie fields
     */
    public Movie() {
        movieTitle = "";
        movieDirector = "";
        movieCastTop3 = new String[] { ".", ".", "." };
        movieTags = new String[] { ".", ".", ".", ".", ".", ".", ".", ".", ".", "." };
        Genre movieGenre = Genre.INDIE;
        movieBlurb = "";
        movieLength = 0;
        releaseYear = 0;
        imagePath = "test.jpg";
    }// End Movie

    /**
     * Parameter constructor that initializes all movie fields
     * 
     * @param
     * @param
     */
    public Movie(String mT, String mD, String[] mCT3, Genre mG, String mB, int rY, int mL, String[] tgs, String imgPth) {
        this.movieTitle = mT;
        this.movieDirector = mD;
        this.movieCastTop3 = mCT3;
        this.movieTags = tgs;
        this.movieGenre = mG;
        this.movieBlurb = mB;
        this.movieLength = mL;
        this.releaseYear = rY;
        this.imagePath = imgPth;
    }// End Movie

   /**
    * Created by River Chan
    * Returns a string (to be used for GUI, potentially)
    */
    public String toString() { 
        return "Movie: " + movieTitle + "\n" +
                "Director: " + movieDirector + "\n" +
                "Genre: " + movieGenre.name() + "\n" +
                "Blurb: " + movieBlurb + "\n" +
                "Top 3 cast: " + Arrays.toString(movieCastTop3) + "\n" +
                "Tags: " + Arrays.toString(movieTags) + "\n" +
                "Length: " + movieLength + "\n" +
                "Release Year: " + releaseYear;
    }   
    public String getTitle() {
        return movieTitle;
    }

    public String getDirector() {
        return movieDirector;
    }

    public Genre getGenre() {
        return movieGenre;
    }

    public String getBlurb() {
        return movieBlurb;
    }

    public String[] getCastTop3() {
        return movieCastTop3;
    }

    public String[] getTags() {
        return movieTags;
    }

    public int getLength() {
        return movieLength;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getFilePath() {
        return imagePath;
    }
}