import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

//TODO (MovieDataBase.csv) change genre type to enum, add image directory
//TODO Reformated project folders, add method comments
//TODO (NewAccountPage.java) ensure customer usernames are not repeated
//TODO (MovieDataBase.csv) HEADERS
public class Main {
    public static void main(String[] args) throws IOException {
        //declare fonts
        Font mainText = new Font("Franklin Gothic Book", Font.BOLD ,12);
        // Create the frame (main window)
        JFrame frame = new JFrame("Movies, Reviews, Suggestions & More");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2400, 900); 
        frame.setLocationRelativeTo(null); // centers the frame on screen

        //main panel to hold pages
        JPanel mainPanel = new JPanel(new CardLayout());
        
        //create main pages, pass through mainPanel
        MoviePage moviePage = new MoviePage(mainPanel, mainText);
        LoginPage loginPage = new LoginPage(mainPanel, mainText, moviePage);
        NewAccountPage newAccountPage = new NewAccountPage(mainPanel, mainText);
        

       /* Customer currentCustomer = Session.getCurrentCustomer();
        HomePage homePage = new HomePage(mainPanel, mainText, moviePage, currentCustomer); */
        
        // Add pages to the CardLayout container
        mainPanel.add(loginPage, "Login");
        mainPanel.add(newAccountPage, "NewAccount");
       // mainPanel.add(homePage,"Homepage");
        mainPanel.add(moviePage, "MoviePage");

        // Add to frame and show
        frame.add(mainPanel);
        frame.setVisible(true);

        //show login page first
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "Login"); 
    }

    
    
}