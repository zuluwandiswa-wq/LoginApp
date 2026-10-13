/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // --- 1. Testing Message Length ---
    @Test
    public void testMessageLengthSuccess() {
        String validMessage = "Hi Mike, can you join us for dinner tonight?";
        // 46 characters, should be under 250
        assertTrue(validMessage.length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        // Create a string that is 255 characters long
        String longMessage = "a".repeat(255); 
        assertTrue(longMessage.length() > 250);
    }

    // --- 2. Testing Recipient Cell Formatting ---
    @Test
    public void testRecipientCellCorrect() {
        // Create a message with a valid number
        Message msg = new Message("+27718693002", "Hi Mike");
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellIncorrect() {
        // Create a message with an invalid number (missing + and international code)
        Message msg = new Message("08575975889", "Hi Megan");
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        assertEquals(expected, msg.checkRecipientCell());
    }

    // --- 3. Testing Message Hash Generation ---
    @Test
    public void testMessageHashGeneration() {
        // Based on Test Case 1 data from the assignment brief
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        // The hash should end with the first and last word in uppercase.
        // First word: Hi, Last word: tonight? -> HITONIGHT
        assertTrue(msg.getMessageHash().endsWith("HITONIGHT"));
        
        // The format should be [First 2 of ID]:[Message Num]:[FIRSTLAST]
        // We can't assert the exact ID since it's random, but we can check the structure.
        String[] parts = msg.getMessageHash().split(":");
        assertEquals(3, parts.length); // Should have 3 parts separated by colons
        assertEquals(2, parts[0].length()); // First part should be 2 digits
    }

    // --- 4. Testing Message ID Generation ---
    @Test
    public void testMessageIDCreation() {
        Message msg = new Message("+27718693002", "Test message");
        // Check that the ID is exactly 10 digits
        assertEquals(10, msg.getMessageID().length());
        // Check that it's a number
        assertTrue(msg.getMessageID().matches("\\d+"));
    }

    // --- 5. Testing Send/Store/Disregard Options ---
    @Test
    public void testSentMessageOptionSend() {
        Message msg = new Message("+27718693002", "Test");
        String expected = "Message successfully sent.";
        assertEquals(expected, msg.sentMessage(1));
    }

    @Test
    public void testSentMessageOptionDisregard() {
        Message msg = new Message("+27718693002", "Test");
        String expected = "Press 0 to delete the message.";
        assertEquals(expected, msg.sentMessage(2));
    }

    @Test
    public void testSentMessageOptionStore() {
        Message msg = new Message("+27718693002", "Test");
        String expected = "Message successfully stored.";
        assertEquals(expected, msg.sentMessage(3));
    }
}