

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Q implements java.io.Serializable{

    Q(){
        this._queue = new ArrayList<>();
        date = true;
        completion = true;
        color = true;
        sortSetting = 0;
    }

    /**
     * @param o Assignment that gets added to Q. Default sorted by due date.
     */

    //FIXME : change to binary search
    public void push(Assignment o) {
        if (_queue.isEmpty()) {
            _queue.add(o);
            return;
        }
        for (int i = 0; i < _queue.size(); i++) {
            if (o.compareTo(_queue.get(i)) == -1 || o.compareTo(_queue.get(i)) == 0) {
                _queue.add(i, o);
                return;
            }
        }
        _queue.add(o);
    }

    /**
     * @return Removes and returns the element on top of the Q. Default is one due soonest.
     */
    public Assignment poll() {
        return this._queue.remove(0);
    }

    /**
     * @return  returns element on top of Q without removing it.
     */
    public Assignment peek() {
        return this._queue.get(0);
    }

    /**
     * @return an Assignment Array equivalent of the Q.
     */
    public Assignment[] asArray() {
        return (Assignment[])_queue.toArray();
    }

    /**
     * Wipes all contents of Q. Does not reset toggle settings
     * to default. Only clears assignments out of Q.
     */
    public void clear() {
        this._queue = new ArrayList<>();
    }

    /**
     * Toggles date on and off for printing.
     */
    public void toggleDate() {
        date = !date;
    }

    /**
     * Toggles completion on and off for printing.
     */
    public void toggleCompletion() {
        completion = !completion;
    }

    /**
     * Toggles color on and off for printing.
     */
    public void toggleColor() {
        color = !color;
    }

    /**
     * Deletes assignment with given name and category if it exists.
     * @param name String name of the assignment to be deleted.
     * @param category String category (class) of assignment to be deleted.
     * @return Returns true on success and false on failure of deletion.
     */
    public boolean delete(String name, String category) {
        Assignment rem = new Assignment(name, category, -1, -1, -1);
        return _queue.remove(rem);
    }

    /**
     * Updates percent completion of given assignment.
     * @param name String name of Assignment to update.
     * @param category String category (class) of assignment to be updated.
     * @param percent int new percent completion of said assignment.
     */
    public void update(String name, String category, int percent) {

        if (_queue.isEmpty()) {
            System.out.println("Assignment with name " + name + " and category " + category + " does not exist.");
            return;
        }

        Assignment upd = new Assignment(name, category, -1, -1, -1);
        for (int i = 0; i < _queue.size(); i++) {
            if(upd.equals(_queue.get(i))) {
                _queue.get(i).update(percent);
                return;
            }
        }
        System.out.println("Assignment with name " + name + " and category " + category + " does not exist.");
    }

    @Override
    public String toString() {

        if ( _queue.isEmpty()) {
            return "Q is empty \n";
        }

        String complete = "";
        String result = "";
        for (int i = 0; i < _queue.size(); i++) {
            if (_queue.get(i).getCompletion() == 100) {
                complete = complete + _queue.get(i).toString(date, completion, color) + "\n";
                continue;
            }
            result = result + _queue.get(i).toString(date, completion, color) + "\n";

        }
        return result + complete;
    }

    public boolean getDateToggle() {
        return date;
    }

    public boolean getCompletionToggle() {
        return completion;
    }

    public boolean getColorToggle() {
        return color;
    }

    /**
     * Determines whether date should be printed for each assignment.
     * Can be toggled on and off.
     */
    private boolean date;

    /**
     * Determines whether completion should be printed for each assignment.
     * Can be toggled on and off.
     */
    private boolean completion;

    /**
     * Determines whether color should be printed for each assignment.
     * Can be toggled on and off.
     */
    private boolean color;

    /**
     * Determines how the Q is ordered.
     * 0 : sorted by due date
     * 1 : sorted by completion percentage
     * 2 : sorted by category (class)
     * 3 : sorted by date assigned
     */
    private int sortSetting; //FIXME : Not yet implemented.

    /**
     * Stores all the assignments. Funnily enough, not a queue.
     */
    private ArrayList<Assignment> _queue;
}
