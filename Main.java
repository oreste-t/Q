

import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String... rawArgs) {

        Q _queue;
        System.out.println("-----");
        String name = "q"; // Allow input for multiple qs.

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
            System.out.println("Q class not found.");
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
                boolean success = add(_queue, info);
                if (success) {
                    System.out.println("Addition Successful.");
                    System.out.println("-");
                    System.out.print(_queue.toString());
                }
            } else if (arr[0].toLowerCase().equals("clear")) {
                _queue.clear();
                System.out.println("Q has been wiped.");
            } else if (arr[0].toLowerCase().equals("delete")) {
                String[] info = arr[1].split(" ");
                boolean success = delete(_queue, info);
                if (success) {
                    System.out.println("Deletion Successful.");
                    System.out.println("-");
                    System.out.print(_queue.toString());
                }
            } else if (arr[0].toLowerCase().equals("exit") || arr[0].toLowerCase().equals("quit")) {
                System.out.println("Exiting Q . . .");
                break;
            } else if (arr[0].toLowerCase().equals("print")) {
                System.out.println("-");
                System.out.print(_queue.toString());
            } else if (arr[0].toLowerCase().equals("toggle")) {
                _queue.toggleDate();
                if (_queue.getDateToggle()) {
                    System.out.println("Date toggled on.");
                } else {
                    System.out.println("Date toggled off.");
                }
            } else if (arr[0].toLowerCase().equals("update")) {
                String[] info = arr[1].split(" ");
                boolean success =update(_queue, info);
                if (success) {
                    System.out.println("Update successful.");
                    System.out.println("-");
                    System.out.print(_queue.toString());
                }
            } else if (arr[0].toLowerCase().equals("help")) {
                String help = "Commands: \n" +
                        "ADD - Adds an assignment to the Q. Assignment must have a name, a category (class/topic), " +
                        "and a due date. Typed command should be in the following format: \n" +
                        "add name category MM DD YYYY \n" +
                        "\n" +
                        "CLEAR - wipes the Q completely. Typed command should be in the following format: \n" +
                        "clear \n" +
                        "\n" +
                        "DELETE - deletes all elements from the queue that have the given name and category. Typed " +
                        "command should be in the following format: \n" +
                        "delete name category \n" +
                        "\n" +
                        "PRINT - Outputs the current contents of the Q, as well as completed assignments. Items " +
                        "further towards the top are due sooner. Typed command should be in the following format: \n" +
                        "print \n" +
                        "\n" +
                        "UPDATE - Updates completion percentage of an assignment. The percentage should be a number " +
                        "between 0 and 100. At 100 percent completion, the assignment becomes complete, and is " +
                        "placed at the bottom of the Q with other completed assignments. Typed command should be " +
                        "in the following " +
                        "format: \n" +
                        "update name category percent";
                System.out.println(help);
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
            System.out.println("Serialized data is saved in q.ser.");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    private static boolean add(Q q, String[] input) {
        if (input.length < 5) {
            System.out.println("Wrong format. An add command must be of the form: \n" +
                    "add name category MM DD YYYY");
            return false;
        }
        try {
            Assignment alpha = new Assignment(input[0], input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4]));
            q.push(alpha);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Date must be properly formatted and consist only of numbers. An add command \n" +
                    "must be of the form:" +
                    "add name category MM DD YYYY");
            return false;
        }

    }

    private static boolean delete(Q q, String[] input) {
        if (input.length < 2) {
            System.out.println("Wrong format. A delete command must be of the form: \n" +
                    "delete name category");
            return false;
        }
        boolean result = q.delete(input[0], input[1]);
        return result;
    }

    private static boolean update(Q q, String[] input) {
        try {

            if (input.length < 3) {
                System.out.println("Wrong format. An update command must be of the form: \n" +
                        "update name category percent");
                return false;
            }
            int i = Integer.parseInt(input[2]);
            if (i > 100 || i < 0) {
                System.out.println("Invalid percent.");
                return false;
            }
            q.update(input[0], input[1], i);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Percent must be a number. An update command must be of the form: \n" +
                    "update name category percent");
            return false;
        }
    }

}
