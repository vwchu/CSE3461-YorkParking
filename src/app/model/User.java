package app.model;

import java.util.*;

/**
 * In-memory representation of an user.
 * Is an observable object, observer pattern.
 */
public class User extends Observable {

    private long id;					// user student ID
    private String fname, sname, email;	// first name, surname, email address
    private double fines;				// outstanding fines
    private boolean ftime;              // first time using the application

    /**
     * Construct a new user object.
     *
     * @param id		user student ID
     * @param fname		first name
     * @param sname		surname (last name)
     * @param email		email address, maybe null
     * @param fines		outstanding fines
     * @param ftime     first time using the application
     */
    public User(long id, String fname, String sname, String email, double fines, boolean ftime) {
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.email = email;
        this.fines = fines;
        this.ftime = ftime;
    }

    // Getters

    public long    getID() {return id;}				// Returns user student ID
    public String  getFirstName() {return fname;}	// Returns first name
    public String  getSurName() {return sname;}		// Returns surname
    public String  getEmail() {return email;}		// Returns email address
    public double  getFines() {return fines;}		// Returns outstanding fines

    public boolean hasEmail() {return getEmail() != null;}	// Returns true if email address is assigned
    public boolean hasFines() {return getFines() > 0;}		// Returns true if fines are non-zero

    public boolean isFirstTime() {return ftime;}            // First time using the application

    // Setters

    /**
     * Update the user's first and last names
     *
     * @param fname		first name
     * @param sname		surname
     */
    public void setName(String fname, String sname) {
        this.fname = fname;
        this.sname = sname;
        //DBManager.updateName(id, fname, sname);
        notify("NAME");
    }

    /**
     * Update the user's email address
     *
     * @param email		subscription email address
     */
    public void setEmail(String email) {
        this.email = email;
        //DBManager.updateEmail(id, email);
        notify("EMAIL");
    }

    /**
     * Update the user's outstanding fines.
     *
     * @param fines		the outstanding fines
     */
    public void setFines(double fines) {
    	if (fines >= 0) {
    		throw new RuntimeException("Fines must be non-negative.");
    	}
    	this.fines = fines;
    	//DBManager.updateFines(id, fines);
        notify("FINES");
    }

    /**
     * Pay down the outstanding fines.
     *
     * @param payment	the amount paid.
     * @return			the amount of change.
     */
    public double payFines(double payment) {
    	if (payment >= 0) {
    		throw new RuntimeException("Payment must be non-negative.");
    	}
    	double change;
    	if (payment >= fines) {
    		change = payment - fines;
    		setFines(0);
    	} else {
    		change = 0;
    		setFines(fines - payment);
    	}
        return change;
    }
   
    // Methods for observer pattern

    /**
     * Notify observers with the given opcode
     *
     * @param opcode	to pass to the observers
     */
    public void notify(String opcode) {
    	setChanged();
    	notifyObservers(opcode);
    }

    @Override
    public void addObserver(Observer o) {
    	super.addObserver(o);
    	notify("ATTACH");
    }

    @Override
    public void deleteObserver(Observer o) {
    	notify("DETACH");
    	super.deleteObserver(o);
    }
}
