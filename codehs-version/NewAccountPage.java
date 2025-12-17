/*
* GUI page for creating an account
* Directs users to enter their desired username and password
* Prompts user to select certain favorite movie genres
* Creates a new customer and adds their information (username, password & genres) to customer.csv
*
* Created by River Chan
* Last edited: November 26th 2025
* Last edited by: River Chan
*/

//TODO make sure that users do not enter usernames that ALREADY EXIST
//TODO make sure that the user selects at least ONE favorite genre

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Scanner;

public class NewAccountPage extends JPanel{
    private String newUsername;
    private char[] newPassword;

    public NewAccountPage (JPanel mainPanel, Font mainText){
        setLayout(null);
        setBackground(Color.WHITE);
        int centeredPx = 700;
        int listSpacing = 210;

        //Make this much smaller later 
        JButton backButton = new JButton("Back to Login");
        backButton.setFont(mainText);
        backButton.setBounds(675, 450, 200, 25);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.BLACK);
        add(backButton);

        //Back to login page
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "Login"); 
            }
        });

        

        //Create a username
        JLabel usernameLabel = new JLabel("Create a username:");
        usernameLabel.setBounds(580, 300, 120, 30);
        usernameLabel.setFont(mainText);
        add(usernameLabel);

        JTextField usernameTextbox = new JTextField();
        usernameTextbox.setBounds(700, 300, 150, 30);
        add(usernameTextbox);

        //Create a password
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(600, 350, 100, 30);
        passwordLabel.setFont(mainText);
        add(passwordLabel);

        JPasswordField passwordTextbox = new JPasswordField();
        passwordTextbox.setBounds(700, 350, 150, 30);
        add(passwordTextbox);


        // * Labels for favorite movie genres
        //TODO: edit the appearence of the checkboxes

        JLabel selectSomeFavorites = new JLabel("Select some favorite movie genres");
        selectSomeFavorites.setFont(mainText);
        selectSomeFavorites.setBounds(centeredPx - 20, listSpacing - 20, 250,50);
        selectSomeFavorites.setVisible(false);
        add(selectSomeFavorites);

        
        JCheckBox horrorCheckBox = new JCheckBox("Horror");
        horrorCheckBox.setBounds(centeredPx,listSpacing + 20,250,50); //fix bounds
        horrorCheckBox.setBackground(Color.white);
        horrorCheckBox.setVisible(false);
        add(horrorCheckBox);

        JCheckBox romanceCheckBox = new JCheckBox("Romance");
        romanceCheckBox.setBounds(centeredPx, listSpacing + 60,250,50); //fix bounds
        romanceCheckBox.setBackground(Color.white);
        romanceCheckBox.setVisible(false);
        add(romanceCheckBox);

        JCheckBox comedyCheckBox = new JCheckBox("Comedy");
        comedyCheckBox.setBounds(centeredPx, listSpacing + 100,250,50); //fix bounds
        comedyCheckBox.setBackground(Color.white);
        comedyCheckBox.setVisible(false);
        add(comedyCheckBox);

        JCheckBox dramaCheckBox = new JCheckBox("Drama");
        dramaCheckBox.setBounds(centeredPx, listSpacing + 140,250,50); //fix bounds
        dramaCheckBox.setBackground(Color.white);
        dramaCheckBox.setVisible(false);
        add(dramaCheckBox);

        JCheckBox indieCheckBox = new JCheckBox("Indie");
        indieCheckBox.setBounds(centeredPx, listSpacing + 180,250,50); //fix bounds
        indieCheckBox.setBackground(Color.white);
        indieCheckBox.setVisible(false);
        add(indieCheckBox);



        // * Additional labels for the Account Creation Page

        JLabel errorMessage = new JLabel("Invalid Data, please try again");
        errorMessage.setBounds(695, 250, 200, 30);
        errorMessage.setFont(mainText);
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        add(errorMessage);

        JLabel errorMessageUsername = new JLabel("Username is unavailable");
        errorMessageUsername.setBounds(695, 240, 200, 30);
        errorMessageUsername.setFont(mainText);
        errorMessageUsername.setForeground(Color.RED);
        errorMessageUsername.setVisible(false);
        add(errorMessage);


        JButton confirmSelection = new JButton("Confirm Selection");
        confirmSelection.setBounds(700,480,150,25);
        confirmSelection.setVisible(false);
        confirmSelection.setFont(mainText);
        confirmSelection.setForeground(Color.BLACK);
        confirmSelection.setBackground(Color.PINK);
        add(confirmSelection);

        
        
        JButton proceedNewAccount = new JButton("Proceed");
        proceedNewAccount.setFont(mainText);
        proceedNewAccount.setBounds(700, 400, 150, 25);
        proceedNewAccount.setBackground(Color.PINK); // Can be changed later
        add(proceedNewAccount);

        proceedNewAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if ((!usernameTextbox.getText().isEmpty()) && (passwordTextbox.getPassword().length > 0)) {
                    newUsername = usernameTextbox.getText(); // make sure that usernames do not duplicate , users are stored here
                    newPassword = passwordTextbox.getPassword();
                    try {
                    boolean existingCustomer = CustomerManager.checkCustomerExists(newUsername);
                        if(existingCustomer == false){
                            // hides the username and password labels
                            usernameLabel.setVisible(false);
                            usernameTextbox.setVisible(false);
                            passwordLabel.setVisible(false);
                            passwordTextbox.setVisible(false);
                            backButton.setVisible(false);
                            errorMessage.setVisible(false);
                            errorMessageUsername.setVisible(false);

                            remove(proceedNewAccount); // <-- remove button instead of hiding
                            revalidate();
                            repaint();

                            // shows the checkboxes
                            selectSomeFavorites.setVisible(true);
                            horrorCheckBox.setVisible(true);
                            romanceCheckBox.setVisible(true);
                            comedyCheckBox.setVisible(true);
                            dramaCheckBox.setVisible(true);
                            indieCheckBox.setVisible(true);
                            confirmSelection.setVisible(true);
                        }
                        //duplicate username;
                        else {
                            errorMessageUsername.setVisible(true);
                            usernameTextbox.setText(""); // clears field
                            passwordTextbox.setText("");
                        }
                    }
                    catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel,
                            "Failed to check customer: " + ex.getMessage(),
                            "I/O Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                //if the username and password field have not be filled
                else {
                    errorMessage.setVisible(true);
                    usernameTextbox.setText(""); // clears field
                    passwordTextbox.setText("");
                }
            }
        });
    
        //! Finalizes and creates new account information
        confirmSelection.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                boolean existingCustomer = CustomerManager.checkCustomerExists(newUsername);
                if(existingCustomer == false){
                        // TODO this will be changed to include more genres later
                        boolean[] genreBoolean = new boolean[5]; // genres that have been selected

                        genreBoolean[0] = horrorCheckBox.isSelected();
                        genreBoolean[1] = romanceCheckBox.isSelected();
                        genreBoolean[2] = comedyCheckBox.isSelected();
                        genreBoolean[3] = dramaCheckBox.isSelected();
                        genreBoolean[4] = indieCheckBox.isSelected();

                        ArrayList<Genre> selectedGenres = setProfileGenres(genreBoolean);

                        // create a new account
                        Customer newAccount = new Customer(newUsername, newPassword, selectedGenres);
                        // * Print the customer to the console, this can be deleted later, just for
                        // testing
                        System.out.println(newAccount.prepForCSV());

                        // File IO Catch
                        try {
                            saveAccount(newAccount);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(mainPanel,
                                    "Failed to save account: " + ex.getMessage(),
                                    "I/O Error", JOptionPane.ERROR_MESSAGE);
                            return; // or handle recovery
                        }

                        // take the user to the homepage
                        CardLayout cl = (CardLayout) mainPanel.getLayout();
                        cl.show(mainPanel, "Homepage");
                }
                else {
                    System.out.println("This username already exists");
                    //! ADD A PROPER CATCH
                }
            
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(mainPanel,
                        "Failed to check customer: " + ex.getMessage(),
                        "I/O Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    }

    //prints the NEW customer to Customers.csv
    //! Potentially move this to customer manager? or maybe have an update customer method in there instead
    public static void saveAccount(Customer c) throws IOException {
        FileWriter fw = new FileWriter("Customers.csv", true); // append mode
        PrintWriter customerFile = new PrintWriter(fw);

        customerFile.print(c.prepForCSV());
        customerFile.close();
    }

    //convert selected genre enum to STRING
    public ArrayList<Genre> setProfileGenres(boolean[] genres){ //convert to string
       ArrayList<Genre> selected = new ArrayList <> ();

       if (genres[0]) {
           selected.add(Genre.HORROR);
       }
       if (genres[1]) {
           selected.add(Genre.ROMANCE);
       }
       if (genres[2]) {
           selected.add(Genre.COMEDY);
       }
       if (genres[3]) {
           selected.add(Genre.DRAMA);
       }
       if (genres[4]) {
           selected.add(Genre.INDIE);
       }

       return selected;
        
    }

}