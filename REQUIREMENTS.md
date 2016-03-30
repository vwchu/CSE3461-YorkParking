# CSE 3461 User Interfaces: Fall 2013
## Assignment 1

Due Friday October 18, 2013 midnight in groups of 3

Your goal for this assignment is to create a Java application for a Parking
Permit Kiosk. Like most kiosks, interaction is via a touch screen. The system’s
keyboard is not used. For this assignment, we’ll use point-and-click operations
with the mouse to simulate a touch screen.

In designing the system, bear in mind that it is a kiosk: input is via the
user's finger on a touch screen. More than likely, interaction will take place
with the user standing in front of the kiosk, as with an ATM (automated teller
machine).

Our Parking Permit Kiosk is a system for use on the campus of York University to
issue vehicle parking permits to students. The following is required as input:

    Student Number – a 9-digit number (validated against database)
    PIN - 4-digit personal identification number (validated against database)
    Email address - optional, used to send parking news to clients
    Vehicle and Insurance Information - (see below)
    Expiry Date – month and year that permit expires

The system maintains a simple database of students at the university. For our
purpose, the database is a text file named [students.txt](assets/students.txt).
It contains one line for each student. Each line contains five comma-delimited
fields:

    <student number>,<PIN>,<family name>,<given name>,<status>

For example,

    123456789, 1234, Smith, Susan, ok
    987654321, 5678,Jones, John Paul, arrears
    132435465, 3475, McIvar, Alan, ok
    etc.

The first four fields are obvious enough. The fifth field contains either “ok”
or “arrears”, the latter indicating that the student has outstanding parking
fines. In this case, no parking permit should be issued.

For the vehicle, information such as make, model, and plate number may be
worthwhile to facilitate the enforcement of parking regulations.

A student vehicle must be insured by a registered insurance company in order to
receive a parking permit. A separate database stores all the names of registered
insurance companies, for example:

    Co-operators Insurance Company of Canada
    State Farm Underwriters Incorporated
    Allstate International Insurance Company Ltd.
    Metropolitan Life Insurance Company
    etc.

For our purposes, the database is a text file named
[companies.txt](assets/companies.txt). The user should identify the company with
which they are insured as well as provide a policy number. While the name of the
insurance company should be validated, the policy number provided is assumed
legitimate.

Billing is based on a fee of $3.50 per day from the day the permit is issued to
the expiry date. Billing is automatic to the student’s account.

Our simulated kiosk does not have a printer. However a permit should be produced
on the system’s display via a popup window. We’ll assume this is the equivalent
to printing the permit.

Remember, no keyboard!

----------------------------------------

**Notes:**

1.  Your application should be robust with a good user interface. Make
    appropriate use of the various GUI components and features supported in
    JFC/Swing. Consider the organization, presentation, interaction, and over-all
    usability of your program.
2.  Your source code should be well organised and documented.
3.  You must do this assignment in groups of three.
4.  Do not use the york package or the type package.
5.  Your programs must compile and run in the Prism lab.
6.  The assignments will be awarded letter grades, according to the university's
    letter grade standard. A program that simply fulfils the requirements described
    here will be awarded a 'C'. To get a higher grade, we want to see "an excellent
    job and originality" in your assignment solution.
7.  Name your program 'a1.java'. Submit it on Prism with 'submit 3461 a1
    a1.java'. Additional files, if required, may be submitted using the same
    command, with the filename as the last argument. (Use 'man submit' for further
    details on using the submit command.)
8.  Also submit a text file named [readme.txt](README.txt) in which you describe the
    motivation behind your software design. Why did you use the JFC/Swing and HTML
    components that you did, and why did you organise them that way? Briefly justify
    your design. If your implementation includes any special features, make sure you
    describe these and provide appropriate instructions.
9.  The readme.txt file should also contain a record of your group meetings.
    Include the date and time of each meeting, the people present, and the topics
    discussed. If other forms of communication were used (e.g., e-mail, phone),
    indicate the type and extent. Also include in your readme.txt file a record of
    the tasks assigned and performed by each member of the team.
10. Please make sure the first three lines of the readme.txt are formatted
    exactly as shown in the template file; namely,

        LoginID, StudentID, LastName, FirstName(s)
        LoginID, StudentID, LastName, FirstName(s)
        LoginID, StudentID, LastName, FirstName(s)
