/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test the Activity Class
 * 
 * @author symone
 *
 */
class ActivityTest {

	/**
	 * If the two activities should NOT lead to a conflict, then a call to
	 * Activity.checkConflict() should not throw an exception.
	 */

	@Test
	public void testcheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330,
				1445);

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

	/**
	 * If the two activities lead to a conflict, then a call to
	 * Activity.checkConflict() should throw an exception.
	 */

	@Test
	public void testCheckConflictWithConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
	}

	/**
	 * Test check conflict when there is an overlap in time.
	 */
	@Test
	public void testCheckConflictOverlap() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330,
				1445);

		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1440,
				1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

	}

	/**
	 * Tests checkConflict() when there is a complete overlap in time and days
	 */
	@Test
	public void testCheckConflictCompleteOverlap() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1440,
				1445);

		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1440,
				1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

	}

	/**
	 * Tests checkConflict() when there is a conflict on a single day
	 */
	@Test
	public void testCheckConflictSingleDayOverlap() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MF", 1430,
				1500);

		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MT", 1300,
				1445);

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());

		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());

	}
}
