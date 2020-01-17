import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Assignment class.
 *  @author O Turchetti
 */
public class AssignmentTest {

    @Test
    public void basicAssignment() {
        Assignment alpha = new Assignment("HW10", "CS61a",
                1, 1, 2020);
        assertEquals("Incorrect Name", "HW10", alpha.getName());
    }
}


