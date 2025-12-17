import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    }

    /*
     * public MoviePage(List<Movie> mL){
     * //Example code to print movie info to screen, obv diff w GUI, and security
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

    /**
     * Search for the index of the movie in ArrayList movies from the title
     * //* This search method is ineffective!!!!!
     * Created by River Chan
     * @param title
     * @return
     * @throws IOException
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


    public static boolean isNumeric(String d) {
        return d.matches("-?\\d+(\\.\\d+)?");

    }
}
