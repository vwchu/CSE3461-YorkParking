package app.helpers;

import app.views.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * This class facilitate switching between views (pages)
 * in the application. Provides multi-panel design
 * pattern in the application. Is a singleton.
 */
public class MultiPanel extends CardLayout {

	public static final MultiPanel SELF = new MultiPanel(); // Reference to singleton

	private final Map<String, AbstractView> VIEWS =
		new HashMap<String, AbstractView>(); 		// Map of view name to views
	private JFrame parent = null; 				 	// Reference to parent container         

	private MultiPanel() { } // private constructor

	/**
	 * Set the parent (wrapping) container.
	 * 
	 * @param parent	the wrapping container
	 * @return 			this object (for chaining)
	 */
	public MultiPanel setParent(JFrame parent) {
		this.parent = parent;
		return this;
	}

	/**
	 * Adds the given view and associates it with the given naem.
	 * Returns true if successfully added, otherwise false.
	 * Condition to add views: name unique (does not already exist) and
	 * parent is set.
	 * 
	 * @param name		the lookup (view name) string
	 * @param av		the view to add
	 * @return			true if successfully added, otherwise false.
	 */
	public boolean add(String name, AbstractView av) {
		if (parent != null && !VIEWS.containsKey(name)) {
			addLayoutComponent(av, name);
			VIEWS.put(name, av);
			parent.add(av);
			return true;
		}
		return false;
	}
	public boolean add(AbstractView av) {
		return add(av.getName(), av);
	}

	/**
	 * Display the view associated with the given name.
	 * Returns true if successfully added, otherwise false.
	 * Condition to add views: name exists and parent is set.
	 * 
	 * @param name		of the view to display
	 * @return			true if successfully added, otherwise false.			
	 */
	public boolean show(String name) {
		if (parent != null && VIEWS.containsKey(name)) {
			show(parent.getContentPane(), name);
			VIEWS.get(name).prepareView();
			return true;
		}
		return false;
	}
}
