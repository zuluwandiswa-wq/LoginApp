/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login;


/**
 *
 * @author zuluwandiswa
 */

/**
 * The Login class handles user registration and authentication.
 * It validates usernames, passwords, and South African cell phone numbers.
 */
public class Login {

    // 1. Variables to store user data when they register
    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private String firstName;
    private String lastName;

    /**
     * Sets the user's first and last name for the welcome message.
     * @param firstName The user's first name
     * @param lastName The user's last name
     */
    public void setNames(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Checks if the username is correctly formatted.
     * @param username The username entered by the user
     * @return true if the username contains an underscore and is no more than 5 characters long
     */
    public boolean checkUserName(String username) {
        // Check if it contains an underscore AND the length is 5 or less
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    } 

    /**
     * Checks if the password meets the complexity requirements.
     * @param password The password entered by the user
     * @return true if the password is at least 8 characters long, contains a capital letter, 
     * a number, and a special character.
     */
    public boolean checkPasswordComplexity(String password) {
        // Regex explanation:
        // (?=.*[A-Z]) -> Must contain at least one uppercase letter
        // (?=.*[0-9]) -> Must contain at least one digit
        // (?=.*[^A-Za-z0-9]) -> Must contain at least one special character
        // .{8,} -> Must be at least 8 characters long
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$";
        return password.matches(regex);
    } 

    /**
     * Checks if the cell phone number is correctly formatted.
     * @param cellNumber The cell phone number entered by the user
     * @return true if the number starts with +27 and is followed by exactly 9 digits.
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        // Regex explanation: 
        // ^\\+27 -> Starts with +27
        // [0-9]{9}$ -> Followed by exactly 9 digits, and nothing else.
        // Reference: Adapted from "Validate Phone Numbers with Country Code" 
        // Available at: https://www.geeksforgeeks.org/dsa/validate-phone-numbers-with-country-code-extension-using-regular-expression/
        String regex = "^\\+27[0-9]{9}$";
        return cellNumber.matches(regex);
    } 

    /**
     * Registers the user by validating all their details and saving them if correct.
     * @param username The entered username
     * @param password The entered password
     * @param cellNumber The entered cell phone number
     * @return A message indicating successful registration or the specific error.
     */
    public String registerUser(String username, String password, String cellNumber) {
        
        // 1. Check the username
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        // 2. Check the password
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        // 3. Check the cell phone number
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        // 4. If all checks pass, save the data to our variables!
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellNumber = cellNumber;
        
        return "User registered successfully.";
    } 

    /**
     * Verifies if the entered login details match the stored registration details.
     * @param username The username entered at login
     * @param password The password entered at login
     * @return true if the details match, false otherwise.
     */
    public boolean loginUser(String username, String password) {
        // Check if the entered details match the stored details
        return username.equals(this.storedUsername) && password.equals(this.storedPassword);
    }

    /**
     * Returns the final login status message.
     * @return A welcome message if logged in, or an error message if not.
     */
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}