
/*
 * "username, password"
 * "favorite genres" -> initialized as user creates account, can be changed, will be array
 * "favorite movies" -> top 3 movies, must be 3 (array[3])
 * "liked movie tags" -> will be an arraylist, based on algorithim
 * "watchlist" -> arraylist, set to null
 * "-----" -> separate the customers
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
     * ! CREATING A NEW CUSTOMER
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
     * Create a fully populated customer
     *!overloaded method, READING FROM THE CUSTOMER.CSV
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
     * NEEDED in findCustomer if a customer cannot be found
     * overloaded method
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

    // formats into a string to be pasted to csv when creating a new account
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
    
    /**
     * Preps a customer's data for CSV when the customer is FULLY populated
     * by Eva
     * @return A String of the customers info to be printed
     */
    public String prepForCSVFULL (){
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
        for (int n = 0; n < 3; n++){
            customerString.append(favouriteMovies[n] + "*");
        }
        customerString.append ("\n");
        


        //liked tags
        for (int n = 0;  n<likedTags.size(); n++){
            customerString.append(likedTags.get(n) + ",");
        }
        customerString.append("\n");

        //watchlist
        
        for (int n = 0; n <watchlist.size(); n++){
            customerString.append(watchlist.get(n) + "*");
        }
        customerString.append("\n");

        //separator
        customerString.append("-----");

        return customerString.toString();
    }
    
    /**
     * 
    public boolean ifUserExists() throws IOException{
        File inFile = new File("Customers.csv");
        if(!inFile.exists()){
            System.out.println("This file does not exist. The program will exit.");
            System.exit(0);
        }
        Scanner readFile = new Scanner(inFile);
        while(readFile.hasNext()
    }
    /

    /**
     * gets and returns the userFavourites
     * 
     * @return the String array that contains the users 3 favourite movies
     *
    public String getUserFavourites() {
        return (userFavourites[0] + "," + userFavourites[1] + "," + userFavourites[2]);
    }*/

}