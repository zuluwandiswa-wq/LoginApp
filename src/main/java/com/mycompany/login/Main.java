/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

/**
 *
 * @author zuluwandiswa
 */

/**
 * The Main class is used to run and demonstrate the Login application.
 */
public class Main {
    public static void main(String[] args) {
        
        // Create a Login object to access its methods
        Login loginSystem = new Login();

        System.out.println("================================");
        System.out.println("   CHAT APP REGISTRATION TEST   ");
        System.out.println("================================");
        
        // --- 1. Testing Registration ---
        // Register the user with valid details
        System.out.println(loginSystem.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976"));
        
        // Set the name for the welcome message
        loginSystem.setNames("Kyle", "Smith"); 

        System.out.println("\n================================");
        System.out.println("        TESTING LOGIN           ");
        System.out.println("================================");
        
        // --- 2. Testing a SUCCESSFUL login ---
        // Attempt to log in with the correct details we just registered
        boolean loginSuccess = loginSystem.loginUser("kyl_1", "Ch&&sec@ke99!");
        System.out.println("Login successful? " + loginSuccess);
        System.out.println(loginSystem.returnLoginStatus(loginSuccess)); 

        // --- 3. Testing a FAILED login ---
        // Attempt to log in with an incorrect password
        boolean loginFail = loginSystem.loginUser("kyl_1", "WrongPassword123!");
        System.out.println("\nLogin successful? " + loginFail);
        System.out.println(loginSystem.returnLoginStatus(loginFail)); 
        
        System.out.println("================================");
    }
}