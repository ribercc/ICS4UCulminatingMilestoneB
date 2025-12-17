/*
 * Can perform actions related to the Review class
 * Created on Dec 8 by Eva Lafontaine
 * Copied on 12/11/2025 3pm
 */

import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.ArrayList;
import java.io.*;

public class ReviewManager {

    /**
     * Write a review method created by River
     * Compatable with Java Swing
     * Opens a "Review Window" for the selected movie
     * Prompts users to rate from a menu of 0 to 5 stars
     * Text area to allow users to write a review
     * ?Returns a review object
     * ?Param username
     * https://stackoverflow.com/questions/3002787/simple-popup-java-form-with-at-least-two-fields
     * 
     * @param currentMovie a movie object
     */
    public static Review writeAReview(Movie currentMovie, int movieIndex) { //!! Will return a review
        Customer user = Session.getCurrentCustomer();
        String username = user.getUsername();

        String[] ratingOptions = { "☆☆☆☆☆", "★☆☆☆☆", "★★☆☆☆", "★★★☆☆", "★★★★☆", "★★★★★" };
        JComboBox<String> combo = new JComboBox<>(ratingOptions);

        JTextArea ratingTextArea = new JTextArea("Write Something...");
        ratingTextArea.setLineWrap(true);
        ratingTextArea.setWrapStyleWord(true);
        ratingTextArea.setRows(4);
        JScrollPane scrollPane = new JScrollPane(ratingTextArea); // in case the review gets too long

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(combo);
        panel.add(new JLabel("Your Review:"));
        panel.add(scrollPane);

        int result = JOptionPane.showConfirmDialog(null, panel, currentMovie.getTitle(),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            System.out.println(combo.getSelectedItem() + "\n " + ratingTextArea.getText());
            int starIndex = combo.getSelectedIndex(); 
            Star starRating = intToStar(starIndex);
            String textReview = ratingTextArea.getText();
            String movieName = currentMovie.getTitle();

            Review newReview = new Review(username,starRating, textReview, movieName, movieIndex);
            return newReview;

        } else {
            System.out.println("Cancelled");
            return null;
        }
    }

    //! Add a change review method

    /**
     * reads in the Reviews.txt file and stores each piece of info in the correct
     * index of a Review object, stored as an ArrayList;
     * By Eva
     * 
     * @return An ArrayList of Review objects
     */
    public static ArrayList<Review> readReviews() throws IOException {
        File file = new File("Reviews.txt");
        Scanner fileReader = new Scanner(file);
        ArrayList<Review> reviews = new ArrayList<>();
        String line;
        String reviewUserName;// index 0
        int score; // index 1
        String writtenReview; // index 2
        String movieName; // index 3
        int movieIndex; // index 4
       // Review tempReview;

        // makes sure the file exists
        if (file.exists()) {
            // runs through all of the lines of code
            while (fileReader.hasNext()) {
                line = fileReader.nextLine();

                // seperate each index by looking for the *
                String[] temp = line.split("*");

                // set each variable to make up a Review object based on the array
                reviewUserName = temp[0];

                score = Integer.parseInt(temp[1]);
                Star starScore = intToStar(score);

                writtenReview = temp[2];

                movieName = temp[3];

                movieIndex = Integer.parseInt(temp[4]);


                // create a Review object
                Review tempValue = new Review(reviewUserName, starScore, writtenReview, movieName, movieIndex);

                // add it to the ArrayList
                reviews.add(tempValue);
            }
        }

        else {
            System.out.println("This file doesn't exist.");
        }

        fileReader.close();

        return reviews;
    }

    /**
     * River
     * Converts the data in a review object to a String review
     * !needs to be movename, username, star, written text
     * @param currentReview
     * @return
     */
    public static String reviewToCSV(Review currentReview){
        Star star = currentReview.getScore();
        int starValue = starToInt(star);

        String review = currentReview.getReviewText();

        StringBuilder reviewString = new StringBuilder();
        reviewString.append(currentReview.getMovieName()).append("*"); //moviename
        reviewString.append(currentReview.getUsername()).append("*"); //username
        reviewString.append(starValue).append("*").append(review); //star + written review;
        return reviewString.toString();
    }

    /**
     * River 
     * Saves a review to reviews.csv
     * Takes the review, converts to a format to be used by the csv file
     * 
     * @param r review to be saved
     * @throws IOException
     */
    public static void saveReview (Review r) throws IOException {
        FileWriter fw = new FileWriter("Reviews.csv", true);
        PrintWriter reviewFile = new PrintWriter(fw);

        reviewFile.println(reviewToCSV(r));
        reviewFile.close();
    }

    /**
     * uses all individual star ratings for a movie to calculate its average star
     * rating
     * by Eva
     * 
     * @param movieName The name of the movie that star rating is being averaged
     */
    public static double calculateAvgStars(int movieIndex) throws IOException {

        ArrayList<Review> allReviews = readReviews();
        double totalPoints = 0;
        int counter = 0;
        double average;

        // goes through all the reviews
        for (int n = 0; n < allReviews.size(); n++) {

            // looks to match for the movie being looked for
            if (allReviews.get(n).getMovieIndex() == movieIndex) {
                // counts up the points and num of reviews averaged
               //? totalPoints += allReviews.get(n).getScore();
                counter++;
            }
        }

        // calculate the average to two decimal places
        average = twoDecimals(totalPoints / counter);

        return average;
    }

    /**
     * rounds the double to two decimal places
     * by Eva (reused from previous projects)
     * 
     * @param price The orignal decimal that is to be rounded
     * @return The rounded double
     */
    public static double twoDecimals(double original) {
        return ((original * 100 + 0.5) - ((original * 100 + 0.5) % 1)) / 100;

    }

    /**
     * Switch statements for Stars to int values (for GUI code)
     * Created by River 12/11/2025
     * 
     * @param Star starRating
     * @return int rating
     */

    public static int starToInt(Star starRating) {
        int numericRating = 0;
        switch (starRating) {
            case NOSTAR:
                numericRating = 0;
                break;
            case ONESTAR:
                numericRating = 1;
                break;
            case TWOSTAR:
                numericRating = 2;
                break;
            case THREESTAR:
                numericRating = 3;
                break;
            case FOURSTAR:
                numericRating = 4;
                break;
            case FIVESTAR:
                numericRating = 5;
                break;
            default:
                numericRating = 0;
        }

        return numericRating;
    }

    /**
     * Switch statement for an int to a Star value
     * Created by River 12/11/2025
     * 
     * @param int star rating as an int
     * @return Star the enum value of star
     */
    public static Star intToStar(int numericRating) {
        Star starRating;
        switch (numericRating) {
            case 0:
                starRating = Star.NOSTAR;
                break;
            case 1:
                starRating = Star.ONESTAR;
                break;
            case 2:
                starRating = Star.TWOSTAR;
                break;
            case 3:
                starRating = Star.THREESTAR;
                break;
            case 4:
                starRating = Star.FOURSTAR;
                break;
            case 5:
                starRating = Star.FIVESTAR;
                break;
            default:
                starRating = Star.NOSTAR;
        }

        return starRating;
    }
}