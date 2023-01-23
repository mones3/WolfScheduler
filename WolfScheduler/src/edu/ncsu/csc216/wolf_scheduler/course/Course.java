
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Course object contains a Course's name, title, section, credit hours,
 * instructorId, and meeting time information. Courses can be added to a
 * student's Schedule and to the Course Catalog in the WolfScheduler class
 * 
 * @author symone
 *
 */
public class Course extends Activity {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Minimum number of characters allowed for Course's name */
	private static final int MIN_NAME_LENGTH = 5;

	/** Maximum number of characters allowed for Course's name */
	private static final int MAX_NAME_LENGTH = 8;

	/** Minimum number of letters allowed for the start of Course's name */
	private static final int MIN_LETTER_COUNT = 1;

	/** Maximum number of letters allowed for the start Course's name */
	public static final int MAX_LETTER_COUNT = 4;

	/** Number of digits allowed in Course's name */
	public static final int DIGIT_COUNT = 3;

	/** Number of characters allowed in Course section */
	public static final int SECTION_LENGTH = 3;

	/** Minimum number of credits allowed for Course */
	public static final int MIN_CREDITS = 1;

	/** Maximum number of credits allowed for Course */
	public static final int MAX_CREDITS = 5;

	/** The upper military time */
	public static final int UPPER_TIME = 2400;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */

	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown. Sets
	 * the Course's name.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 5 or
	 *                                  greater than 8
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		int letterCount = 0;
		int digitCount = 0;
		Boolean spaceflag = false;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!spaceflag) {
				if (Character.isLetter(c)) {
					letterCount++;
				} else if (c == ' ') {
					spaceflag = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}

			else if (spaceflag) {
				if (Character.isDigit(c)) {
					digitCount++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");

				}

			}
		}
		if (letterCount < MIN_LETTER_COUNT || letterCount > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (digitCount != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;

	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. Throws an IllegalArgumentException with message
	 * "Invalid section." if section is null or does not contain three characters or
	 * three characters are not digits.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null or empty String.
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		for (int i = 0; i < section.length(); i++) {
			char c = section.charAt(i);
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException("Invalid section.");
			}

		}
		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits. Throws an IllegalArugmentException with message
	 * "Invalid credits" if Course credit is out of bounds (less than 1 or greater
	 * than 5).
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits are greater than 5 or less than 1
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor id.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor id Throws and IllegalArgumentException with
	 * message “Invalid instructor id.” if instructorId is null or empty string.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if string is null or empty.
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hasCode for Course using all fields.
	 * 
	 * @return hasCode for Course.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare.
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns an array of length 4 containing the Course name, section, title, and
	 * meeting string.
	 * 
	 * @return array containing Course information
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = { name, section, getTitle(), getMeetingString() };
		return shortDisplayArray;
	}

	/**
	 * Returns an array of length 7 containing the Course name, section, title,
	 * credits, instructorId, meeting string, empty string (for a field that Event
	 * will have that Course does not).
	 * 
	 * @return array containing Course information.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = { name, section, getTitle(), "" + credits, instructorId, getMeetingString(), "" };
		return longDisplayArray;
	}

	/**
	 * Checks meeting days do not consist of any characters other than ‘M’, ‘T’,
	 * ‘W’, ‘H’, ‘F’, or ‘A’. Checks if ‘A’ is in the meeting days list, if so
	 * ensures is is the only character. Throws IllegalArgumentException if
	 * meetingDays, startTime, or endTime fields are invalid (using criteria above).
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);

		} else {

			if (meetingDays == null || "".equals(meetingDays) || !meetingDays.matches("[MTWHF]+")) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Checks if an activity object is a Course and whether that course is a
	 * duplicate
	 * 
	 * @param activity Activity to be compared
	 * @return true if activity is a duplicate course
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course other = (Course) activity;
			return this.getName().equals(other.getName());
		}
		return false;
	}
}
