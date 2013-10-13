package app.uitoolkit;

import app.helpers.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * This class implements a large square button (tile)
 * consisting of a large icon and a label text. 
 */
public class BoxButton extends JButton {

	private static final int SIZE = 200;		// Button size (width and height)
	private static final int ICON_SIZE = 100;	// Icon font size
	private static final int LABEL_SIZE = 16;	// Label font size

	protected final String ENTITY;				// HTML entity for the icon.

	private static final String HTML =
		  "<html><body style=\"font-family: OpenSansRegularIcons;\">"
		+ "<center style=\"font-size:"+ICON_SIZE+"pt;\">{ENTITY}</center>"
		+ "<br /><center style=\"font-size: "+LABEL_SIZE+"pt;\">{LABEL}</center>"
		+ "</body></html>";

	/**
	 * Construct a new button.
	 * 
	 * @param name		of the button. Used to reference in it.
	 * @param icon		to show in the center of the button. (large)
	 * @param label		to show in the bottom-left of the button
	 * @param listener	to perform an action when clicked.
	 */
	public BoxButton(String name, String icon, String label, ActionListener listener) {
		setName(name);
		ENTITY = (MyFont.ICONS.get(icon) != null) ? MyFont.ICONS.get(icon) : "";
		setText(HTML.replace("{ENTITY}", ENTITY).replace("{LABEL}", label));
		if (listener != null) {
			addActionListener(listener);
		}
		UIToolbox.setSize(this, new Dimension(SIZE, SIZE));
	}
	public BoxButton(String name, String icon, String label) {
		this(name, icon, label, null);}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
    	UITheme.setLookAndFeel();
    	JFrame frame = new JFrame();
        JPanel inner = new JPanel();
	        inner.setLayout(new GridLayout(2, 3));
	        ActionListener al = new ActionListener() {
	        	public void actionPerformed(ActionEvent event) {
	        		System.out.println(((JButton)event.getSource()).getName());
	        	}
	        };
	        inner.add(new BoxButton("USER", "USER", "My Profile", al));
	        inner.add(new BoxButton("VEHICLES", "CAR", "My Vehicles", al));
	        inner.add(new BoxButton("SUBSCIPTION", "MAIL", "Subscription", al));
	        inner.add(new BoxButton("NEW_PERMIT", "NEW", "Get Permit", al));
	        inner.add(new BoxButton("HISTORY", "FILES", "History", al));
	        inner.add(new BoxButton("PAY_NOW", "DOLLAR", "Pay Now", al));
        frame.add(UIToolbox.box(new JPanel(), inner));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
