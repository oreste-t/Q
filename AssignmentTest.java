import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Assignment class.
 *  @author O Turchetti
 */
public class AssignmentTest {

    @Test
    public void basicAssignment() {
        Assignment alpha = new Assignment("HW10", "CS61a",
                -1, -1, -1);
        assertEquals("Incorrect Name", "HW10", alpha.getName());
    }

    @Test
    public void basicQDueDate() {
        Assignment bravo = new Assignment("HW10", "CS61a",
                8, 1, 2019);
        Assignment charlie = new Assignment("WW 1.1", "Math53", 8, 10, 2019);

        Q delta = new Q();
        delta.push(bravo);
        delta.push(charlie);
        assertEquals("Incorrect Order", bravo.getName(), delta.peek().getName());

        Assignment echo = new Assignment("Notes 17.2", "CS170", 7, 20, 2019);
        delta.push(echo);
        assertEquals("Incorrect Order", echo.getName(), delta.peek().getName());
    }
}


