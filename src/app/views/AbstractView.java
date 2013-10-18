package app.views;

import app.helpers.*;
import app.model.*;
import app.uitoolkit.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Abstract implementation of a view (page) in the application.
 * Facilitates action listeners for button actions. 
 */
public abstract class AbstractView extends JPanel implements ActionListener {

	private final TitlePane TITLE = new TitlePane(); // Reference to title panel
	protected User USER_OBJ = null;		 	         // FIXME

	/**
	 * Constructs a new view.
	 * Sets the title and add itself to the multi-panel controller.
	 * 
	 * @param tp		reference to the shared title pane 
	 * @param name		the lookup name associated with the view
	 * @param text		the title text
	 */
	public AbstractView(String name, String text) {
		setLayout(new BorderLayout());
		setName(name);
		if (text != null) {
			TITLE.setText(text);
			add(TITLE, BorderLayout.NORTH);
		}
	}
	
	/**
	 * Prepare the view for displaying.
	 * Invoked by MultiPanel.show before view is displayed.
	 *
	 * @param args		arguments needed to prepare the view 
	 */
	public void prepareView(Object... args) {
		TITLE.setUserTag(USER_OBJ);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		if (name == "EXIT") {
			// TODO
		} else {
			MultiPanel.SELF.show(name);
		}
	}

	// FOR TESTING PURPOSES ONLY
	
	public static void main(String[] arg) {
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
    		MultiPanel.SELF.add(new SubscriptionPage());
    		MultiPanel.SELF.add(new VehiclesPage());
        frame.pack();
        frame.setVisible(true);
	}
}
