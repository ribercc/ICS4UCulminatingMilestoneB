import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*
* Last edited December 10th 2025
* Last edited by River Chan
*/
public class MovieManager {
    private List<Movie> movieList = new ArrayList<>();

    /**
     * Method to do file I/O for movie.java
     * By Jonathan
     * 
     * @return List<Movie> movieList
     */

    public static ArrayList<Movie> readMovieFile() throws IOException {

        // Intialize variables, arrayLists, file object
        ArrayList<Movie> movieList = new ArrayList<>();
        String movieTtle; // index 0
        String movieDrector; // index 1
        String[] movieCstTop3 = new String[3]; // index 2,3,4
        String[] movieTgs = new String[10]; // index 5,6,7,8,9,10,11,12,13,14
        String movieGnre = ""; // index 15
        String movieBlrb = ""; // index 16
        int movieLngth; // index 17
        int rleaseYear; // index 18
        
        String imagePath; //index 19

        int commas = 0;
        String lineString = "";
        File movieFile = new File("MovieDataBase.csv");

        // if()

        // Verifying the file exists
        if (!movieFile.exists()) {
            System.out.println("This file does not exist. The program will exit.");
            System.exit(0);
        }

        Scanner inFile = new Scanner(movieFile);

        while (inFile.hasNext()) {
            commas = 0;
            lineString = inFile.nextLine();
            for (int i = 0; i < lineString.length(); i++) {
                if (lineString.charAt(i) == ',') {
                    commas++;
                }
            }

            if (commas < 19) {
                System.out.println("This file is formatted incorrectly. The program will exit");
                System.exit(0);
            }

            String[] movieArray = lineString.split(",");
            Genre movieGenre;
            switch (movieArray[15].toLowerCase()) {
                case "horror":
                    movieGenre = Genre.HORROR;
                    break;
                case "romance":
                    movieGenre = Genre.ROMANCE;
                    break;
                case "comedy":
                    movieGenre = Genre.COMEDY;
                    break;
                case "drama":
                    movieGenre = Genre.DRAMA;
                    break;
                case "animation":
                    movieGenre = Genre.ANIMATION;
                    break;
                case "fantasy":
                    movieGenre = Genre.FANTASY;
                    break;
                case "sci-fi":
                    movieGenre = Genre.SCIFI;
                    break;
                case "musical":
                    movieGenre = Genre.MUSICAL;
                    break;
                case "action":
                    movieGenre = Genre.ACTION;
                    break;
                case "adventure":
                    movieGenre = Genre.ADVENTURE;
                    break;
                case "indie":
                    movieGenre = Genre.INDIE;
                    break;
                default:
                    movieGenre = Genre.INDIE;
            }

            if (!isNumeric(movieArray[18]) && !isNumeric(movieArray[17])) {
                System.out.println("This file is formatted incorrectly. The program will exit");
                System.exit(0);
            }
            movieList.add(new Movie(movieArray[0], movieArray[1],
                    new String[] { movieArray[2], movieArray[3], movieArray[4] }, movieGenre, movieArray[16],
                    Integer.parseInt(movieArray[18]), Integer.parseInt(movieArray[17]),
                    new String[] { movieArray[5], movieArray[6], movieArray[7], movieArray[8], movieArray[9],
                            movieArray[10], movieArray[11], movieArray[12], movieArray[13], movieArray[14] }, movieArray[19]));

        }
        return movieList;
    } // end readMovieFile

    /*
     * public MoviePage(List<Movie> mL){
     * 
     * By Jonathan
     * stuff not implemented, but Rob isn't checking yet
     * movieList = mL;
     * }
     */

    public static void printToScreenMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();

        // https://github.com/souvik-ghosh/react-native-create-thumbnail/issues/96
        try {
            movieList = readMovieFile();
        } catch (IOException e) {
            System.out.println(".");
        }
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println("Movie: " + movieList.get(i).getTitle());
            System.out.println("Director: " + movieList.get(i).getDirector());
            System.out.print("Genre: ");
            switch (movieList.get(i).getGenre()) {
                case ROMANCE:
                    System.out.println("Romance");
                    break;
                case COMEDY:
                    System.out.println("Comedy");
                    break;
                case DRAMA:
                    System.out.println("Drama");
                    break;
                case ANIMATION:
                    System.out.println("Animation");
                    break;
                case FANTASY:
                    System.out.println("Fantasy");
                    break;
                case SCIFI:
                    System.out.println("Sci-Fi");
                    break;
                case MUSICAL:
                    System.out.println("Musical");
                    break;
                case ACTION:
                    System.out.println("Action");
                    break;
                case ADVENTURE:
                    System.out.println("Adventure");
                    break;
                case INDIE:
                    System.out.println("Indie");
            }
            System.out.println("Blurb: " + movieList.get(i).getBlurb());
            System.out.println("Top 3 cast: " + Arrays.toString(movieList.get(i).getCastTop3()));
            System.out.println("Tags: " + Arrays.toString(movieList.get(i).getTags()));
            System.out.println("Length: " + movieList.get(i).getLength());
            System.out.println("Release Year: " + movieList.get(i).getReleaseYear());
            System.out.println("-----------------------------------------------------------------------------------");

        }
    }


    
    
    public static boolean isNumeric(String d) {
        return d.matches("-?\\d+(\\.\\d+)?");

    }
    
    /**
     * Movie title returned as an index of the array
    */
    public static int findMovieIndexByTitle(String title) throws IOException {
        ArrayList<Movie> movies = readMovieFile();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1; // not found
    }
    
    /**Returns search
     * by Jonathan
     * @return an array of ints
     */
    public int[] searchMovies(String q){
        int searchConfidence = 0;
        ArrayList<Integer> movieRanks = new ArrayList<>();
        ArrayList<Integer> movies = new ArrayList<>();
        
        String [] query = q.split(" ");
        //checks if titles match
        for(int i = 0; i < movieList.size(); i++){
            
            for(int k = 0; k < query.length; k++){
                searchConfidence = 0;
                if((movieList.get(i).getTitle().toLowerCase().equals(query[k].toLowerCase()))){
                    searchConfidence += 10;
                }
                if(movieList.get(i).getDirector().toLowerCase().equals(query[k].toLowerCase())){
                    searchConfidence += 7;
                }
                for(int j = 0; j < 3; j++){
                    if((movieList.get(i).getCastTop3())[j].toLowerCase().equals(query[k].toLowerCase())){
                        searchConfidence += 5;
                    }
                }
                if(movieList.get(i).getGenreString().toLowerCase().equals(query[k].toLowerCase())){
                    searchConfidence += 3;
                }
            }
            //https://www.digitalocean.com/community/tutorials/java-array-of-arraylist-of-array
            movieRanks.add(searchConfidence);
            
            searchConfidence = 0;
        }   
        
        for(int i = 0; i < movieRanks.size(); i++){
            movies.add(i);
        }
        
        int l = 0;
        int index = 0;
        int[] movieIndexes = new int[3];
        for(int i = 0; i < 3; i++){
            
            for(int k = 0; k < movieRanks.size(); k++){
                if(movieRanks.get(k) >= l){
                    l = movieRanks.get(k);
                }
            }
            index = movieRanks.indexOf(l);
            movieIndexes[i] = movies.get(index);
            movieRanks.remove(index);
            movies.remove(index);
        }
        return movieIndexes;
      
    }
    
    
    /**
     * determines the order of movies based on their average star rating
     * by Eva
     * @return  An array of ints that represent the index of the movies in order of average star rating
     */
    public int [] highestStarMovies () throws IOException{
        //gets the list of all the movies
        ArrayList <Movie> allMovies = readMovieFile();
        double [] averageRatings = new double [allMovies.size()];
        ArrayList <Integer> rankedMovies = new ArrayList <>();
        int [] orderedIndexes;
        boolean added = false;
        
        
        //loops through all
        for (int n = 0; n < allMovies.size(); n++){
            //gets the average rating for each movie
            averageRatings [n] = ReviewManager.calculateAvgStars(n);
        }
        
        
        //works through each movie's rating until its place is found
        for (int n = 0; n< averageRatings.length; n++){
            added = false;
            //progresses through the arraylist looking for when it starts ranking above other ratings
            for (int x = 0; x< rankedMovies.size(); x++){
                
                //if the average of the movie is greater than the value next in the arrayList, and this hasn't been added already
                if ((averageRatings[n] > averageRatings[rankedMovies.get(x)]) && (added == false)){
                    
                    //https://docs.google.com/document/d/1xg7yqoZsrbGnLq8Q38ympRf5GbDw2zfqPX9EZk4jqfw/edit?tab=t.0#heading=h.kk1966kbedef
                    rankedMovies.add (x, n);
                    
                    //change that this value has now been added
                    added = true;
                }
            }
            
            //if the average rating is last, it still must be added
            if (added == false){
                rankedMovies.add(n);
            }
            
        }
        
        //turns the arraylist into an array
        orderedIndexes = new int [rankedMovies.size()];
    
        for (int n = 0; n< orderedIndexes.length; n++){
            orderedIndexes[n] = rankedMovies.get(n);
        }
        
        
        

        return orderedIndexes;
        
    }
    
    /**
     * Method that makes a recommendation algorithm based on the user's most liked/highest reviewed movies (useful for Milestone B requirement, can be commented out if unnecessary)
     * By Daniel
     * @return a new sorted ArrayList
     */
    public ArrayList<Movie> sortFilms() throws IOException {
        ArrayList<Movie> movieList = readMovieFile();
        ArrayList<Double> avgScore = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            double a = 0.0;
            try { a = ReviewManager.calculateAvgStars(i); } catch (IOException e) { a = 0.0; }
            if (Double.isNaN(a) || Double.isInfinite(a)) a = 0.0;
            avgScore.add(a);
        }
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) indices.add(i);
        indices.sort((a, b) -> Double.compare(avgScore.get(b), avgScore.get(a)));
        //the actual sorted list
        ArrayList<Movie> sortedFilms = new ArrayList<>();
        for (int idx : indices) sortedFilms.add(movieList.get(idx));
    
        return sortedFilms;
    }
    
}