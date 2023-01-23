/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This exception class extends Exception. 
 * Outputs a  message defining
 * two classes or events occur at the same time.
 * @author symone
 *
 */
public class ConflictException extends Exception {
	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	
	/**
     * Constructor for ConflictException (parameterized).
     * @param message is an error message.
     */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructor for ConflictException (parameterless)
	 */
	public ConflictException() {
	    this("Schedule conflict.");
	}
}


