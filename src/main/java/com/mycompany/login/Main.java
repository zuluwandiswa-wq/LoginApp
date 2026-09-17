/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

/**
 *
 * @author zuluw
 */
        public class Main {
        public static void main(String[] args) {
        Login loginSystem = new Login();

        System.out.println("--- 1. Testing Registration ---");
        // Register the user and save their name
        System.out.println(loginSystem.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976"));
        loginSystem.setNames("Kyle", "Smith"); // Setting the name for the welcome message

        System.out.println("\n--- 2. Testing Login ---");
        
        // Test a SUCCESSFUL login (Correct details)
        boolean loginSuccess = loginSystem.loginUser("kyl_1", "Ch&&sec@ke99!");
        System.out.println("Login successful? " + loginSuccess);
        System.out.println(loginSystem.returnLoginStatus(loginSuccess)); // Prints the welcome message

        // Test a FAILED login (Wrong password)
        boolean loginFail = loginSystem.loginUser("kyl_1", "WrongPassword123!");
        System.out.println("\nLogin successful? " + loginFail);
        System.out.println(loginSystem.returnLoginStatus(loginFail)); // Prints the error message
    }
        }