/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.login;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Login class to ensure all validation logic works correctly.
 */
public class LoginTest {

    // Create an instance of Login to test its methods
    Login login = new Login();

    // --- Testing checkUserName ---
    @Test
    public void testUsernameCorrectlyFormatted() {
        // Test Data: "kyl_1" - Should return true (has underscore, 5 chars)
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Test Data: "kyle!!!!!!!" - Should return false (no underscore, too long)
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    // --- Testing checkPasswordComplexity ---
    @Test
    public void testPasswordMeetsComplexity() {
        // Test Data: "Ch&&sec@ke99!" - Should return true (meets all rules)
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        // Test Data: "password" - Should return false (no capital, no number, no special char)
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // --- Testing checkCellPhoneNumber ---
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        // Test Data: "+27838968976" - Should return true (starts with +27, 9 digits follow)
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Test Data: "08966553" - Should return false (no international code)
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // --- Testing registerUser (assertEquals with exact strings) ---
    @Test
    public void testRegisterUserSuccess() {
        String expected = "User registered successfully.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(expected, actual);
    }

    @Test
    public void testRegisterUserBadUsername() {
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testRegisterUserBadPassword() {
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testRegisterUserBadCell() {
        String expected = "Cell phone number incorrectly formatted or does not contain international code.";
        String actual = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(expected, actual);
    }

    // --- Testing loginUser ---
    @Test
    public void testLoginSuccessful() {
        // First register the user so the data is stored in the object
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        // Now test if login works with the same details
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        // First register the user
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        // Now test if login fails with a wrong password
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }
}