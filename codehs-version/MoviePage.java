/*
* Class to display a "movie", will show movie image, title and all other information pertaining to the current movie object
* Currently has buttons that direct user back to the homepage or a button that prints all of the current movie information to the console
* //!This print movie button will be changed, it's just for testing right now
*
* Created by River Chan
* Last edited: December 16th 2025
* Last edited by: River Chan
*/

//TODO Add movie display information, incorporate movie images?
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class MoviePage extends JPanel{

    private int selectedMovieIndex;
    private ArrayList<Movie> moviesList = MovieManager.readMovieFile(); // arraylist of all current movies in the database
    private Movie currentMovie = moviesList.get(selectedMovieIndex);

    private JLabel titleLabel;
    private JLabel directorLabel;
    private JLabel yearLabel;
    private JLabel castLabel;
    private JTextArea blurbArea;
    private JLabel posterLabel;
    
    public MoviePage(JPanel mainPanel, Font mainText) throws IOException{
        setLayout(null);
        setBackground(Color.WHITE);

        this.selectedMovieIndex = selectedMovieIndex;

       //Basic buttons to return to homepage
        JButton returnHomepageButton = new JButton("Return to Homepage");
        returnHomepageButton.setBounds(100,800,171,30);
        returnHomepageButton.setFont(mainText);
        add(returnHomepageButton);

        returnHomepageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent goBack) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Homepage");
            }
        });

        //title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(800, 120, 350, 60);
        titlePanel.setBackground(Color.WHITE);

        titleLabel = new JLabel(currentMovie.getTitle(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Franklin Gothic Book", Font.BOLD, 26));

        titlePanel.add(titleLabel);
        add(titlePanel);

        //directors, actors, year released
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.setBounds(800, 200, 250, 100);
        infoPanel.setFont(mainText);
        //TODO change borders and spacing
        directorLabel = new JLabel("Director: " + currentMovie.getDirector());
        yearLabel = new JLabel("Release Year: " + currentMovie.getReleaseYear());
        castLabel = new JLabel("Cast: " + Arrays.toString(currentMovie.getCastTop3()));
        blurbArea = new JTextArea(currentMovie.getBlurb());

        infoPanel.add(directorLabel);
        infoPanel.add(yearLabel);
        infoPanel.add(castLabel);
        add(infoPanel);

        //blurb box (maybe add director here or make a separate panel)
        JPanel blurbPanel = new JPanel();
        blurbPanel.setLayout(new BorderLayout());
        blurbPanel.setBounds(800, 400, 300, 200);
        blurbPanel.setBackground(Color.WHITE);
        

        blurbArea = new JTextArea(currentMovie.getBlurb());
        blurbArea.setEditable(false);
        blurbArea.setLineWrap(true);
        blurbArea.setWrapStyleWord(true);
        blurbPanel.add(blurbArea, BorderLayout.CENTER);
        add(blurbPanel);


        int rating = 1; //star rating value, switch to customer variable later
        StringBuilder starString = new StringBuilder(5);
        
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                starString.append("★");
            }
            else {
                starString.append("☆");
            }
        }

        String stars = starString.toString();

        JLabel yourRating = new JLabel("Your Rating:");
        yourRating.setBounds(200, 475, 300, 400);
        yourRating.setFont(mainText);
        add(yourRating);

        JLabel starLabel = new JLabel(stars);
        starLabel.setFont(new Font("Serif", Font.PLAIN, 32)); // big stars
        starLabel.setBounds(200, 500, 300, 400);
        add(starLabel);

        JButton writeReview = new JButton("Write a review");
        writeReview.setBounds(280, 800, 171, 30);
        writeReview.setFont(mainText);
        add(writeReview);

        writeReview.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent writeReview) {
                Review movieReview = ReviewManager.writeAReview(currentMovie, selectedMovieIndex);
                try {
                    ReviewManager.saveReview(movieReview);
                } catch (IOException e) {
                    //Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        //movie poster/image
        JPanel moviePoster = new JPanel();
        moviePoster.setBackground(Color.WHITE);
        moviePoster.setBounds(100, 150, 342, 513);
        moviePoster.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        moviePoster.setLayout(null);
        add(moviePoster);

        String imagePath = currentMovie.getFilePath();
        //load image
        ImageIcon rawIcon = new ImageIcon("Posters/" + imagePath); 
        Image scaledImage = rawIcon.getImage().getScaledInstance(342, 513, Image.SCALE_SMOOTH);
        ImageIcon posterIcon = new ImageIcon(scaledImage);

        //add image to the movie poster bounds
        posterLabel = new JLabel(posterIcon);
        posterLabel.setBounds(0, 0, 342, 513);

        
        moviePoster.add(posterLabel);
    }

    /**
     * Method to refresh MoviePage contents
     * Updates all data (title,director,release year etc)
     * ?Will eventually update the reviews too!
     * @param movieIndex the index of the movie in ArrayList MovieList
     */
    public void loadMovie(int movieIndex) {
        this.selectedMovieIndex = movieIndex;
        currentMovie = moviesList.get(movieIndex);

        // Update title
        titleLabel.setText(currentMovie.getTitle());

        // Update director
        directorLabel.setText("Director: " + currentMovie.getDirector());

        // Update year
        yearLabel.setText("Release Year: " + currentMovie.getReleaseYear());

        // Update cast
        castLabel.setText("Cast: " + Arrays.toString(currentMovie.getCastTop3()));

        // Update blurb
        blurbArea.setText(currentMovie.getBlurb());

        // Update poster
        String imagePath = currentMovie.getFilePath();
        ImageIcon rawIcon = new ImageIcon("Posters/" + imagePath);
        Image scaled = rawIcon.getImage().getScaledInstance(342, 513, Image.SCALE_SMOOTH);
        ImageIcon posterIcon = new ImageIcon(scaled);
        posterLabel.setIcon(posterIcon);

        revalidate();
        repaint();
    }
    

}