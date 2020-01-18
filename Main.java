

import java.io.*;
import java.util.Scanner;


public class Main {

    /**
     * Prompts the user for the name of a Q to load. If no Q exists with given name,
     * one will be created. Once a Q is loaded or created, the program enters a loop where
     * the user is continuously prompted for input commands (which can be viewed with the
     * help command). The program then executes the given commands, and restarts the loop.
     * This continues until the user inputs an exit command, at which point whatever Q that
     * is open is automatically saved and the program terminates.
     */
    public static void main(String... rawArgs) {


        System.out.println("-----");

        System.out.println("Enter name of Q you would like to load, or name of new Q:");
        Scanner getQ = new Scanner(System.in);
        String qName = getQ.nextLine();  // Read user input
        String[] temp = qName.split(" ", 2);

        String name = constructName(temp); // Allow input for multiple qs.

        int marker = load(name);
        if (marker == 0) {
            System.out.println("Serialized Q located and read successfully.");
        } else if (marker == 1) {
            System.out.println("No serialized Q of name " + name +" has been identified. " +
                    "New Q has been created.");
        } else {
            System.out.println("Q class not found.");
        }



        System.out.println("-----");
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Welcome to Q. For a list of commands, type \'help\'. To exit, type \'exit\'. \n" +
                "-----");

        boolean success;
        while(true) {
            String command = input.nextLine();  // Read user input
            String arr[] = command.split(" ", 2);
            if (arr[0].toLowerCase().equals("add")) {
                String[] info = arr[1].split(" ");
                success = add(_queue, info);
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
                success = delete(_queue, info);
                if (success) {
                    System.out.println("Deletion Successful.");
                    System.out.println("-");
                    System.out.print(_queue.toString());
                }
            } else if (arr[0].toLowerCase().equals("exit") || arr[0].toLowerCase().equals("quit")) {
                System.out.println("Exiting Q . . .");
                break;
            } else if (arr[0].toLowerCase().equals("load")) {
                if (save(_queue, name)) {
                    System.out.println("Serialized data is saved in " + name + ".ser.");
                } else {
                    System.out.println("Failed to save current Q.");
                    break;
                }
                marker = load(arr[1]);
                if (marker == 0) {
                    name = arr[1];
                    System.out.println("Serialized Q located and read successfully.");
                } else if (marker == 1) {
                    name = arr[1];
                    System.out.println("No serialized Q of name " + name +" has been identified. " +
                            "New Q has been created.");
                } else {
                    System.out.println("Q class not found.");
                }
            } else if (arr[0].toLowerCase().equals("print")) {
                System.out.println("-");
                System.out.print(_queue.toString());
            } else if (arr[0].toLowerCase().equals("save")) {
                boolean saved = save(_queue, name);
                if (saved) {
                    System.out.println("Saved successfully. Serialized data is saved in " + name + ".ser.");
                }
            } else if (arr[0].toLowerCase().equals("sort")) {
                String[] info = arr[1].split(" ");
                int setting = sort(_queue, info);
                String result = "Q now sorted by ";
                if (setting == 0) {
                    result = result + "due date.";
                } else if (setting == 1) {
                    result = result + "completion percentage.";
                } else if (setting == 2) {
                    result = result + "category.";
                } else if (setting == 3) {
                    result = result + "date assigned.";
                } else {
                    continue;
                }
                System.out.println(result);
                System.out.println("-");
                System.out.print(_queue.toString());
            } else if (arr[0].toLowerCase().equals("toggle")) {
                String[] info = arr[1].split(" ");
                toggle(_queue, info);

            } else if (arr[0].toLowerCase().equals("update")) {
                String[] info = arr[1].split(" ");
                success = update(_queue, info);
                if (success) {
                    System.out.println("Update successful.");
                    System.out.println("-");
                    System.out.print(_queue.toString());
                }
            } else if (arr[0].toLowerCase().equals("help")) {
                System.out.println(HELP);
            } else {
                System.out.println("Unrecognized command. Type \'help\' for a list of commands.");
            }
            System.out.println("-----");
        }

        if (save(_queue, name)) {
            System.out.println("Serialized data is saved in " + name + ".ser.");
        }
    }

    /**
     * Adds an assignment to the Q. Also check for input to be the correct format and
     * prints error messages if input is not correct.
     * @param q the currently opened Q to which the assignment should be added.
     * @param input a string array constructed from user input. Item 0 should be the name
     *              of the new assignment, item 1 should be the category (class), item 2
     *              should be month of due date, item 3 should be day of due date, item
     *              4 should be year of due date.
     * @return boolean where true means addition was successful and false means it was not.
     */
    private static boolean add(Q q, String[] input) {
        if (input.length < 5) {
            System.out.println("Wrong format. An add command must be of the form: \n" +
                    "add name category MM DD YYYY");
            return false;
        }
        try {
            Assignment alpha = new Assignment(input[0], input[1], Integer.parseInt(input[2]),
                    Integer.parseInt(input[3]), Integer.parseInt(input[4]));
            q.push(alpha);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Date must be properly formatted and consist only of numbers. An add command \n" +
                    "must be of the form:" +
                    "add name category MM DD YYYY");
            return false;
        } catch (java.time.DateTimeException f) {
            System.out.println("Date must be properly formatted and consist only of numbers. An add command \n" +
                    "must be of the form:" +
                    "\n add name category MM DD YYYY");
            System.out.println("Make sure your inputted month and date exist.");
            return false;
        }
    }

    /**
     * Deletes assignment from Q.
     * @param q Q from which assignment should be deleted.
     * @param input String array constructed from user input. First item should be name of assignment,
     *              second item should be category (class) of assignment to be deleted.
     * @return boolean where true means deletion was successful and false means it was not.
     */
    private static boolean delete(Q q, String[] input) {
        if (input.length < 2) {
            System.out.println("Wrong format. A delete command must be of the form: \n" +
                    "delete name category");
            return false;
        }
        boolean result = q.delete(input[0], input[1]); //FIXME : Have delete throw a custom error type.
        return result;
    }

    /**
     * Loads an existing Q into the main program thread, allowing operations to be
     * performed on it.
     * @param name String name of the Q to be loaded.
     * @return returns 0 if load was successful, 1 if no Q with given name was found,
     *         and 2 if there is a ClassNotFoundException.
     */
    private static int load(String name) {
        try {
            FileInputStream fileIn = new FileInputStream(name + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            _queue = (Q) in.readObject();

            in.close();
            fileIn.close();
            return 0;
        } catch (IOException i) {
            _queue = new Q();
            save(_queue, name);
            return 1;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return 2;
        }
    }

    /**
     * Serializes the Q in order to save its contents for later use.
     * @param q Q to be written/saved.
     * @param name Name that the Q should be save to.
     * @return boolean where true means save was successful and false means it was not.
     */
    private static boolean save(Q q, String name) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(name + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(q);
            out.close();
            fileOut.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    /**
     * Sets the sort order of the Q for printing.
     * @param q Q whose sort order should be changed.
     * @param input string array which should have one item. Any additional items will be ignored.
     *              Should be a string word either due, completion, category, or assigned. These
     *              correspond to each of the possible ways to sort the Q.
     * @return and int that lets the main thread know if a valid setting was chosen, and if so,
     * which one. 
     */
    private static int sort(Q q, String[] input) {
        if (input.length < 1) {
            System.out.println("Wrong format. A sort command must be of the form: \n" +
                    "sort setting");
            return -1;
        }
        if (input[0].toLowerCase().equals("due")) {
            q.setSortSetting(0);
            return 0;
        } else if (input[0].toLowerCase().equals("completion")) {
            q.setSortSetting(1);
            return 1;
        } else if (input[0].toLowerCase().equals("category")) {
            q.setSortSetting(2);
            return 2;
        } else if (input[0].toLowerCase().equals("assigned")) {
            q.setSortSetting(3);
            return 3;
        } else {
            System.out.println("Setting does not exist. Type help to view available" +
                    " settings for this command.");
            return -1;
        }

    }

    /**
     * Toggles either due date, completion percentage, or color for printing of the Q.
     * @param q Q where setting should be toggled.
     * @param input string array where the first item should be the setting to be toggled.
     *              Setting should be either date, completion, or color. Subsequent elements
     *              in the array will be ignored.
     * @return boolean where true means toggle was successful and false means it was not.
     */
    private static boolean toggle(Q q, String[] input) { //FIXME : need to add an option to toggle off completion splitting.
        if (input.length < 1) {
            System.out.println("Wrong format. A toggle command must be of the form: \n" +
                    "toggle setting");
            return false;
        }
        if (input[0].toLowerCase().equals("date")) {
            q.toggleDate();
            if (q.getDateToggle()) {
                System.out.println("Date is toggled ON.");
            } else {
                System.out.println("Date is toggled OFF.");
            }
        } else if (input[0].toLowerCase().equals("completion")) {
            q.toggleCompletion();
            if (q.getCompletionToggle()) {
                System.out.println("Completion is toggled ON.");
            } else {
                System.out.println("Completion is toggled OFF.");
            }
        } else if (input[0].toLowerCase().equals("color")) {
            q.toggleColor();
            if (q.getColorToggle()) {
                System.out.println("Color is toggled ON.");
            } else {
                System.out.println("Color is toggled OFF.");
            }
        } else {
            System.out.println("Unknown setting cannot be toggled.");
        }
        return true;
    }

    /**
     * Updates completion percentage of an assignment in the Q.
     * @param q Q that contains assignment to be updated.
     * @param input String array where the first item should be the name of the assignment
     *              to be update. Second item should be category (class) of assignment.
     *              Third item should be number of new completion percentage between 0 and
     *              100, inclusive.
     * @return boolean where true means update was successful and false means it was not.
     */
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

    /**
     * Fixes inputted name for serialized file.
     * @param nameArr User-inputted name for Q to be serialized.
     * @return String name but with spaces replaced with _
     */
    private static String constructName(String[] nameArr) {
        String result = "";
        for (int i = 0; i < nameArr.length; i++) {
            result = result + nameArr[i] + "_";
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * The Q object to which we load the currently opened Q.
     */
    private static Q _queue;

    /**
     * Help printout with info on all implemented commands available to users.
     */
    private static String HELP = "Commands: \n" +
            "ADD - Adds an assignment to the Q. Assignment must have a name, a category (class/topic), " +
            "and a due date. Typed command should be in the following format: \n" +
            " add name category MM DD YYYY \n" +
            "\n" +
            "CLEAR - wipes the Q completely. Typed command should be in the following format: \n" +
            " clear \n" +
            "\n" +
            "DELETE - deletes all elements from the queue that have the given name and category. Typed " +
            "command should be in the following format: \n" +
            " delete name category \n" +
            "\n" +
            "EXIT - Closes program. Automatically saves the Q for next session. Typed " +
            "command should be in the following format: \n" +
            " exit \n" +
            "\n" +
            "LOAD - Loads an existing Q, or if the given name is not the name of an existing Q, a new Q " +
            "with that name is created. If a Q is currently open when this command is used, that Q will " +
            "automatically be saved before being closed and before the new Q is loaded up. Typed command " +
            "should be in the following format: \n" +
            " load name \n" +
            "\n" +
            "PRINT - Outputs the current contents of the Q, as well as completed assignments. Items " +
            "further towards the top are due sooner. Typed command should be in the following format: \n" +
            " print \n" +
            "\n" +
            "SAVE - Saves Q, allowing it to be retrieved even if program terminates unexpectedly. Quitting " +
            "utilizing the exit or quit commands will automatically save.  Typed command should be in the " +
            "following format: \n" +
            " save \n" +
            "\n" +
            "SORT - Changes the order in which the queue is sorted. There are four options, sort by due date," +
            "sort by completion percentage, sort by category, sort by date assigned. Typed command should be in the " +
            "following format, where setting should be either due, completion, category, or assigned: \n" +
            " sort setting \n" +
            "\n" +
            "TOGGLE - Toggles date, completion percentage, or text color-coding on and off when printing " +
            "the assignments. Typed command should be in the following format, where setting should be " +
            "either date, completion, or color: \n" +
            " toggle setting \n" +
            "\n" +
            "UPDATE - Updates completion percentage of an assignment. The percentage should be a number " +
            "between 0 and 100. At 100 percent completion, the assignment becomes complete, and is " +
            "placed at the bottom of the Q with other completed assignments. Typed command should be " +
            "in the following " +
            "format: \n" +
            " update name category percent";
}
