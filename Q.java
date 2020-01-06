

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Q implements java.io.Serializable{

    Q(){
        this._queue = new ArrayList<>();
        date = true;
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
     * Useful for printing.
     */
    public Assignment[] asArray() {
        return (Assignment[])_queue.toArray();
    }

    /**
     * Wipes all contents of Q.
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
     * @param name String name of the assignment to be deleted.
     * @param category String category (class) of assignment to be deleted.
     * @return Returns true on success and false on failure of deletion.
     */
    public boolean delete(String name, String category) {
        Assignment rem = new Assignment(name, category, -1, -1, -1);
        return _queue.remove(rem);
    }

    /**
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
                complete = complete + _queue.get(i).toString() + "\n";
                continue;
            }
            result = result + _queue.get(i).toString() + "\n";
        }
        return result + complete;
    }

    private boolean date;

    private ArrayList<Assignment> _queue;

    private Assignment[] _arrayQ = new Assignment[0];

    private ArrayList<Assignment> _q;

    //Unused for now. Plan to implement other orderings (completion, date assigned, etc)
    private String _order = "Due Date";
}
