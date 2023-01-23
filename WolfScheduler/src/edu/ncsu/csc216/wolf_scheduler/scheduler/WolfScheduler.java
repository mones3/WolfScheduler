/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler reads in and stores as a list all of the Course records stored
 * in a file Additionally, WolfScheduler creates a schedule for the current user
 * (a student) and provides functionality for naming the schedule, adding a
 * Course to the schedule, removing a Course from the schedule, resetting the
 * schedule.
 * 
 * @author symone
 *
 */
public class WolfScheduler {

	/** list of activities in schedule */
	private ArrayList<Activity> schedule;

	/** list of all courses in catalog */
	private ArrayList<Course> catalog;

	/** schedule's title */
	private String title;

	/**
	 * Constructs an empty ArrayList to hold schedule. Sets title field to the
	 * default "My Schedule"
	 * 
	 * @param fileName file name for course records that will be read in and stored.
	 *                 * @throws IllegalArgumentException if there is an error
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<Activity>();
		this.title = "My Schedule";

		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file ");
		}

	}

	/**
	 * Returns a 2D String array of the catalog used in the GUI to create the table
	 * of course catalog information. There is a row for each Course and three
	 * columns for name, section, and title. If there are no Courses in the catalog,
	 * an empty 2D String array is returned.
	 * 
	 * @return catalogArray a 2D String array of the catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][3];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}

	/**
	 * Returns a 2D String array of the schedule. This array is used in the GUI to
	 * create the table of course catalog information. There is a row for each
	 * Course and four columns for name, section, and title. If there are no Courses
	 * in the schedule, an empty 2D String array is returned.
	 * 
	 * @return scheduleArray 2D array of the schedule
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduleArray = new String[schedule.size()][3];
		for (int i = 0; i < schedule.size(); i++) {
			scheduleArray[i] = schedule.get(i).getShortDisplayArray();
		}

		return scheduleArray;
	}

	/**
	 * Returns a 2D String array of the schedule with all information. This array is
	 * used in the GUI to create the table of course catalog information. There is a
	 * row for each Course and seven columns for name, section, title, credits,
	 * instructorId, and the meeting days string. If there are no Courses in the
	 * schedule, an empty 2D String array is returned.
	 * 
	 * @return fullScheduleArray full schedule with all Course information.
	 */
	public String[][] getFullScheduledActivities() {
		String[][] fullScheduleArray = new String[schedule.size()][6];
		for (int i = 0; i < schedule.size(); i++) {
			fullScheduleArray[i] = schedule.get(i).getLongDisplayArray();
		}

		return fullScheduleArray;
	}

	/**
	 * Iterates through all the elements of the catalog ArrayList to find a Course
	 * with the given name and section and returns. Returns null if Course is not in
	 * catalog
	 * 
	 * @param name    name of the Course to search
	 * @param section section of the Course to search
	 * @return Course found in catalog, else returns null.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				return catalog.get(i);
			}

		}
		return null;
	}

	/**
	 * Returns true if the given Course (represented by the name and section) meets
	 * the following criteria: the course exists in the catalog and the course is
	 * successfully added to the student’s schedule. If the Course is not in the
	 * catalog, it cannot be added to the schedule and the method returns false.
	 * 
	 * @param name    name of the Course
	 * @param section section of the Course
	 * @return true if the Course can be added to the schedule else returns false.
	 * @throws IllegalArgumentException if name of course matches.
	 */
	public boolean addCourseToSchedule(String name, String section) throws IllegalArgumentException {
		Course courseToAdd = getCourseFromCatalog(name, section);

		for (int j = 0; j < catalog.size(); j++) {
			Course catalogCourse = catalog.get(j);
			if (catalogCourse.getName().equals(name)) {

				for (int i = 0; i < schedule.size(); i++) {
					Activity scheduleCourse = schedule.get(i);
					if (courseToAdd.isDuplicate(scheduleCourse)) {
						throw new IllegalArgumentException("You are already enrolled in " + courseToAdd.getName());
					} else {
						try {
							courseToAdd.checkConflict(schedule.get(i));
						} catch (ConflictException e) {
							throw new IllegalArgumentException("The course cannot be added due to a conflict.");
						}
					}

				}

				schedule.add(schedule.size(), courseToAdd);
				return true;
			}

		}

		return false;

	}

	/**
	 * If the new Event is a duplicate of an existing Event in the schedule, an
	 * IllegalArgumentException should be thrown with the message “You have already
	 * created an event called [event title]”.
	 * 
	 * @param eventTitle       title of the Event to be added
	 * @param eventMeetingDays meeting days of the Event to be added
	 * @param eventStartTime   start time of the Event to be added.
	 * @param eventEndTime     end time of the Event to be added.
	 * @param eventDetails     details of the Event to be added.
	 * @throws IllegalArgumentException if event is already in schedule
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			String eventDetails) throws IllegalArgumentException {

		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for (int i = 0; i < schedule.size(); i++) {
			Activity scheduleEvent = schedule.get(i);
			if (event.isDuplicate(scheduleEvent)) {
				throw new IllegalArgumentException("You have already created an event called " + event.getTitle());
			} else {
				try {

					event.checkConflict(scheduleEvent);

				} catch (ConflictException e) {
					throw new IllegalArgumentException("The event cannot be added due to a conflict.");
				}

			}

		}

		schedule.add(schedule.size(), event);
	}

	/**
	 * Returns true if the given Course (represented by the name and section) can be
	 * removed from the student’s schedule. The Course is then removed. The method
	 * returns false if the Course isn’t in the schedule.
	 * 
	 * @param idx index of the Activity to be removed from schedule.
	 * 
	 * @return false if the course can be removed, else returns true.
	 */
	public boolean removeActivityFromSchedule(int idx) {
		boolean result = false;
		try {
			schedule.remove(idx);
			result = true;

		} catch (IndexOutOfBoundsException e) {
			result = false;
		}
		return result;
	}

	/**
	 * Creates an empty array list for the schedule.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
		title = "My Schedule";

	}

	/**
	 * Sets the schedule title. Throws an IllegalArgumentException if the title is
	 * null with an error message of “Title cannot be null.”
	 * 
	 * @param title title of the schedule
	 * @throws IllegalArgumentException if title is null or empty string
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Returns the schedule title
	 * 
	 * @return scheduleTitle
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * Exports student's schedule to a file.
	 * 
	 * @param filename file name where student's schedule will be saved to.
	 * @throws IllegalArgumentException if CourseRecordsIO runs into errors.
	 */
	public void exportSchedule(String filename) {
		try {

			ActivityRecordIO.writeActivityRecords(filename, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");

		}
	}

}
