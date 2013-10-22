========================================
YorkParking.app
Alpha 0.1
2013-10-22
========================================

cse13261, 211898400, Chu, Vincent
cse21044, 207382468, Patel, Simitkumar
cse13021, 210998565, Mangaru, David


---------------------
Special Requirements: (optional)
---------------------

Our program assumes that the user only uses the on screen keyboard and 
the numberpad provided by us and not the physical keyboard

To run the application, place YorkParking.jar and YorkParking.db in the same
directory. Then, from the terminal, run the command: java -jar YorkParking.jar

You can find the source embedded within the YorkParking.jar.

-------
Design:
-------

(Describe the motivation behind your software design. Why did you use
the JFC/Swing components that you did, and why did you organise them
that way? Briefly justify your design.)

The software design that we used for the user interface was Multipanel,
which was implemented using Card Layout from AWT. It was chosen to be 
multipanel because that allows for easy transitions between screens.
Each card is stateless in itself and state is transferred between the 
cards. However, Main class is implemented as a Singleton that keeps 
track of the sessions and user information.

For the user interface, We chose to make the components (Buttons, 
TextFields, etc.) large taking into account that someone might have 
large fingers.

In our design, we have many screens to collect information. As a result, 
the user is never presented with a lot of information at one time.
They are only required to enter a little amount of information for 
the particular screen.

We chose to use a combination of text fields and labels to present
information so that the user can get a sense of when to enter information
and when not to. (Text areas identify that the user has to enter
information, and labels do not).

The keyboard is only presented when information is required from the user.
This is to reduce confusion as the user will know when and where to enter
information, and when not to.

We also chose to make the screens similar so that the user can get accustomed
to using the interface quickly. The reasoning behind this is that the
user is only presented with few changes in the screens, which should
decrease the amount of time using the application.


--------------------
Additional Features: (optional)
--------------------

We implemented a database to store all the information about users.
Database is stored on a local file (included in JAR). Local database engine 
SQLite was used to retrieve and/or manipulate the data. 

Advantages of using database is that it allows users to edit information 
about themselves, and their existing vehicles. Additionally, it allows users
add vehicles to the database (for newer makes and models).

User Interface changes based on what information is already available 
The user is not presented with the same screens if previous information
is available.

There are three types of keyboards presented to the user based on the type
of input field. NumberPad, AlphaNumeric keyboard, and Alphabetic keyboard are
the three types of keyboards.

---------------
Communications:
---------------

2013-09-14  Email: from <V Chu> to <D Mangaru> and <S Patel>
            - Group formation
            - Git "CSE 3461 - Assignment 1" created
2013-09-17  Meeting: <V Chu>, <D Mangaru> and <S Patel>
            - Discuss feature set and requirements
2013-09-18  Meeting: <V Chu> and <S Patel>
            - Begin wireframing user interface
2013-09-19  Meeting: <V Chu>, <D Mangaru> and <S Patel>
            - Discuss user interface
            - Define display panels
            - Define SQL schema
2013-10-03  Email: <V Chu>, <D Mangaru> and <S Patel>
	    - Members send completed tasks to each other
2013-10-13  Meeting: <V Chu>, <D Mangaru> and <S Patel>
	    - Complete UI Screens
2013-10-20  Meeting: <V Chu>, <D Mangaru> and <S Patel>
	    - Final implementations of GUI back end

-----------------
Responsibilities:
-----------------

Vincent:
Database, GUI Functionality, and Testing 

Simit:
GUI Implementation, GUI Functionality

David:
GUI Implementation, Documentation

-----------------
Technical Issues:
-----------------

Following are some of the key issues that delayed the submission of the 
assignment. However, they have been fixed now.

- We wanted to limit the number of characters that user can type in for 
student number to 9 and PIN to 4. For that, we used the text formatting, 
which did not work properly and crashed on us during testing phase.
- We implemented GUI using modular approach, which was working fine when 
used with individual screen but was breaking when put together for the 
overall GUI.
- We also had problem with keeping track of session between different 
screens of the GUI for our overall multi-panel design.
- Last, we faced some technical difficulties while trying to deploy the 
project on prism computers. Server was responding very slowly and Eclipse 
IDE was taking very long time to import the project.