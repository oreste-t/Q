
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

    public boolean equals( Assignment o) {
        return o.getName().equals(this.getName()) && o.getCategory().equals(this.getCategory());
    }

    public void update(int percent) {
        _completion = percent;
        if (_completion == 100) {
            _due = LocalDate.MAX;
        }
    }

    @Override
    public String toString() {
        String result = _name;
        if (_completion == 100) {
            result = result + " : " + _category + " : -COMPLETE-";
        } else {
            result = result + " : " + _category + " : at " + _completion + "%";
        }

        return result;
    }

    @Override
    public int compareTo(Assignment o) {
        boolean c = this.getDueDate().isBefore(o.getDueDate());
        if (c) {
            return -1;
        }
        return 1;
    }

    /**
     * Gets due date.
     * @return Date at which assignment is due.
     */
    LocalDate getDueDate() {
        return _due;
    }

    String getName() {
        return _name;
    }

    String getCategory() {
        return _category;
    }

    /** Due Date of this assignment. */
    private LocalDate _due;

    private String _name;

    private String _category;

    private LocalDate _assigned;

    private Integer _completion = 0;


}
