package app.helpers;

import java.awt.*;
import javax.swing.*;

/**
 * This static class contains a collection of commonly
 * used methods for constructing, displaying, manipulating
 * user interface components.
 */
public class UITools {

	private UITools() { } // static class, no constructor

	/**
	 * Return the screen size.
	 * 
	 * @return the screen size.
	 */
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();  
	}

    /**
     * Add the panel to the outer panel and box.
     * This fixes the size of the panel, so it does not
     * expand when resized.
     * 
     * @param outer		panel to be added to.
	 * @param inner		panel to add to the outer.
	 * @return			the outer panel 
	 */
    public static JPanel box(JPanel outer, JPanel inner) {
    	Box box = Box.createVerticalBox();
        box.add(inner);
        outer.add(box);
        return outer;
    }

	/**
	 * Set the size of the given of the component.
	 * 
	 * @param comp	component to set the size of
	 * @param size	width and height of the panel
	 * @return		the component that was given. 
	 */
	public static Component setSize(Component comp, Dimension size) {
		comp.setPreferredSize(size);
		comp.setMinimumSize(size);
		comp.setMaximumSize(size);
		comp.setSize(size);
		return comp;
	}

}
