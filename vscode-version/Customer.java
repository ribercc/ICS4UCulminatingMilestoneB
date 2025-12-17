/*
 * Creates a customer object object 
 * includes the username, password, liked genres,liked movies, liked tags, and watchlist
 * Created on: Nov 15, 2025
 * last edited on : Nov 25, 2025
 * Last edited by Eva Lafontaine
 */
import java.util.*;
import java.io.*;

public class Customer {
    // declare fields
    private String username;
    private char[] password;
    private ArrayList <Genre> favouriteGenres;

    private String[] favouriteMovies;
    private ArrayList<String> likedTags;
    private ArrayList <String> watchlist;

    
    /**
     * Method for creating a NEW customer
     * Only favorite genres, username and password are initialized
     * By River
     * 
     * @param username the new username
     * @param password the new password
     * @param favouriteGenres user's favorite genres
     */
    public Customer(String username, char[] password, ArrayList <Genre> favouriteGenres) {
        this.username = username;
        this.password = password;

        // genres selected by checkboxes
        this.favouriteGenres = favouriteGenres;

        // null for now,
        this.favouriteMovies = null;
        this.likedTags = null;
        this.watchlist = null;
    }


    /**
     * Overloaded method for creating a customer object (after being read from the Cusomter.csv)
     * Initializes ALL fields
     * By River
     * 
     * @param username
     * @param password
     * @param favouriteGenres
     * @param favouriteMovies
     * @param likedTags
     * @param watchlist
     * 
     */
    public Customer(String username, char[] password, ArrayList<Genre> favouriteGenres, String[] favouriteMovies,
        ArrayList<String> likedTags, ArrayList<String> watchlist) {
        this.username = username;
        this.password = password;
        this.favouriteGenres = favouriteGenres;
        this.favouriteMovies = favouriteMovies;
        this.likedTags = likedTags;
        this.watchlist = watchlist;
    }
    
    /**
     * Eva
     * Username and password are arguments, but all other data is default
     * NEEDED in readCustomer if a customer cannot be found
     * @param uName is the username of the account being passed in
     * @param uPassword is the user's pasword
     */
     public Customer (String uName, char [] uPassword){
         username = uName;
         password = uPassword;
         favouriteGenres = new ArrayList <>();
         favouriteMovies = new String [3];
         likedTags = new ArrayList <>();
         watchlist = new ArrayList <>();
     }

    public String getUsername() {
        return username;

    }

    public char[] getPassword() {
        return password;

    }

    public ArrayList<Genre> getFavouriteGenres() {
        return favouriteGenres;

    }

    public String[] getFavouriteMovies() {
        return favouriteMovies;

    }

    public ArrayList<String> getLikedTags() {
        return likedTags;

    }

    public ArrayList <String> getWatchlist() {
        return watchlist;

    }

    /**
     * Creates a formatted string to be used in customer.csv
     * By River
     * @return the string for the CSV
     */
    public String prepForCSV() {
        
        StringBuilder customerString = new StringBuilder();

        //* first line (username, password)
        customerString.append("\n" + username).append(",").append(new String(password)).append("\n");

        //* favorite genres (converts ENUM to String), sets to NULL if none selected
        if (favouriteGenres == null || favouriteGenres.size() == 0) { //if no genres have been selected
            customerString.append("null\n");
        } 

        else {
            int i = 0;
            for (Genre currentGenre : favouriteGenres) {
                customerString.append(currentGenre.name());
                if (i < favouriteGenres.size() - 1) //if there are more genres selected seperate by comma
                    customerString.append(",");
                i++;
            }
            customerString.append("\n");
        }

        //favorite movies
        customerString.append("null\n");

        //liked tags
        customerString.append("null\n");

        //watchlist
        customerString.append("null\n");

        //separator
        customerString.append("-----");

        return customerString.toString();
    }
    
}