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

        assertEquals("Incorrect ordering.", alpha, qu.get(0));
        assertEquals("Incorrect ordering.", charlie, qu.get(1));
        assertEquals("Incorrect ordering.", echo, qu.get(2));
        assertEquals("Incorrect ordering.", delta, qu.get(3));
        assertEquals("Incorrect ordering.", bravo, qu.get(4));

        qu.sort(2);
        assertEquals("Incorrect ordering.", "CS10", qu.get(0).getCategory());
        assertEquals("Incorrect ordering.", "CS10", qu.get(1).getCategory());
        assertEquals("Incorrect ordering.", "CS188", qu.get(2).getCategory());
        assertEquals("Incorrect ordering.", "CS188", qu.get(3).getCategory());
        assertEquals("Incorrect ordering.", "ESPM10", qu.get(4).getCategory());
    }

    @Test
    public void multipleReSort() {

        Assignment alpha = new Assignment("HW1", "CS10",
                10, 10, 2020);
        alpha.modifyDateAssigned(1, 5, 2020);
        alpha.update(33);

        Assignment bravo = new Assignment("HW2", "CS10",
                10, 15, 2020);
        bravo.modifyDateAssigned(1, 1, 2020);
        bravo.update(99);

        Assignment charlie = new Assignment("notes", "CS188",
                10, 11, 2020);
        charlie.modifyDateAssigned(3, 28, 2020);
        charlie.update(100);

        Assignment delta = new Assignment("HW1", "CS188",
                10, 13, 2020);
        delta.modifyDateAssigned(1, 2, 2020);
        delta.update(10);

        Assignment echo = new Assignment("syllabus", "ESPM10",
                10, 12, 2020);
        echo.modifyDateAssigned(1, 6, 2020);
        echo.update(27);

        Q qu = new Q();
        qu.push(alpha);
        qu.push(bravo);
        qu.push(charlie);
        qu.push(delta);
        qu.push(echo);

        qu.sort(3);
        assertEquals("Incorrect ordering.", bravo, qu.get(0));
        assertEquals("Incorrect ordering.", delta, qu.get(1));
        assertEquals("Incorrect ordering.", alpha, qu.get(2));
        assertEquals("Incorrect ordering.", echo, qu.get(3));
        assertEquals("Incorrect ordering.", charlie, qu.get(4));
        assertEquals("Incorrect length.", 5, qu.length());

        qu.sort(2);
        assertEquals("Incorrect ordering.", "CS10", qu.get(0).getCategory());
        assertEquals("Incorrect ordering.", "CS10", qu.get(1).getCategory());
        assertEquals("Incorrect ordering.", "CS188", qu.get(2).getCategory());
        assertEquals("Incorrect ordering.", "CS188", qu.get(3).getCategory());
        assertEquals("Incorrect ordering.", "ESPM10", qu.get(4).getCategory());
        assertEquals("Incorrect length.", 5, qu.length());

        qu.sort(1);
        assertEquals("Incorrect ordering.", delta, qu.get(0));
        assertEquals("Incorrect ordering.", echo, qu.get(1));
        assertEquals("Incorrect ordering.", alpha, qu.get(2));
        assertEquals("Incorrect ordering.", bravo, qu.get(3));
        assertEquals("Incorrect ordering.", charlie, qu.get(4));
        assertEquals("Incorrect length.", 5, qu.length());

        qu.sort(0);
        assertEquals("Incorrect ordering.", alpha, qu.get(0));
        assertEquals("Incorrect ordering.", charlie, qu.get(1));
        assertEquals("Incorrect ordering.", echo, qu.get(2));
        assertEquals("Incorrect ordering.", delta, qu.get(3));
        assertEquals("Incorrect ordering.", bravo, qu.get(4));
        assertEquals("Incorrect length.", 5, qu.length());
    }

    @Test
    public void deletion() {
        Assignment alpha = new Assignment("HW1", "CS10",
                10, 10, 2020);
        alpha.modifyDateAssigned(1, 5, 2020);
        alpha.update(33);

        Assignment bravo = new Assignment("HW2", "CS10",
                10, 15, 2020);
        bravo.modifyDateAssigned(1, 1, 2020);
        bravo.update(99);

        Assignment charlie = new Assignment("notes", "CS188",
                10, 11, 2020);
        charlie.modifyDateAssigned(3, 28, 2020);
        charlie.update(100);

        Assignment delta = new Assignment("HW1", "CS188",
                10, 13, 2020);
        delta.modifyDateAssigned(1, 2, 2020);
        delta.update(10);

        Assignment echo = new Assignment("syllabus", "ESPM10",
                10, 12, 2020);
        echo.modifyDateAssigned(1, 6, 2020);
        echo.update(27);

        Q qu = new Q();
        qu.push(alpha);
        qu.push(bravo);
        qu.push(charlie);
        qu.push(delta);
        qu.push(echo);

        qu.delete("HW1", "CS188");

        assertEquals("Incorrect ordering.", alpha, qu.get(0));
        assertEquals("Incorrect ordering.", charlie, qu.get(1));
        assertEquals("Incorrect ordering.", echo, qu.get(2));
        assertEquals("Incorrect ordering.", bravo, qu.get(3));
        assertEquals("Incorrect length.", 4, qu.length());

        qu.delete("HW4", "CS188");
        assertEquals("Incorrect length.", 4, qu.length());
    }

    @Test
    public void updateReSort() {
        Assignment alpha = new Assignment("HW1", "CS10",
                10, 10, 2020);
        alpha.modifyDateAssigned(1, 5, 2020);
        alpha.update(33);

        Assignment bravo = new Assignment("HW2", "CS10",
                10, 15, 2020);
        bravo.modifyDateAssigned(1, 1, 2020);
        bravo.update(99);

        Assignment charlie = new Assignment("notes", "CS188",
                10, 11, 2020);
        charlie.modifyDateAssigned(3, 28, 2020);
        charlie.update(100);

        Assignment delta = new Assignment("HW1", "CS188",
                10, 13, 2020);
        delta.modifyDateAssigned(1, 2, 2020);
        delta.update(10);

        Assignment echo = new Assignment("syllabus", "ESPM10",
                10, 12, 2020);
        echo.modifyDateAssigned(1, 6, 2020);
        echo.update(27);

        Q qu = new Q();
        qu.push(alpha);
        qu.push(bravo);
        qu.push(charlie);
        qu.push(delta);
        qu.push(echo);

        qu.setSortSetting(1);
        assertEquals("Incorrect ordering.", delta, qu.get(0));
        assertEquals("Incorrect ordering.", echo, qu.get(1));
        assertEquals("Incorrect ordering.", alpha, qu.get(2));
        assertEquals("Incorrect ordering.", bravo, qu.get(3));
        assertEquals("Incorrect ordering.", charlie, qu.get(4));
        assertEquals("Incorrect length.", 5, qu.length());

        qu.update("notes", "CS188", 25);

        assertEquals("Incorrect ordering.", delta, qu.get(0));
        assertEquals("Incorrect ordering.", charlie, qu.get(1));
        assertEquals("Incorrect ordering.", echo, qu.get(2));
        assertEquals("Incorrect ordering.", alpha, qu.get(3));
        assertEquals("Incorrect ordering.", bravo, qu.get(4));
        assertEquals("Incorrect length.", 5, qu.length());

    }

    @Test
    public void updateDateAssndReSort() {
        Assignment alpha = new Assignment("HW1", "CS10",
                10, 10, 2020);
        alpha.modifyDateAssigned(1, 5, 2020);
        alpha.update(33);

        Assignment bravo = new Assignment("HW2", "CS10",
                10, 15, 2020);
        bravo.modifyDateAssigned(1, 1, 2020);
        bravo.update(99);

        Assignment charlie = new Assignment("notes", "CS188",
                10, 11, 2020);
        charlie.modifyDateAssigned(3, 28, 2020);
        charlie.update(100);

        Assignment delta = new Assignment("HW1", "CS188",
                10, 13, 2020);
        delta.modifyDateAssigned(1, 2, 2020);
        delta.update(10);

        Assignment echo = new Assignment("syllabus", "ESPM10",
                10, 12, 2020);
        echo.modifyDateAssigned(1, 6, 2020);
        echo.update(27);

        Q qu = new Q();
        qu.push(alpha);
        qu.push(bravo);
        qu.push(charlie);
        qu.push(delta);
        qu.push(echo);

        qu.setSortSetting(3);
        assertEquals("Incorrect ordering.", bravo, qu.get(0));
        assertEquals("Incorrect ordering.", delta, qu.get(1));
        assertEquals("Incorrect ordering.", alpha, qu.get(2));
        assertEquals("Incorrect ordering.", echo, qu.get(3));
        assertEquals("Incorrect ordering.", charlie, qu.get(4));
        assertEquals("Incorrect length.", 5, qu.length());

        qu.updateAssnd("HW2", "CS10", 5, 25, 2020);

        assertEquals("Incorrect ordering.", delta, qu.get(0));
        assertEquals("Incorrect ordering.", alpha, qu.get(1));
        assertEquals("Incorrect ordering.", echo, qu.get(2));
        assertEquals("Incorrect ordering.", charlie, qu.get(3));
        assertEquals("Incorrect ordering.", bravo, qu.get(4));
        assertEquals("Incorrect length.", 5, qu.length());

    }

    @Test
    public void clear() {
        Assignment alpha = new Assignment("HW1", "CS10",
                10, 10, 2020);

        Assignment bravo = new Assignment("HW2", "CS10",
                10, 15, 2020);

        // Should be cleared
        Assignment charlie = new Assignment("notes", "CS188",
                10, 11, 2020);
        charlie.update(100);

        Assignment delta = new Assignment("HW1", "CS188",
                10, 13, 2020);
        delta.update(10);

        Assignment echo = new Assignment("syllabus", "ESPM10",
                10, 12, 2020);
        echo.update(27);

        // Should be cleared
        Assignment foxtrot = new Assignment("proj1", "CS10",
                10, 20, 2020);
        foxtrot.update(100);


        Q qu = new Q();
        qu.push(alpha);
        qu.push(bravo);
        qu.push(charlie);
        qu.push(delta);
        qu.push(echo);
        qu.push(foxtrot);

        qu.clean();
        for (int i = 0; i < qu.length(); i++) {
                assertEquals("Q not properly cleared.", qu.get(i).getName().equals("proj1"), false);
                assertEquals("Q not properly cleared.", qu.get(i).getName().equals("notes"), false);
        }

    }
}
