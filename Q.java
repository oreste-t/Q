

import java.util.PriorityQueue;


public class Q implements java.io.Serializable{

    Q(){
        this._queue = new PriorityQueue<>();
//        _modified = 0;

    }


    public void push(Assignment o) {
        this._queue.add(o);
//        _modified = 1;
        this.asArray();
    }

    public Assignment poll() {
        return this._queue.poll();
    }

    public Assignment peek() {
        return this._queue.peek();
    }

    public Assignment[] asArray() {
//        if (this._modified == 0) {
//            return this._arrayQ;
//        }

        this._arrayQ =_queue.toArray(_arrayQ);
//        this._modified = 0;
        return _arrayQ;
    }

    public void clear() {
        this._queue.clear();
        this._arrayQ = new Assignment[0];
//        this._modified = 1;
    }

    public void update(String name, String category, int percent) {
        _queue = new PriorityQueue<>();
        boolean e = false;
        for (int i = 0; i < _arrayQ.length; i++) {
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
//        if (this._modified == 1) {
//            this.asArray();
//            return this.toString();
//        }

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




    private int _modified;

    private String _order = "Due Date";

    private PriorityQueue<Assignment> _queue;

    private Assignment[] _arrayQ = new Assignment[0];
}
