
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Constructs and contains details about the Event. Extends Activity class.
 * 
 * @author symone
 *
 */
public class Event extends Activity {

	/** Activity's title. */
	private String eventDetails;

	/**
	 * Constructor for Event. Contains parameters to construct an Activity
	 * 
	 * @param title        title of the Event
	 * @param meetingDays  meeting days of the Event
	 * @param startTime    start time of the Event
	 * @param endTime      end time of the Event
	 * @param eventDetails event details of the Event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Returns the event details of Event.
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the event details of Event. If eventDetails is null an illegal argument
	 * exception is thrown.
	 * 
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Returns a string array of length four. The first two values are empty (Since
	 * event doesn't have a name or section). The last two values are title and
	 * meeting strings.
	 * 
	 * @return shortArray short display array for Event
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = new String[4];
		shortArray[0] = "";
		shortArray[1] = "";
		shortArray[2] = this.getTitle();
		shortArray[3] = this.getMeetingString();
		return shortArray;
	}

	/**
	 * Return a String array of length seven. The first two values should be empty
	 * strings since Event doesn’t have a name or section. The third value is the
	 * title followed by two values with empty strings. The last two are the meeting
	 * string and eventDetails
	 * 
	 * @return longArray long display array for Event.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = new String[7];
		longArray[0] = "";
		longArray[1] = "";
		longArray[2] = this.getTitle();
		longArray[3] = "";
		longArray[4] = "";
		longArray[5] = this.getMeetingString();
		longArray[6] = this.getEventDetails();

		return longArray;
	}

	/**
	 * Returns a comma separated value String of Event fields.
	 * 
	 * @return String representation of Event
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + ","
				+ getEventDetails();
	}

	/**
	 * Checks meeting days do not consist of any characters other than ‘M’, ‘T’,
	 * ‘W’, ‘H’, ‘F’, 'U', or 'S' and meeting days is not null or an empty string.
	 * Throws IllegalArgumentException if meetingDays, startTime, or endTime fields
	 * are invalid (using criteria above).
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays) || !meetingDays.matches("[UMTWHFS]+")) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Checks if an activity object is a Event and whether that Event is a duplicate
	 * 
	 * @param activity Activity to be compared
	 * @return true if activity is a duplicate event
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event other = (Event) activity;
			return this.getTitle().equals(other.getTitle());
		}
		return false;
	}

}
