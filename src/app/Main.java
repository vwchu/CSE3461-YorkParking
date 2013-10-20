package app;

import java.awt.*;

import javax.swing.*;

import app.helpers.*;
import app.model.*;
import app.views.*;

/**
 * This class is the root class of the application.
 */
public class Main {

	public static User USER;

	// TODO: Implement Window Close event to close DBManager before app terminates

	public static void createAndShowGUI() {
    	UITheme.setLookAndFeel();
    	JFrame frame = new JFrame();
    	UIToolbox.fullscreen(frame);
    	frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLayout(MultiPanel.SELF.setParent(frame));
    		MultiPanel.SELF.add(new LoginPage());
    		MultiPanel.SELF.add(new HomePage());
    		MultiPanel.SELF.add(new UserPage());
    			MultiPanel.SELF.add(new ChangePINPage());
    		MultiPanel.SELF.add(new VehiclesPage());
    			MultiPanel.SELF.add(new EditVehiclePage());
    			MultiPanel.SELF.add(new UpdateInsurance());
    		MultiPanel.SELF.add(new SubscriptionPage());
        frame.pack();
        frame.setVisible(true);
	}

	public static void main(String[] arg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() { createAndShowGUI(); }
		});
	}
}
