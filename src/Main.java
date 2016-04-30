import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        JFrame frame = new ChatFrame();
    }

    public static void setupClient(String address, String port, String name) {
        try {
            System.out.println("Connecting...");
            Socket client = new Socket(address, Integer.parseInt(port));
            System.out.println("Connect to " + address + ":" + port);
            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            new Thread(() -> {
                while(in.hasNextLine())
                    System.out.println(in.nextLine());
            }).start();

            new Thread(() -> {
                while(keyboard.hasNextLine())
                    out.println(name + ": " + keyboard.nextLine());
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setupServer(String port, String name) {
        try {
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner in = new Scanner(clientSocket.getInputStream());
            out.println("Contact from server");

            new Thread(() -> {
                while(in.hasNextLine())
                    System.out.println(in.nextLine());
            }).start();

            new Thread(() -> {
                while(keyboard.hasNextLine())
                    out.println( name + ": " + keyboard.nextLine());
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
