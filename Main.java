

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String... rawArgs) {

        Q _queue;
        System.out.println("-----");
        try {
            FileInputStream fileIn = new FileInputStream("q.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            _queue = (Q) in.readObject();

            in.close();
            fileIn.close();

            System.out.println("Serialized Q located and read successfully.");

        } catch (IOException i) {
            _queue = new Q();
            System.out.println("No serialized Q identified. New Q has been created.");

        } catch (ClassNotFoundException c) {
            System.out.println("Q class not found");
            c.printStackTrace();
            return;
        }



        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Welcome to Q. For a list of commands, type \'help\'. To exit, type \'exit\'. \n" +
                "-----");

        while(true) {
            String command = input.nextLine();  // Read user input
            String arr[] = command.split(" ", 2);

            if (arr[0].toLowerCase().equals("add")) {
                String[] info = arr[1].split(" ");
                add(_queue, info);
                System.out.print(_queue.toString());
            } else if (arr[0].toLowerCase().equals("exit") || arr[0].toLowerCase().equals("quit")) {
                System.out.println("Exiting Q . . .");
                break;
            } else if (arr[0].toLowerCase().equals("print")) {
                System.out.print(_queue.toString());
            } else if (arr[0].toLowerCase().equals("clear")) {
                _queue.clear();
                System.out.println("Q has been wiped.");
            } else if (arr[0].toLowerCase().equals("update")) {
                String[] info = arr[1].split(" ");
                update(_queue, info);
                System.out.print(_queue.toString());
            } else {
                System.out.println("Unrecognized command. Type \'help\' for a list of commands.");
            }
            System.out.println("-----");
        }


        // Write the queue so it is persistent between calls of main.
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("q.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(_queue);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in q.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public static void add(Q q, String[] input) {
        if (input.length < 5) {
            System.out.println("Wrong format. An add command must be of the form:" +
                    "\n add name category month day year");
            return;
        }
        Assignment alpha = new Assignment(input[0], input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        q.push(alpha);
    }

    public static void update(Q q, String[] input) {
        try {
            int i = Integer.parseInt(input[2]);
            if (input.length < 3) {
                System.out.println("Wrong format. An update command must be of the form:" +
                        "\n update name category percent");
                return;
            }
            if (i > 100 || i < 0) {
                System.out.println("Invalid percent.");
                return;
            }
            q.update(input[0], input[1], i);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format. An update command must be of the form:" +
                    "\n update name category percent");
        }
    }

}
