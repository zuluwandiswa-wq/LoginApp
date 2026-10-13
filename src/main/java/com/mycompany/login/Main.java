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
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();
        
        // --- PART 1: REGISTRATION AND LOGIN ---
        System.out.println("=== Welcome to QuickChat Registration ===");
        
        // Basic registration flow
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        loginSystem.setNames(firstName, lastName);

        System.out.print("Create Username (must contain _ and max 5 chars): ");
        String username = scanner.nextLine();
        System.out.print("Create Password (min 8 chars, capital, number, special): ");
        String password = scanner.nextLine();
        System.out.print("Enter SA Cell Number (+27...): ");
        String cell = scanner.nextLine();

        String regMessage = loginSystem.registerUser(username, password, cell);
        System.out.println(regMessage);

        if (regMessage.equals("User registered successfully.")) {
            // --- LOGIN ---
            System.out.println("\n=== Please Login ===");
            System.out.print("Username: ");
            String loginUser = scanner.nextLine();
            System.out.print("Password: ");
            String loginPass = scanner.nextLine();

            boolean isLoggedIn = loginSystem.loginUser(loginUser, loginPass);
            System.out.println(loginSystem.returnLoginStatus(isLoggedIn));

            if (isLoggedIn) {
                // --- PART 2: MESSAGING MENU ---
                System.out.println("\nWelcome to QuickChat.");
                
                System.out.print("How many messages do you wish to enter? ");
                int numMessages = scanner.nextInt();
                scanner.nextLine(); // Consume the leftover newline

                ArrayList<Message> sentMessages = new ArrayList<>();
                ArrayList<Message> storedMessages = new ArrayList<>();
                int sentCount = 0;

                // --- MAIN MENU LOOP ---
                boolean running = true;
                while (running) {
                    System.out.println("\n--- MENU ---");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Choose an option: ");
                    int menuChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (menuChoice) {
                        case 1:
                            // Loop through the number of messages the user specified
                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                                
                                System.out.print("Enter Recipient Cell Number: ");
                                String recipient = scanner.nextLine();
                                
                                System.out.print("Enter Message (max 250 chars): ");
                                String text = scanner.nextLine();

                                // Check message length
                                if (text.length() > 250) {
                                    int excess = text.length() - 250;
                                    System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
                                    i--; // Decrement i so the loop repeats this message slot
                                    continue; // Skip to the next iteration
                                } else {
                                    System.out.println("Message ready to send.");
                                }

                                // Create the Message object
                                Message msg = new Message(recipient, text);

                                // Validate recipient
                                System.out.println(msg.checkRecipientCell());

                                // Display generated ID and Hash
                                System.out.println("Message ID generated: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());

                                // Ask user what to do
                                System.out.println("\nWhat would you like to do?");
                                System.out.println("1 - Send Message");
                                System.out.println("2 - Disregard Message");
                                System.out.println("3 - Store Message to send later");
                                System.out.print("Choice: ");
                                int action = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                String statusResult = msg.sentMessage(action);
                                System.out.println(statusResult);

                                if (action == 1) {
                                    sentMessages.add(msg);
                                    sentCount++;
                                } else if (action == 3) {
                                    storedMessages.add(msg);
                                    // Call the JSON storage method!
                                    System.out.println(msg.storeMessage()); 
                                }
                            }
                            break; // Break out of the switch, back to main menu

                        case 2:
                            System.out.println("Coming Soon.");
                            break;

                        case 3:
                            System.out.println("Exiting application. Goodbye!");
                            running = false;
                            break;

                        default:
                            System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    }
                }

                // --- FINAL SUMMARY ---
                System.out.println("\n=== Summary of Sent Messages ===");
                if (sentMessages.isEmpty()) {
                    System.out.println("No messages were sent.");
                } else {
                    for (Message m : sentMessages) {
                        System.out.println(m.printMessages());
                    }
                }
                System.out.println("Total messages sent: " + sentCount);
            }
        }
        scanner.close();
    }
}