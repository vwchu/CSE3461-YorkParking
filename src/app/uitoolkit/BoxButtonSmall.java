package app.uitoolkit;

import app.helpers.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * This class implements a small horizontal button
 * consisting of a large icon and a label text.
 */
public class BoxButtonSmall extends BoxButton {

	private static final int HEIGHT    = 50;	// Button height
	private static final int FONT_SIZE = 18;	// Label / icon font size

	/**
	 * Construct a horizontal icon button.
	 * Two layouts available: (icon on the left or icon on the right)
	 * 
	 * @param name			of the button. Used to reference it.
	 * @param icon			to show in the center of the button. (large)
	 * @param label			to show in the bottom-left of the button
	 * @param listener		to perform an action when clicked.
	 * @param width			the width of the button. If width is -1, width is
	 * 						computed automatically based on the length of the string. 						
	 * @param rightIcon		if true, right justified (icon on the right), else
	 * 						left justified (icon on the left). Default: false
	 */
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, int width, boolean rightIcon) {
		super(name, icon, label, listener);
		if (!ENTITY.isEmpty()) {
			label = rightIcon
				? (label.isEmpty() ? "" : label.toUpperCase() + " ") + ENTITY
				: ENTITY + (label.isEmpty() ? "" : " " + label.toUpperCase());
			if (!label.isEmpty()) {
				setMargin(new Insets(10, 0, 10, 10));
			}
		}
		setFont(MyFont.REGULAR_FONT.deriveFont((float)FONT_SIZE));
		setText(label);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		if (width == -1) {width = getFontMetrics(getFont()).stringWidth(label) + 60;}
		UIToolbox.setSize(this, new Dimension(width, HEIGHT));
	}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, int width) {
		this(name, icon, label, listener, width, false);}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener) {
		this(name, icon, label, listener, -1, false);}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, boolean rightIcon) {
		this(name, icon, label, listener, -1, rightIcon);}
	public BoxButtonSmall(String name, String icon, String label) {
		this(name, icon, label, null, -1, false);}
	public BoxButtonSmall(String name, String icon, String label, boolean rightIcon) {
		this(name, icon, label, null, -1, rightIcon);}

	// FOR TESTING PURPOSES ONLY

	public static void main(String[] args) {
		UITheme.setLookAndFeel();
		JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        ActionListener al = new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		System.out.println(((JButton)event.getSource()).getName());
        	}
        };
        frame.add(new BoxButtonSmall("HOME", "HOME", "Home", al));
        frame.add(new BoxButtonSmall("BACK", "BACK", "Back", al));
        frame.add(new BoxButtonSmall("PREV", "PREV", "Prev", al, 200));
        //frame.add(new PageCounter(5, 10, 400));
        frame.add(new BoxButtonSmall("NEXT", "NEXT", "Next", al, 200, true));
        frame.add(new BoxButtonSmall("CONTINUE", "FORW", "Continue", al, true));
        frame.add(new BoxButtonSmall("EXIT", "EXIT", "Exit", al, true));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
}
