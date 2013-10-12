package app.views;

import app.helpers.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BoxButtonSmall extends BoxButton {

	private static final int HEIGHT = 50;										// Button height
	private static final int ICON_SIZE = 25;									// Icon font size
	private static final int LABEL_SIZE = 16;									// Label font size

	/**
	 * Construct a horizontal icon button.
	 * Two layouts available: (icon, label) or (label, icon)
	 * 
	 * @param name			of the button. Used to reference in it.
	 * @param icon			to show in the center of the button. (large)
	 * @param label			to show in the bottom-left of the button
	 * @param listener		to perform an action when clicked.
	 * @param width			the width of the button.
	 * @param iinsets		the inner padding of the icon. Default: Insets(10, 10, 10, 5)
	 * @param linsets		the inner padding of the label. Default: Insets(10, 5, 10, 10)
	 * @param rightIcon		if true, right justified (icon on the right), else
	 * 						left justified (icon on the left). Default: false
	 */
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, int width, Insets iinsets, Insets linsets, boolean rightIcon) {
		super(name, icon, label, listener);
		JButton ib = (JButton)INNER.getComponent(0);
		JButton lb = (JButton)INNER.getComponent(1);
		INNER.remove(ib);
		INNER.remove(lb);
		INNER.add(lb, BorderLayout.CENTER);
		if (icon != null) {
			INNER.add(ib, rightIcon ? BorderLayout.EAST : BorderLayout.WEST);
			ib.setFont(MyFont.ICON_FONT.deriveFont(Font.PLAIN, ICON_SIZE));
			if (rightIcon) {
				int temp = iinsets.left; 
					iinsets.left = iinsets.right;
					iinsets.right = temp;
				temp = linsets.left; 
					linsets.left = linsets.right;
					linsets.right = temp;
				lb.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			ib.setMargin(iinsets);			
			lb.setMargin(linsets);
		} else {
			lb.setHorizontalAlignment(SwingConstants.CENTER);
		}
		lb.setFont(MyFont.BODYTEXT_FONT.deriveFont(Font.PLAIN, LABEL_SIZE));
		setPanelSize(INNER, new Dimension(width, HEIGHT));
	}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, int width, boolean rightIcon) {
		this(name, icon, label, listener, width, new Insets(10, 10, 10, 5), new Insets(10, 5, 10, 10), rightIcon);
	}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, int width) {
		this(name, icon, label, listener, width, false);
	}

	// FOR TESTING PURPOSES ONLY

	public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        ActionListener al = new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		System.out.println(((JButton)event.getSource()).getName());
        	}
        };
        frame.add(new BoxButtonSmall("HOME", "HOME", "Home", al, 120));
        frame.add(new BoxButtonSmall("BACK", "BACK", "Back", al, 110));
        frame.add(new BoxButtonSmall("PREV", "PREV", "Prev", al, 200, new Insets(10, 50, 10, 10), new Insets(10, 0, 10, 10), false));
        frame.add(new BoxButtonSmall("NEXT", "NEXT", "Next", al, 200, new Insets(10, 50, 10, 10), new Insets(10, 0, 10, 10), true));
        frame.add(new BoxButtonSmall("CONTINUE", "FORW", "Continue", al, 150, true));
        frame.add(new BoxButtonSmall("EXIT", "EXIT", "Exit", al, 110, true));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
}
