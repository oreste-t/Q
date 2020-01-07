
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.io.*;

public class Assignment implements Comparable<Assignment>, java.io.Serializable{
    /**
     * Constructs an assignment.
     * @param name of assignment.
     * @param category class or type of assignment.
     * @param month due date month.
     * @param day due date day.
     * @param year due date year.
     *
     */
    Assignment(String name, String category, int month,
               int day, int year) {


        if (month != -1 && day != -1 && year != -1) {
            _due = LocalDate.of(year, month, day);
        } else {
            _due = null;
        }

        _name = name;
        _category = category;
        _assigned = LocalDate.now();

    }

    /**
     * Updates percent completion of assignment.
     * @param percent the new percent completion.
     */
    public void update(int percent) {
        _completion = percent;
    }


    /**
     * Compares assignments to see if they are equal. Assignments are deemed equal if they
     * have the same name anc category.
     * @param o Assignment to which this is being compared.
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Assignment) {
            return ((Assignment) o).getName().equals(this.getName()) && ((Assignment) o).getCategory().equals(this.getCategory());
        }
        return false;

    }

    /**
     * Creates string representation for assignment. If you wish to customize the string, call the
     * other toString(boolean, boolean, boolean) that is below this one. Also see that comment
     * for more info on the format of the output string.
     * @return A string representation of the assignment. This method's output includes assignment name,
     *      assignment category (class), completion percentage, due date. This string is color-coded by
     *      completion percentage. See next method comment for more info.
     */
    @Override
    public String toString() {
        return toString(true, true, true);
    }

    /**
     * Creates a string to represent the assignment. This string can be customized to include or
     * not include date due, completion percentage, and color-coding by completion percentage.
     * @param date boolean that determines whether due date should be included in the string.
     * @param comp boolean that determines whether completion percentage should be included in the
     *             String.
     * @param color boolean that determines whether string should be color-coded by completion
     *              percentage. By default, Green means complete, Yellow means started but unfinished,
     *              and red means not started.
     * @return returns a one-line string representation of the assignment.
     */
    public String toString(boolean date, boolean comp, boolean color) {
        String result = _name;
        result = result + " : " + _category;

        if (comp) {
            if (_completion == 100) {
                result = result + " : -COMPLETE-";
            } else {
                result = result + " : at " + _completion + "%" ;
            }
        }

        if (date) {
            if (_completion != 100) {
                result = result + " : due " + _due.format(DateTimeFormatter.ofPattern("LLLL dd"));;
            }
        }

        if (color) {
            if (_completion == 0) {
                result = (ANSI_RED + result + ANSI_RESET);
            } else if (_completion < 100) {
                result = (ANSI_YELLOW + result + ANSI_RESET);
            } else {
                result = (ANSI_GREEN + result + ANSI_RESET);
            }
        }


        return result;
    }

    /**
     * Compares to assignments to see which is larger. Based on due date,
     * which allows us to sort the Q by which assignment is due next.
     * @param o assignment to be compared to this.
     * @return -1 if this is due before o. 1 if otherwise.
     */
    @Override
    public int compareTo(Assignment o) {
        boolean c = this.getDueDate().isBefore(o.getDueDate());
        if (c) {
            return -1;
        }
        return 1;
    }

    /**
     * Gets due date. Accessor method.
     * @return Date at which assignment is due.
     */
    LocalDate getDueDate() {
        return _due;
    }

    /**
     * Gets name. Accessor method.
     * @return Name of assignment.
     */
    String getName() {
        return _name;
    }

    /**
     * Gets category. Accessor method.
     * @return Name of category.
     */
    String getCategory() {
        return _category;
    }

    /**
     * Gets completion percentage. Accessor method.
     * @return Int which represents percent of assignment completed.
     */
    int getCompletion() {
        return _completion;
    }

    /** Due Date of this assignment. */
    private LocalDate _due;

    /** Name of this assignment. */
    private String _name;

    /** Category of this assignment. i.e. name of the class to which the assignment belongs. */
    private String _category;

    /** Date on which the assignment was assigned. */
    //Currently unused, but planned as a way to sort Q.
    private LocalDate _assigned;

    /** Percent of Assignment completed. */
    private Integer _completion = 0;

    /** ANSI escape codes to color print output. */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

}
