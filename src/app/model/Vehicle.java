package app.model;

import java.sql.*;

/**
 * In-memory representation of a vehicle.
 */
public class Vehicle {

	private String plate, make, model, insurer, policy;
	private int year;
	private Date expiry;

	/**
	 * Construct a new vehicle object.
	 * 
	 * @param owner		user who owns the vehicle
	 * @param plate		the license plate
	 * @param make		the manufacturer
	 * @param model		the car model
	 * @param year		model year
	 * @param insurer	the insurance company
	 * @param policy	the insurance policy
	 * @param expiry	the expiry date of insurance
	 */
	public Vehicle(String plate, String make, String model, int year,
			String insurer, String policy, Date expiry) {
		this.plate = plate;
		this.make = make;
		this.model = model;
		this.year = year;
		this.insurer = insurer;
		this.policy = policy;
		this.expiry = expiry;
	}

	// Getters
	
	public String getPlate() {return plate;}
	public String getMake() {return make;}
	public String getModel() {return model;}
	public int    getModelYear() {return year;}
	public String getInsurer() {return insurer;}
	public String getPolicy() {return policy;}
	public Date   getExpiry() {return expiry;}

	// Setters
	
	public void setPlate(String plate) {
		this.plate = plate;
		//DBManager.setPlate(plate);
	}

	public void updateInsurance(String insurer, String policy, Date expiry) {
		this.insurer = insurer;
		this.policy = policy;
		this.expiry = expiry;
		//DBManager.updateInsurance(plate, insurer, policy, expiry);
	}

}
