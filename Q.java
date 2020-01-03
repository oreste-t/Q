

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Q implements java.io.Serializable{

    Q(){
        this._queue = new PriorityQueue<>();
    }

    /**
     * @param o Assignment that gets added to Q. Default sorted by due date.
     */
    public void push(Assignment o) {
        this._queue.add(o);
        this.asArray();
    }

    /**
     * @return Removes and returns the element on top of the Q. Default is one due soonest.
     */
    public Assignment poll() {
        return this._queue.poll();
    }

    /**
     * @return  returns element on top of Q without removing it.
     */
    public Assignment peek() {
        return this._queue.peek();
    }

    /**
     * @return an Assignment Array equivalent of the Q.
     * Useful for printing.
     */
    public Assignment[] asArray() {

        this._arrayQ =_queue.toArray(_arrayQ);
        return _arrayQ;
    }

    /**
     * Wipes all contents of Q and the Array version of the Q.
     */
    public void clear() {
        this._queue.clear();
        this._arrayQ = new Assignment[0];
    }

    /**
     * @param name String name of the assignment to be deleted.
     * @param category String category (class) of assignment to be deleted.
     * @return Returns true on success and false on failure of deletion.
     */
    public boolean delete(String name, String category) {
        _queue = new PriorityQueue<>();
        boolean e = false; // Does the inputted Assignment exist in the Q?
        for (int i = 0; i < _arrayQ.length; i++) {
            //If element to be deleted is found, we will not add this back into the new Q.
            if(_arrayQ[i].getName().equals(name) && _arrayQ[i].getCategory().equals(category)) {
                e = true;
                continue;
            }
            _queue.add(_arrayQ[i]);
        }
        if (!e) {
            System.out.println("Assignment with name " + name + " and category " + category + " does not exist.");
            return false;
        }
        this.asArray();
        return true;
    }

    /**
     * @param name String name of Assignment to update.
     * @param category String category (class) of assignment to be updated.
     * @param percent int new percent completion of said assignment.
     */
    public void update(String name, String category, int percent) {
        _queue = new PriorityQueue<>();
        boolean e = false;
        for (int i = 0; i < _arrayQ.length; i++) {
            if (_arrayQ[i] == null) {
                continue;
            }
            if(_arrayQ[i].getName().equals(name) && _arrayQ[i].getCategory().equals(category)) {
                _arrayQ[i].update(percent);
                e = true;
            }
            _queue.add(_arrayQ[i]);
        }
        if (!e) {
            System.out.println("Assignment with name " + name + " and category " + category + " does not exist.");
        }
        this.asArray();
    }

    @Override
    public String toString() {

        if ( this._arrayQ == null || this._arrayQ.length == 0 || this._arrayQ[0] == null) {
            return "Q is empty \n";
        }
        PriorityQueue<Assignment> copy = new PriorityQueue<>();
        Assignment asmt;
        String complete = "";
        String result = "";
        while (!_queue.isEmpty()) {
            asmt = _queue.poll();
            if (asmt == null) {
                continue;
            }
            if (asmt.getCompletion() == 100) {
                complete = complete + asmt.toString() + "\n";
                copy.add(asmt);
                continue;
            }
            result = result + asmt.toString() + "\n";
            copy.add(asmt);
        }
        _queue = copy;
        return result + complete;
    }


    private PriorityQueue<Assignment> _queue;

    private Assignment[] _arrayQ = new Assignment[0];

    private ArrayList<Assignment> _q;

    //Unused for now. Plan to implement other orderings (completion, date assigned, etc)
    private String _order = "Due Date";
}
