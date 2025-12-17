
// Creates and manages Review objects
// Created on October 30th 2025 by Evangelia Lafontaine
// Last edited on December 12th 2025 by Daniel Malungo
//import java.util.Scanner;
//import java.io.*;
//import java.util.ArrayList;

public class Review {
    private String userName;
    private Star score;
    private String reviewText;
    private String movieName;
    private int movieIndex;

    /**
     * Constructor that initializes Review fields movie objects
     * 
     * @param paramUserName   the name of the user in the review
     * @param paramScore      the rating of a movie
     * @param paramReviewText the text of a review
     * @param paramMovieName  the name of the movie
     * @param paramMovieIndex the index of the movie in the database
     */
    public Review(String paramUserName, Star paramScore, String paramReviewText, String paramMovieName,
            int paramMovieIndex) {
        userName = paramUserName;
        score = paramScore;
        reviewText = paramReviewText;
        movieName = paramMovieName;
        movieIndex = paramMovieIndex;
    }// End Review

    /**
     * This method gets the username of the person who made the review
     * 
     * @return the username of the reviewer
     */
    public String getReviewName() {
        return userName;
    }// End getReviewName

    /**
     * This method gets the review score
     * 
     * @return rating given to the movie as a number
     */
    public Star getScore() {
        return score;
    }// End getScore

    /**
     * This method gets the review text
     * 
     * @return the text written in a review
     */
    public String getReviewText() {
        return reviewText;
    }// End getReviewText

    /**
     * This method gets the movie name
     * 
     * @return the name of the movie being reviewed
     */
    public String getMovieName() {
        return movieName;
    }// End getMovieName

    /**
     * This method gets the movie's index
     * 
     * @return the number of the movie in the database
     */
    public int getMovieIndex() {
        return movieIndex;
    }// End getMovieIndex

    public String getUsername(){
        return userName;
    }

   

}