import java.io.*;
import java.net.*;
package org.example;


public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) { // Start server on port 12345
            System.out.println("Server is running and waiting for connections...");
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept a client connection
                System.out.println("Client connected.");

                // Read input from client and send the response
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String input = in.readLine(); // Read the number sent by the client
                    int number = Integer.parseInt(input);

                    // Determine if the number is prime
                    String response = isPrime(number) ? "Yes, it is prime." : "No, it is not prime.";
                    out.println(response); // Send the response back to the client
                } catch (IOException | NumberFormatException e) {
                    System.err.println("Error processing client request: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }

    // Helper method to check if a number is prime
    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
