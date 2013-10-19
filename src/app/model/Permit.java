package app.model;

import java.sql.*;

/**
 * In-memory representation of a permit. Read-only.
 */
public class Permit {

	private final Vehicle vehicle;
	private final Date startDate, endDate, issueDate;
	private final int days;
	
	/**
	 * Construct a new Permit Object
	 * 
	 * @param vehicle		the vehicle assigned to this permit
	 * @param startDate		the start date of the permit
	 * @param endDate		the end date of the permit
	 * @param issueDate		the date and time the permit was issued
	 */	
	public Permit(Vehicle vehicle, Date startDate, Date endDate, Date issueDate) {
		this.vehicle = vehicle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.issueDate = issueDate;
		this.days = 0; // FIXME
	}
	//public Permit(Vehicle vehicle, Date startDate, int days) {
		//this(vehicle, startDate, DBManager.computeExpiryDate(startDate, days), null);
	//}

	public Vehicle getVehicle() {return vehicle;}
	public Date getStartDate() {return startDate;}
	public Date getEndDate() {return endDate;}
	public Date getIssueDate() {return issueDate;}
	public int getDaysLeft() {return days;}

}
