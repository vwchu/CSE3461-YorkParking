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
	 * @param width			the width of the button. If width is -1, width is
	 * 						computed automatically based on the length of the string. 						
	 * @param iinsets		the inner padding of the icon. Default: Insets(10, 10, 10, 5)
	 * @param linsets		the inner padding of the label. Default: Insets(10, 5, 10, 10)
	 * @param rightIcon		if true, right justified (icon on the right), else
	 * 						left justified (icon on the left). Default: false
	 */
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, int width, boolean rightIcon) {
		super(name, icon, label, listener);
		JButton ib = (JButton)INNER.getComponent(0);
		JButton lb = (JButton)INNER.getComponent(1);

		// Remove the buttons
		// Rearrange the button layout
		INNER.remove(ib);
		INNER.remove(lb);
		INNER.add(lb, BorderLayout.CENTER);

		lb.setFont(MyFont.BODYTEXT_FONT.deriveFont(Font.PLAIN, LABEL_SIZE));	
		if (icon != null) {
			INNER.add(ib, rightIcon ? BorderLayout.EAST : BorderLayout.WEST);		
			ib.setFont(MyFont.ICON_FONT.deriveFont(Font.PLAIN, ICON_SIZE));

			Insets iinsets = new Insets(10, 10, 10, 5);
			Insets linsets = new Insets(10, 5, 10, 10);

			// compute the font width
			int fwidth = ib.getFontMetrics(ib.getFont()).stringWidth(icon)
					   + lb.getFontMetrics(lb.getFont()).stringWidth(label)
					   - iinsets.left - iinsets.right
					   - linsets.left - linsets.right;

			if (width == -1) {
				width = fwidth;
			} else {
				// automatically center the icon and label
				linsets.right = iinsets.left = (width - fwidth) / 2;
			}
			width += 10; // for padding

			// swap the padding and re-alignment the label
			if (rightIcon) {
				int temp = iinsets.left; 
					iinsets.left = iinsets.right;
					iinsets.right = temp;
				temp = linsets.left; 
					linsets.left = linsets.right;
					linsets.right = temp;
				lb.setHorizontalAlignment(SwingConstants.RIGHT);
			}

			// set the padding
			ib.setMargin(iinsets);			
			lb.setMargin(linsets);
		} else {
			lb.setHorizontalAlignment(SwingConstants.CENTER);
			if (width == -1) {
				// the addition 40px is for padding
				width = lb.getFontMetrics(lb.getFont()).stringWidth(label) + 40;
			}
		}
		setPanelSize(INNER, new Dimension(width, HEIGHT));
	}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, int width) {
		this(name, icon, label, listener, width, false);}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener, boolean rightIcon) {
		this(name, icon, label, listener, -1, rightIcon);}
	public BoxButtonSmall(String name, String icon, String label, ActionListener listener) {
		this(name, icon, label, listener, false);}

	// FOR TESTING PURPOSES ONLY

	public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        ActionListener al = new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		System.out.println(((JButton)event.getSource()).getName());
        	}
        };
        frame.add(new BoxButtonSmall("HOME", "HOME", "Home", al));
        frame.add(new BoxButtonSmall("BACK", "BACK", "Back", al));
        frame.add(new BoxButtonSmall("PREV", "PREV", "Prev", al, 200, false));
        frame.add(new BoxButtonSmall("NEXT", "NEXT", "Next", al, 200, true));
        frame.add(new BoxButtonSmall("CONTINUE", "FORW", "Continue", al, true));
        frame.add(new BoxButtonSmall("EXIT", "EXIT", "Exit", al, true));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
}
