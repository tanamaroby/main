package seedu.savenus.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.savenus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RestrictionsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Restrictions(null));
    }

    @Test
    public void constructor_invalidRestrictions_throwsIllegalArgumentException() {
        String invalidRestrictions = "";
        assertThrows(IllegalArgumentException.class, () -> new Restrictions(invalidRestrictions));
    }

    @Test
    public void isValidRestrictions() {
        // null restrictions
        assertThrows(NullPointerException.class, () -> Restrictions.isValidRestrictions(null));

        // valid restrictions
        assertTrue(Restrictions.isValidRestrictions("PeterJack1examplecom"));
        assertTrue(Restrictions.isValidRestrictions("food")); // minimal
        assertTrue(Restrictions.isValidRestrictions("steak with cheese and chilli")); // alphabets only
    }

    @Test
    public void isBlankRestrictions() {
        // blank restrictions
        assertFalse(Restrictions.isValidRestrictions("")); // empty string
        assertFalse(Restrictions.isValidRestrictions(" ")); // spaces only
        assertFalse(Restrictions.isValidRestrictions("          ")); // tons of spaces
    }
}
