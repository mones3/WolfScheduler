/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for ConflictException class.
 * @author symone
 *
 */
class ConflictExceptionTest {

	/**
	 * Test method for parameterized constructor.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}
	/**
	 * Test method for parameterless constructor.
	 */
	@Test
	public void testConflictException() {
		 ConflictException ce = new ConflictException();
		    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
