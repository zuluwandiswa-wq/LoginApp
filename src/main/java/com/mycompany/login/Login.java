/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login;


/**
 *
 * @author zuluw
 */
public class Login {

    // 1. Variables to store user data
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String firstName;
    private String lastName;

    // 2. Method to set names (for the final welcome message)
    public void setNames(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // 3. Check Username Method
    public boolean checkUserName(String username) {
        // Must contain an underscore and be 5 characters or less
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    } 

    // 4. Check Password Method
    public boolean checkPasswordComplexity(String password) {
        // Regex: At least 8 chars, 1 uppercase, 1 number, 1 special char
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$";
        return password.matches(regex);
    } 

    // 5. Check Cell Phone Method
    public boolean checkCellPhoneNumber(String cellNumber) {
        // Regex: Starts with +27, followed by exactly 9 digits
        // Reference: Standard South African phone number formats (Regex101/StackOverflow)
        String regex = "^\\+27[0-9]{9}$";
        return cellNumber.matches(regex);
    } 

    // 6. Register User Method
    public String registerUser(String username, String password, String cellNumber) {
        
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        // If all checks pass, save the data!
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellNumber = cellNumber;
        
        return "User registered successfully.";
    } 

    // 7. Method to verify login details
    public boolean loginUser(String username, String password) {
        // Check if the entered details match the stored details
        return username.equals(this.storedUsername) && password.equals(this.storedPassword);
    }

    // 8. Method to return the final login message
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}