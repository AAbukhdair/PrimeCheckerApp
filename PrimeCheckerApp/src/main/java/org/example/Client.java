import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345); // Connect to the server on localhost:12345
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Get input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a number to check if it is prime: ");
            int number = scanner.nextInt();

            // Send the number to the server
            out.println(number);

            // Receive and display the response from the server
            String response = in.readLine();
            System.out.println("Server response: " + response);
        } catch (IOException e) {
            System.err.println("Client exception: " + e.getMessage());
        }
    }
}
