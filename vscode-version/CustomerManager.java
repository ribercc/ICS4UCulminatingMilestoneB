/*
 * Allows for reading in customers efficiently to be used for other aspects of the code, such as log in, recommendations algorithm, and reviews
 * Created on: Nov 19, 2025
 * Last edited on: Nov 26, 2025
 * Last edited by Eva Lafontaine
 */


import java.util.*;
import java.io.*;

public class CustomerManager {
    
 

    //load the list of customers
    public static ArrayList<Customer> loadCustomers() throws IOException {
        return null;
    }
    // save and update customer data
     public static void saveAccount(Customer c) throws IOException {
        FileWriter fw = new FileWriter("Customers.csv", true); // append mode
        PrintWriter customerFile = new PrintWriter(fw);

        customerFile.print(c.prepForCSV()); //! Change to a new method (set to csv)
        customerFile.close();
    }

    //* Eva is working on this right now 12.16.2025 */
    public static String customerToCSV(Customer c) throws IOException {
        StringBuilder customerString = new StringBuilder();

        //username + password
        customerString.append("\n" + c.getUsername()).append(","); //append(/*PASSWORD */)

        //favorite genres (converts ENUM to String)

        //favorite movies

        //liked tags

        //watchlist

        //separator

        return customerString.toString();

    }

    //public static void updateCSV(Customer c) throws IOException
    //find a customer for the profile
    public static Customer findCustomer(String username, char[] password) throws IOException {
        return null;
    }
    
    
    
    /**
     * Eva
     * Returns the information about a specific customer in a Customer object
     * @param inputUsername: the username of the customer that is being looked for
     * @param inputPassword: the password of the customer that is being looked for
     * @return: all of the matching customers information stored in a Customer object
     */
    public static Customer readCustomer (String inputUsername, char [] inputPassword) throws IOException {
        File file = new File ("Customers.csv");
        Scanner fileReader = new Scanner (file);
        String line;
        String [] tempLine1;
        String username = " ";
        char [] password = new char [0];
        String [] splitGenres;
        ArrayList <Genre> genres = new ArrayList <>();
        String [] favMovies = new String [4]; //?four favorite movies for now?
        String [] splitMovieTags;
        ArrayList <String> movieTags = new ArrayList <>();
        String [] splitWatchlist;
        ArrayList <String> watchlist = new ArrayList<>();
        
        if (file.exists()){
            while (fileReader.hasNext()){
                for (int x = 0; x < 6; x++){
                    //username and password
                    if (x == 0){
                        line = fileReader.nextLine();
                        tempLine1 = line.split(",");
                        
                        username = tempLine1[0];
                        
                        password = new char [tempLine1 [1].length()];
                
                        for (int n = 0; n<tempLine1[1].length(); n++){
                            password[n] = tempLine1[1].charAt(n);
                        }
                    }
                    
                    //liked genres
                    else if (x == 1){
                        line  = fileReader.nextLine();
                        splitGenres = line.split(",");
                        for (int n = 0; n <splitGenres.length; n++){
                            if (splitGenres[n].equalsIgnoreCase("HORROR")){
                                genres.add(Genre.HORROR);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("ROMANCE")){
                                genres.add(Genre.ROMANCE);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("COMEDY")){
                                genres.add(Genre.COMEDY);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("DRAMA")){
                                genres.add(Genre.DRAMA);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("ANIMATION")){
                                genres.add(Genre.ANIMATION);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("FANTASY")){
                                genres.add(Genre.FANTASY);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("SCIFI")){
                                genres.add(Genre.SCIFI);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("MUSICAL")){
                                genres.add(Genre.MUSICAL);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("ACTION")){
                                genres.add(Genre.ACTION);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("ADVENTURE")){
                                genres.add(Genre.ADVENTURE);
                            }
                            else if (splitGenres[n].equalsIgnoreCase("INDIE")){
                                genres.add(Genre.INDIE);
                            }
                        }
                    }
                    
                    //favourite movies
                    else if (x==2){
                        line = fileReader.nextLine();
                        
                        favMovies = line.split(",");
                        
                    }
                    
                    //movie tags
                    else if (x==3){
                        line = fileReader.nextLine();
                        
                        //gets rid of []
                        line = line.substring (1, line.length()-1);
                        
                        splitMovieTags = line.split(",");
                        
                        for (int n = 0; n < splitMovieTags.length; n++){
                            movieTags.add(splitMovieTags[n]);
                        }
                    }
                    
                    //watchlist
                    else if (x==4){
                        line = fileReader.nextLine();
                        
                        //gets rid of []
                        line = line.substring (1, line.length()-1);
                        
                        splitWatchlist = line.split(",");
                        
                        for (int n = 0; n < splitWatchlist.length; n++){
                            watchlist.add(splitWatchlist[n]);
                        }
                    }
                    // reads ------ (just needed to skip by it)
                    else {
                        line = fileReader.nextLine();
                    }
                }
                
                if (inputUsername.equals(username) && Arrays.equals(inputPassword, password)) {
                    Customer foundCustomer = new Customer(username, password, genres, favMovies, movieTags, watchlist);
                    return foundCustomer;
                }
                
                
            }
            
            fileReader.close();
        }
        else {
            System.out.println("This file doesn't exist.");
        }
        //returns a default Customer if anything else went wrong during the reading process
        System.out.println("It looks like this customer doesn't exist.");
        Customer blank = new Customer(inputUsername, inputPassword);
        return blank;
    }
        
    /**
     * Eva
     * Checks to see if the username exists
     * @param inputUsername: the username of the customer that is being looked for
     * @return: a boolean 
     */
    public static boolean checkCustomerExists (String inputUsername) throws IOException {
        File file = new File ("Customers.csv");
        Scanner fileReader = new Scanner (file);
        String line;
        String [] tempLine1;
        String username = " ";
        
        
        if (file.exists()){
            while (fileReader.hasNext()){
                for (int x = 0; x < 6; x++){
                    //username and password
                    if (x == 0){
                        line = fileReader.nextLine();
                        tempLine1 = line.split(",");
                        
                        username = tempLine1[0];
                    }
                    
                    //skips liked genres
                    else if (x == 1){
                        line  = fileReader.nextLine();
                    }
                    
                    //skips favourite movies
                    else if (x==2){
                        line = fileReader.nextLine();
                    }
                    
                    //skips movie tags
                    else if (x==3){
                        line = fileReader.nextLine();
                    }
                    
                    //skips watchlist
                    else if (x==4){
                        line = fileReader.nextLine();
                    }
                    // reads ------ (just needed to skip by it)
                    else {
                        line = fileReader.nextLine();
                    }
                }
                
                if (inputUsername.equals(username)){
                    return true;
                    
                }
                
                
            }
            
            fileReader.close();
        }
        else {
            System.out.println("This file doesn't exist.");
        }
        //returns a default Customer if anything else went wrong during the reading process
        System.out.println("It looks like this username doesn't exist.");
        return false;
    }
        
        
    /**
     * Eva
     * Checks to see if the customers credentials are correct
     * @param inputUsername: the username of the customer that is being looked for
     * @param inputPassword: the password of the customer that is being looked for
     * @return: returns a boolean if the log in is successful
     */
    public static boolean correctLogIn (String inputUsername, char [] inputPassword) throws IOException {
        File file = new File ("Customers.csv");
        Scanner fileReader = new Scanner (file);
        String line;
        String [] tempLine1;
        String username = " ";
        char [] password = new char [0];
        
        
        if (file.exists()){
            while (fileReader.hasNext()){
                for (int x = 0; x < 6; x++){
                    //username and password
                    if (x == 0){
                        line = fileReader.nextLine();
                        tempLine1 = line.split(",");
                        
                        username = tempLine1[0];
                        
                        password = new char [tempLine1 [1].length()];
                
                        for (int n = 0; n<tempLine1[1].length(); n++){
                            password[n] = tempLine1[1].charAt(n);
                        }
                    }
                    
                    //skips liked genres
                    else if (x == 1){
                        line  = fileReader.nextLine();
                
                    }
                    
                    //skips favourite movies
                    else if (x==2){
                        line = fileReader.nextLine();
                    }
                    
                    //skips movie tags
                    else if (x==3){
                        line = fileReader.nextLine();
                    }
                    
                    //skips watchlist
                    else if (x==4){
                        line = fileReader.nextLine();
                    }
                    // reads ------ (just needed to skip by it)
                    else {
                        line = fileReader.nextLine();
                    }
                }
                
                if (inputUsername.equals(username) && Arrays.equals(inputPassword, password)) {
                    return true;
                }
                
                
            }
            
            fileReader.close();
        }
        else {
            System.out.println("This file doesn't exist.");
        }
        //returns a default Customer if anything else went wrong during the reading process
        System.out.println("Incorrect Login.");
        return false;
    }
        
}