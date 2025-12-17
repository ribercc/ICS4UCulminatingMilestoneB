/*
* Class for Homepage GUI, displays panels for the reviews, recommended movies and blog pages
* Has a button to view a "movie page"
* Created by River Chan
* Last edited November 26th 2025
* Last edited by: River Chan
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class HomePage extends JPanel {
    
    private MoviePage moviePage;
    private JPanel mainPanel;
    private ArrayList<Movie> moviesList = MovieManager.readMovieFile();

    public HomePage (JPanel mainPanel, Font mainText, MoviePage moviePage, Customer currentCustomer) throws IOException{
        if (currentCustomer == null) {
            throw new IllegalStateException("Homepage requires a logged-in customer");
        }
        setLayout(null);
        setBackground(Color.WHITE);
        this.mainPanel = mainPanel;
        this.moviePage = moviePage;
        
        //panel for recommended movies, display movie images as buttons here!
        JPanel recommendedPanel = new JPanel();
        recommendedPanel.setBackground(Color.WHITE);
        recommendedPanel.setBounds(150, 100, 900, 350);
        recommendedPanel.setBorder(BorderFactory.createTitledBorder("Recommended movies")); //change font and location
        recommendedPanel.setLayout(new GridLayout(1, 4, 20, 0));
        add(recommendedPanel);
        
        //!shows unique top 4 (favorite movies) this should be RECOMENDED
        String[] favMovies = currentCustomer.getFavouriteMovies(); 
        int[] recommendedMovies = new int[favMovies.length]; //*should be 4

        //get the int of the movies in the favorites (This should be re-used for the favorites)
        //recomended movies will be an array of ints when parsed in, so no conversion will be necessecary 

        for (int i = 0; i < favMovies.length; i++) {
            recommendedMovies[i] = MovieManager.findMovieIndexByTitle(favMovies[i]);
        }

        //get the movie from the index, display as a button
        for (int i = 0; i < 4; i++) {
            int movieIndex = recommendedMovies[i]; 

            if (movieIndex == -1){
                continue;
            }
            Movie currentMovie = moviesList.get(movieIndex);

            JButton movieButton = new JButton();
            movieButton.setBorderPainted(true);
            movieButton.setFocusPainted(false);
            movieButton.setContentAreaFilled(true);
            movieButton.setPreferredSize(new Dimension(210, 350));

            // Load and scale the image
            ImageIcon rawImage = new ImageIcon("Posters/" + currentMovie.getFilePath());
            Image scaledImage = rawImage.getImage().getScaledInstance(210, 350, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(scaledImage);

            // Set the image as the button icon
            movieButton.setIcon(imageIcon); 

            //goes to the selected movie page
            movieButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent goToRecommendedMovies) {
                    moviePage.loadMovie(movieIndex); // movie index
                    showMoviePage();
                }
            });

            recommendedPanel.add(movieButton);
        }
        
        //! Top movies reuse code from the recomended
        //reviews from recently watched movies from other people
        JPanel topMoviePanel = new JPanel();
        topMoviePanel.setBackground(Color.WHITE);
        topMoviePanel.setBounds(150, 500, 900, 200);
        topMoviePanel.setBorder(BorderFactory.createTitledBorder("Current Top Movies")); //change font and location
        add(topMoviePanel);
        
        
        //potential panel for blog posts go here
        //?Maybe change this to reviews actually
        JPanel blogPanel = new JPanel();
        blogPanel.setBackground(Color.white);
        blogPanel.setBounds(1100, 100, 300, 600);
        blogPanel.setBorder(BorderFactory.createTitledBorder("Blogs and Discussions"));
        add(blogPanel);
        
        //index panel with different menu options
        JPanel indexPanel = new JPanel();
        indexPanel.setLayout(null);
        indexPanel.setBackground(Color.WHITE);
        indexPanel.setBounds(150, 730, 900, 50);
        indexPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(indexPanel);
        
        //centers added buttons
        indexPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        
        //button for watchlist page
        JButton watchListButton = new JButton("Watchlist");
        watchListButton.setFont(mainText);
        watchListButton.setOpaque(false);
        watchListButton.setContentAreaFilled(false);
        watchListButton.setBorderPainted(false);
        watchListButton.setForeground(Color.BLACK);
       
        //button for favorites page
        JButton favoritesButton = new JButton("Favorites");
        favoritesButton.setFont(mainText);
        favoritesButton.setOpaque(false);
        favoritesButton.setContentAreaFilled(false);
        favoritesButton.setBorderPainted(false);
        favoritesButton.setForeground(Color.BLACK);
        
        //button for review page/logging a movie
        JButton reviewButton = new JButton("Write a Review");
        reviewButton.setFont(mainText);
        reviewButton.setOpaque(false);
        reviewButton.setContentAreaFilled(false);
        reviewButton.setBorderPainted(false);
        reviewButton.setForeground(Color.BLACK);
        
        //account settings page
        JButton accountSettingsButton = new JButton("Account Settings");
        accountSettingsButton.setFont(mainText);
        accountSettingsButton.setOpaque(false);
        accountSettingsButton.setContentAreaFilled(false);
        accountSettingsButton.setBorderPainted(false);
        accountSettingsButton.setForeground(Color.BLACK);
        
        //!temporary display movies button here
       /* JButton moviePageButton = new JButton("Movies");
        moviePageButton.setFont(mainText);
        moviePageButton.setOpaque(false);
        moviePageButton.setContentAreaFilled(false);
        moviePageButton.setBorderPainted(false);
        moviePageButton.setForeground(Color.BLACK); */

        //add all buttons to the index panel
        indexPanel.add(watchListButton);
        indexPanel.add(favoritesButton);
        indexPanel.add(reviewButton);
        indexPanel.add(accountSettingsButton);
       // indexPanel.add(moviePageButton);
        
        //actionlistener for movies button, directs to a "movie page" -> this is a blank template, will edit this later
      /*  moviePageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent goToMovies) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "MoviePage");
            }
        }); */

    }

    private void showMoviePage() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "MoviePage");
    }
}