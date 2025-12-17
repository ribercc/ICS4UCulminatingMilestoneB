// Creates and manages Review objects
// Created on October 30th 2025 by Evangelia Lafontaine
// Last edited on December 12th 2025 by Daniel Malungo
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Review {
    
    
    
     //DONT WORK ON THIS!! - River
    
    
    
    private String userName;
    private Star score;
    private String reviewText;
    private String movieName;
    private int movieIndex;

    /**
     * Constructor that initializes Review fields movie objects
     * @param paramUserName the name of the user in the review
     * @param paramScore the rating of a movie
     * @param paramReviewText the text of a review
     * @param paramMovieName the name of the movie
     * @param paramMovieIndex the index of the movie in the database
     */
    public Review(String paramUserName, Star paramScore, String paramReviewText, String paramMovieName, int paramMovieIndex){
        userName = paramUserName;
        score = paramScore;
        reviewText = paramReviewText;
        movieName = paramMovieName;
        movieIndex = paramMovieIndex;
    }// End Review
    
    /**
     * This method gets the username of the person who made the review
     * @return the username of the reviewer
     */
    public String getReviewName(){
        return userName;
    }//End getReviewName
    
    /**
     * This method gets the review score
     * @return rating given to the movie as a number
     */
    public Star getScore(){
        return score;
    }// End getScore
    
    /**
     * Getter method for the movie's index.
     * @return index of the movie's review in its file.
     */
    public int getMovieIndex(){
        return movieIndex;
    }
    
    /**
     * This method gets the review text
     * @return the text written in a review
     */ 
    public String getReviewText(){
        return reviewText;
    }// End getReviewText
    
    /**
     * This method gets the movie name
     * @return the name of the movie being reviewed
     */
    public String getMovieName(){
        return movieName;
    }// End getMovieName
    
    /**
     * Allows the user to make a review, by Daniel
     * Stores the Review to Review.csv
     * @param uName the user's name
     * @param mName the movie's name
     */
   /* public void makeReview(String uName, String mName) throws IOException{
        PrintWriter pw = new PrintWriter("Reviews.csv");
        Scanner kb = new Scanner(System.in);
        MovieManager mm = new MovieManager();
        ArrayList<Movie> moviesList = mm.readMovieFile(); // This line of code  might not have to exist ngl
        String uWriting;
        //The String referenced would still be the movie's name but gotten from the original Movie page.
        //The code between this comment and the next will have to be changed because it doesn't account for the fact that we're working with a GUI. (When converting to a GUI, either ask user to make a review after the rating OR have both the review and rating on the same page, whichever is easier.) 
        
        Star rn = scoreMovie(mName);
        System.out.print("Do you want to write a full review?");
        String answer = kb.nextLine();
        if (answer.equals("yes")) uWriting = writeReview(mName);
        else uWriting = "";
        //creates a Review object
        Review myReview = new Review(uName, rn, uWriting, mName);
        //prints the review to the file
        pw.println(uName + "*" + rn + "*" + uWriting + "*" + mName);
        pw.close();
    } */

    /**
     * Gets the user to write a review.
     */
   /* public String writeReview(String mN){
        Scanner kb = new Scanner(System.in);
        System.out.print("Write your review here:\t");
        String revText = kb.nextLine();
        return revText;
    }*/


    /**
     * Adds a simple prompt to rate the movie, by Daniel
     * Last Edited by River (12/11/2025)
     * @param m the name of the movie being rated
     * @return Star object for the rating
     */
   /* public Star scoreMovie(String m) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Rate " + m + " out of 5 stars: \t");
        int score = kb.nextInt();
        Star movieRating = ReviewManager.intToStar(score);
        
        return movieRating;
    }*/

    /**
     * Allows the user to make changes to their review or score, by Daniel.
     * @param r the review made by the user in the makeReview method.
     * @return the new version of the review. 
     * It will essentially just change the enum type of the score and the text in the file.
     */
   /* public Review changeReview(Review r) throws IOException{
       //This method gets triggered by choosing to edit a certain review. 
        Scanner kb = new Scanner(System.in);
        Star altStar = scoreMovie(r.getMovieName());
        String altText;
        System.out.println("Do you want to update your review text?");
        String ans = kb.nextLine();
        if (ans.equals("yes")) altText = writeReview(r.getMovieName());
        else altText = r.getReviewName();
        Review altReview = new Review(r.getReviewName(), altStar, altText, r.getMovieName());
        return altReview;
    }*/
    
}