/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

/**
 *
 * @author zuluw
 */
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class Message {

    // 1. Variables to store message data
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status; // "Sent", "Stored", or "Disregarded"

    // A static counter to auto-generate the message number for all messages
    private static int messageCounter = 0;

    // 2. Constructor: Runs automatically when we create a new Message object
    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID(); // Auto-generated
        messageCounter++; // Increment the global counter
        this.messageNumber = messageCounter; // Set the current message number
        this.messageHash = createMessageHash(); // Auto-generated
    }

    // --- GETTERS (So I can access the data from Main.java) ---
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    // --- REQUIRED METHODS FROM THE ASSIGNMENT ---

    /**
     * Generates a random 10-digit number for the Message ID.
     */
    private String generateMessageID() {
        Random rand = new Random();
        // Generate a random number between 1000000000 and 9999999999 (10 digits)
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    /**
     * Checks if the message ID is not more than ten characters.
     */
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    /**
     * Checks if the recipient cell number is correctly formatted.
     * Reuses the Regex logic from Part 1.
     */
    public String checkRecipientCell() {
        // Regex: Starts with +, followed by 1 to 3 digits for country code, then 9 digits.
        String regex = "^\\+[0-9]{1,3}[0-9]{9}$"; 
        
        if (this.recipient.matches(regex)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    /**
     * Creates the Message Hash.
     * Format: First two numbers of ID : Message Number : FIRSTLASTWORDS (in caps)
     */
    public String createMessageHash() {
        // 1. Get first two characters of the Message ID
        String firstTwoID = this.messageID.substring(0, 2);
        
        // 2. Get the message number
        String num = String.valueOf(this.messageNumber);
        
        // 3. Get the first and last words of the message
        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // Combine first and last word, remove punctuation, and convert to uppercase
        String combinedWords = (firstWord + lastWord).replaceAll("[^a-zA-Z]", "").toUpperCase();

        // 4. Combine all parts: "00:0:HITHANKS"
        return firstTwoID + ":" + num + ":" + combinedWords;
    }

    /**
     * Returns the status message based on the user's choice.
     * @param choice 1 for Send, 2 for Disregard, 3 for Store
     */
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                this.status = "Sent";
                return "Message successfully sent.";
            case 2:
                this.status = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.status = "Stored";
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    /**
     * Returns a formatted string of all the message details for printing.
     */
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText + "\n" +
               "Status: " + status + "\n" +
               "------------------------------";
    }

    /**
     * Required method to store messages in a JSON file.
     */
    public String storeMessage() {
        // Create a Gson object (this is from the library I just added)
        Gson gson = new Gson();
        
        // Convert this Message object into a JSON string format
        String json = gson.toJson(this);
        
        // Try to write the JSON string to a file named "messages.json"
        try (FileWriter writer = new FileWriter("messages.json", true)) { // 'true' means append to the file, don't overwrite it
            writer.write(json + "\n"); // Write the JSON and add a new line
            return "Message successfully stored.";
        } catch (IOException e) {
            // If something goes wrong (like no permission to write files)
            return "Error storing message: " + e.getMessage();
        }
    }
}