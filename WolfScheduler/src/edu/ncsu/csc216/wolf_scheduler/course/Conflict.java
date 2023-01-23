package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is an interface that handles conflicts 
 * Error handles if two events or courses have conflicting times. 
 * @author symone
 *
 */
public interface Conflict {
	
	
	/**
	 * Establishes conflict exception
	 * @param possibleConflictingActivity is a conflicting activity
	 * @throws ConflictException if there are any conflicts 
	 */
	
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;


}
