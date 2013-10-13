package app.helpers;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

/**
 * This static class contains a collection of commonly
 * used methods for constructing, displaying, manipulating
 * user interface components.
 */
public class UITools {

	private UITools() { } // static class, no constructor

    /**
     * Add the panel to the outer panel and box.
     * This fixes the size of the panel, so it does not
     * expand when resized.
     * 
     * @param outer		panel to be added to.
	 * @param inner		panel to add to the outer. 
	 */
    public static void box(JPanel outer, JPanel inner) {
    	Box box = Box.createVerticalBox();
        box.add(inner);
        outer.add(box);
    }

	/**
	 * Set the size of the given of the component.
	 * 
	 * @param comp	component to set the size of
	 * @param size	width and height of the panel 
	 */
	public static void setSize(Component comp, Dimension size) {
		comp.setPreferredSize(size);
		comp.setMinimumSize(size);
		comp.setMaximumSize(size);
		comp.setSize(size);
	}    

}
