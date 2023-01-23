package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Super class of Course and Event. Represents the activities of course's and
 * event's contains title, meetingDays, startTime, and endTime fields.
 * 
 * @author symone
 *
 */
public abstract class Activity implements Conflict {


	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Upper value for hour used for Activity meeting times */
	private static final int UPPER_HOUR = 23;
	/** Upper value for minute used four Activity meeting times */
	private static final int UPPER_MINUTE = 59;
	/** The upper military time */
	private static final int UPPER_TIME = 2400;

	/**
	 * Constructor for activity
	 * 
	 * @param title       title of Activity
	 * @param meetingDays meeting days for activity
	 * @param startTime   start time of activity
	 * @param endTime     end time of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Used to populate the rows of the course catalog and student schedule.
	 * 
	 * @return string array of the course catalog and student schedule
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Used to display the student's final schedule
	 * 
	 * @return string array of the Students final schedule
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Returns the Activity's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title. Throws an IllegalArgumentException if title is
	 * null or empty string with the message "Invalid title"
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");

		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's meetingDays, startTime, and endTime. Throws and
	 * IllegalArgumentException if meeting days is null, empty, or contains invalid
	 * characters. if an arranged class has non-zero start or end times, if start
	 * time or end time is an incorrect time of if end time is less than start time.
	 * 
	 * @param meetingDays the meeting days to set
	 * @param startTime   the start time to set
	 * @param endTime     the end time to set
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int count = 0;
		int currentLetter = 0;

		// char array for meeting days
		char[] c = meetingDays.toCharArray();

		for (int i = 0; i < c.length; i++) {
			currentLetter = c[i];
			count = 0;
			for (int j = 0; j < c.length; j++) {

				if (currentLetter == c[j]) {
					count++;
				}
			}
			if (count > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;

		int endHour = endTime / 100;
		int endMin = endTime % 100;

		if (startHour > UPPER_HOUR || startHour < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (startMin > UPPER_MINUTE || startMin < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endHour > UPPER_HOUR || endHour < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endMin > UPPER_MINUTE || endMin < 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns Activity's meeting time and day information represented as a string
	 * 
	 * @return Activity's meetingString
	 */
	public String getMeetingString() {
		String days = getMeetingDays();
		if ("A".equals(days)) {
			return "Arranged";
		}

		int start = getStartTime();
		int end = getEndTime();
		int hours;
		int minutes;
		String strStartTime = "";
		String strEndTime = "";

		boolean isPM = false;

		if (start >= UPPER_TIME / 2) {
			isPM = true;

		}
		hours = start / 100;
		minutes = start % 100;

		if (hours > 12) {
			hours -= 12;
		}

		if (!isPM) {
			if (minutes % 100 == 0) {
				strStartTime = hours + ":" + minutes + "0AM";
			} else {
				strStartTime = hours + ":" + minutes + "AM";
			}

		} else {
			if (minutes % 100 == 0) {
				strStartTime = hours + ":" + minutes + "0PM";
			} else {
				strStartTime = hours + ":" + minutes + "PM";
			}
		}

		isPM = false;
		if (end >= UPPER_TIME / 2) {
			isPM = true;
		}

		hours = end / 100;
		minutes = end % 100;

		if (hours > 12) {
			hours -= 12;
		}

		if (!isPM) {
			if (minutes % 100 == 0) {
				strEndTime = hours + ":" + minutes + "0AM";
			} else {

				strEndTime = hours + ":" + minutes + "AM";
			}
		} else {

			if (minutes % 100 == 0) {
				strEndTime = hours + ":" + minutes + "0PM";
			} else {
				strEndTime = hours + ":" + minutes + "PM";
			}
		}

		return getMeetingDays() + " " + strStartTime + "-" + strEndTime;
	}

	/**
	 * Generates a hasCode for Activity using all fields.
	 * 
	 * @return hasCode for Activity.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks if activity is a duplicate.
	 * 
	 * @param activity activity to be checked
	 * @return true or false if activity is duplicated
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Implements checkConflict()
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean sameTime = false;
		boolean sameDay = false;

		if (this.getStartTime() <= possibleConflictingActivity.getStartTime()
				&& this.getEndTime() >= possibleConflictingActivity.getStartTime()
				|| this.getStartTime() <= possibleConflictingActivity.getEndTime()
						&& this.getEndTime() >= possibleConflictingActivity.getEndTime()
				|| this.getStartTime() >= possibleConflictingActivity.getStartTime()
						&& this.getEndTime() <= possibleConflictingActivity.getEndTime()
				|| this.getStartTime() <= possibleConflictingActivity.getStartTime()
						&& this.getEndTime() >= possibleConflictingActivity.getEndTime()) {
			sameTime = true;
		}

		int search = -1;
		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			char c = this.getMeetingDays().charAt(i);
			search = possibleConflictingActivity.getMeetingDays().indexOf(c);
			if (search > -1) {
				sameDay = true;
			}
		}

		if (this.getMeetingDays().equals("A") || possibleConflictingActivity.getMeetingDays().equals("A")) {
			sameDay = false;
		}

		if (sameDay && sameTime) {
			throw new ConflictException();
		}

	}


}
