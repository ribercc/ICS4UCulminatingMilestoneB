/*
* GUI page for logging into the website
* Allows users to input current login information or create a new account
* Redirects users to the homepage or account creation page, accordingly
*
* Created by River Chan
* Last edited: November 26th 2025
* Last edited by: River Chan
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class LoginPage extends JPanel {
    
    private MoviePage moviePage;

    public LoginPage(JPanel mainPanel, Font mainText, MoviePage moviePage) throws IOException{
        setLayout(null);
        setBackground(Color.WHITE);
        this.moviePage = moviePage;
        
        //text that says "Username: " 
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(600, 300, 100, 30);
        usernameLabel.setFont(mainText);
        add(usernameLabel);
        
        //text field for the username
        JTextField usernameTextbox = new JTextField();
        usernameTextbox.setBounds(700, 300, 150, 30);
        add(usernameTextbox);

        //text that says "Password"
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(600, 350, 100, 30);
        passwordLabel.setFont(mainText);
        add(passwordLabel);

        //password field for the password (hides the characters)
        JPasswordField passwordTextbox = new JPasswordField();
        passwordTextbox.setBounds(700, 350, 150, 30);
        add(passwordTextbox);
        
        //create an account button
        JButton newAccount = new JButton("Create an Account");
        newAccount.setBounds(675, 380, 200, 25);
        newAccount.setFont(mainText);
        newAccount.setOpaque(false);
        newAccount.setContentAreaFilled(false);
        newAccount.setBorderPainted(false);
        newAccount.setForeground(Color.BLACK);
        
        
        //redirects users to the account creation page
        newAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent makeNewAccount) {
                System.out.println("Create new account button has been clicked.");

              CardLayout cl = (CardLayout) mainPanel.getLayout();
                cl.show(mainPanel, "NewAccount");
                //follow create new account information here
            }
        });
        add(newAccount);
        
        //login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(700, 410, 150, 30);
        loginButton.setBackground(new Color(212, 139, 179));

        //error message (multipurpose)
        JLabel errorMessage = new JLabel("Invalid Data, please try again");
        errorMessage.setBounds(695, 250, 200, 30);
        errorMessage.setFont(mainText);
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        add(errorMessage);
        
        //action listener for logging in, runs the pasword and username check
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent login) {
                System.out.println("Login button clicked!");
                
                //text from the text and password fields
                String username = usernameTextbox.getText();
                char[] password = passwordTextbox.getPassword();
                
                //prints to console to check
                System.out.println(username);
                System.out.println(password);
                try {
                    if (CustomerManager.checkCustomerExists(username) && CustomerManager.correctLogIn(username, password)) { //checks if user exists in Customer.csv
                         //checks if password and username match up
                            Customer currentCustomer = CustomerManager.findCustomer(username, password);
                            Session.setCurrentCustomer(currentCustomer);

                            HomePage homePage = new HomePage(mainPanel, mainText, moviePage, currentCustomer);
                            mainPanel.add(homePage, "Homepage");

                            CardLayout cl = (CardLayout) mainPanel.getLayout();
                            cl.show(mainPanel, "Homepage");
            
                    } else {
                        errorMessage.setVisible(true);
                        usernameTextbox.setText("");
                        passwordTextbox.setText("");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    errorMessage.setText("File error. Contact admin."); //missing file etc
                    errorMessage.setVisible(true); 
                }
            }
        });
        add(loginButton);

    }
    
}