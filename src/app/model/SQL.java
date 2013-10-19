package app.model;

import java.sql.*;

/**
 * Defines the set of all SQL statements used throughout the application.
 * Statements are parameterized and final, intended to executed as a
 * PreparedStatement to interface with the database.
 */
enum SQL {

	// ----- Queries -----
	GET_USER(
		"SELECT fname, sname, email, fines, lactive " +
        "FROM User " +
        "WHERE uid = ? AND pin = ?"),
	//GET_USERS(
	//    "SELECT U.sname, U.pin, U.fname, U.fines, U.email, U.lactive " +
	//    "FROM User U, User A " +
	//    "WHERE A.uid = 'admin' AND pin = ?"),
    GET_VEHICLES_BY_USER(
        "SELECT plate, make, model, year, insurer, policy, expiry " +
        "FROM Vehicle " +
        "WHERE owner = ?"),
    GET_PERMITS_BY_USER(
		"SELECT P.vehicle, V.make, V.model, V.year, V.insurer, V.policy, " +
               "V.expiry, P.start, P.expiry AS end, P.issued " +
        "FROM Permit P, Vehicle V" +
        "WHERE P.vehicle = V.plate AND V.owner = ?" +
        "ORDER BY P.expiry DESC"),
	GET_PERMIT_EXPIRY_BY_VEHICLE(
        "SELECT MAX(expiry) " +
        "FROM Permit " +
        "WHERE vehicle = ?"),
    GET_INSURERS(
    	"SELECT * " +
    	"FROM Insurer"),
    GET_AUTOMAKERS(
    	"SELECT * " +
    	"FROM AutoMaker"),
    GET_MODELS_BY_MAKE(
        "SELECT model " +
        "FROM Auto " +
        "WHERE make = ?"),
    GET_CURRENT_DATE(
        "SELECT DATE('now')"),
    COMPUTE_EXPIRY_DATE(
    	"SELECT DATE(JULIANDAY(?) + ?)"),

    // ----- Add Entries -----
    //ADD_USER(
	//    "INSERT INTO User(uid, pin, fname, sname, fines) " +
	//    "VALUES (?, ?, ?, ?, ?)"),
    //ADD_INSURER(
    //    "INSERT INTO Insurer(name) " +
    //    "VALUES (?)"),
    //ADD_AUTOMAKER(
    //    "INSERT INTO AutoMaker(name) " +
    //    "VALUES (?)"),
    //ADD_AUTO(
    //    "INSERT INTO Auto(make, model) " +
    //    "VALUES (?, ?)"),
    ADD_VEHICLE(
        "INSERT INTO Vehicle(plate, owner, make, model, year, insurer, policy, expiry) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
    ADD_PERMIT(
        "INSERT INTO PPKPermit(owner, vehicle, start, days) " +
        "VALUES (?, ?, ?, ?)"),

    // ----- Update Entries -----
    SET_USER_NAME(
        "UPDATE User " +
        "SET fname = ?, sname = ? " +
        "WHERE uid = ?"),
    SET_USER_PIN(
        "UPDATE User " +
        "SET pin = ? " +
        "WHERE uid = ? AND pin = ?"),
    SET_USER_FINES(
        "UPDATE User " +
        "SET fines = ? " +
        "WHERE uid = ?"),
    SET_USER_EMAIL(
        "UPDATE User " +
        "SET email = ? " +
        "WHERE uid = ?"),
    SET_USER_LASTACTIVE(
    	"UPDATE User " +
    	"SET lactive = DATETIME('now')" +
    	"WHERE uid = ?"),

    SET_VEHICLE_PLATE(
        "UPDATE Vehicle " +
        "SET plate = ?" +
        "WHERE plate = ?"),
    SET_VEHICLE_INSURANCE(
        "UPDATE Vehicle " +
        "SET insurer = ?, policy = ?, expiry = ? " +
        "WHERE plate = ?"),

    // ----- Delete Entries -----

	//DEL_USER(
	//    "DELETE FROM User " +
	//    "WHERE uid = ?"),
    DEL_VEHICLE(
        "DELETE FROM Vehicle " +
        "WHERE plate = ?")
    ;

    /** The parameterized SQL statement for the operation. */
    private final String STATEMENT;

    /**
     * The private constructor of the statements.
     *
     * @param sql 			the SQL parameterized string.
     */
    private SQL(String sql) {
        this.STATEMENT = sql + ";";
    }

    /**
     * Return the prepared statement for given database connection.
     *
     * @param conn 			the JDBC connection to the database.
     * @return 				the prepared statement for given database connection.
     * @throws SQLException if a database access error occurs or
     * 						this method is called on a closed connection
     */
    public PreparedStatement prepareStatement(Connection conn) throws SQLException {
        return conn.prepareStatement(STATEMENT);
    }

}
