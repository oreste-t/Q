import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Q class.
 *  @author O Turchetti
 */
public class QTest {

    @Test
    public void basicSort() {
        Assignment alpha = new Assignment("HW1", "CS10",
                10, 10, 2020);
        Assignment bravo = new Assignment("HW2", "CS10",
                10, 15, 2020);
        Assignment charlie = new Assignment("notes", "CS188",
                10, 11, 2020);
        Assignment delta = new Assignment("HW1", "CS188",
                10, 13, 2020);
        Assignment echo = new Assignment("syllabus", "ESPM10",
                10, 12, 2020);
        Q qu = new Q();
        qu.push(alpha);
        qu.push(bravo);
        qu.push(charlie);
        qu.push(delta);
        qu.push(echo);

        assertEquals("Incorrect ordering.", "HW1 CS10", qu.get(0).getName() + " " + qu.get(0).getCategory());
        assertEquals("Incorrect ordering.", "notes CS188", qu.get(1).getName() + " " + qu.get(1).getCategory());
        assertEquals("Incorrect ordering.", "syllabus ESPM10", qu.get(2).getName() + " " + qu.get(2).getCategory());
        assertEquals("Incorrect ordering.", "HW1 CS188", qu.get(3).getName() + " " + qu.get(3).getCategory());
        assertEquals("Incorrect ordering.", "HW2 CS10", qu.get(4).getName() + " " + qu.get(4).getCategory());

        qu.sort(2);

        assertEquals("Incorrect ordering.", "CS10", qu.get(0).getCategory());
        assertEquals("Incorrect ordering.", "CS10", qu.get(1).getCategory());
        assertEquals("Incorrect ordering.", "CS188", qu.get(2).getCategory());
        assertEquals("Incorrect ordering.", "CS188", qu.get(3).getCategory());
        assertEquals("Incorrect ordering.", "ESPM10", qu.get(4).getCategory());
    }


}
