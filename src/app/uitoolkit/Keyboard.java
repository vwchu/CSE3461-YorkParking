package app.uitoolkit;

import app.helpers.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * This class provides at abstract definition of a keyboard.
 */
abstract class Keyboard extends JPanel implements ActionListener {

	public static final int KEY_HEIGHT   = 45; // height of each key
    public static final int KEY_WIDTH    = 75; // width of standard keys
    public static final int FONT_SIZE    = 20; // font size of keys
    public static final int KEY_SPACING  = 5;  // spacing between the keys horizontally

    // Reference to the input field being modified
    protected JTextField field = null;
    
    // A mapping of key code names and the Button objects
    public final Map<String, AbstractButton> BLOOKUP = new HashMap<String, AbstractButton>();

    /**
     * Default constructor of a keyboard.
     * 
     * @param field 	text input linked to the keyboard.
     */
    protected Keyboard(JTextField field) {
    	setInputComponent(field);
    }

    /**
     * Associates the text input with the keyboard.
     *  
     * @param field 	text input to link to the keyboard.
     */
    public void setInputComponent(JTextField field) {
        this.field = field;
    	for (String name : BLOOKUP.keySet()) {
    		setEnabled(BLOOKUP.get(name), field != null);
    	}
    }

    /**
     * Enable or disable the button on the keyboard.
     * 
     * @param ab 		button to enable or disable.
     * @param enable 	if true, enable, else disable.
     */
    protected void setEnabled(AbstractButton ab, boolean enable) {
    	ab.setEnabled(enable);
		ab.setOpaque(enable);
		ab.setContentAreaFilled(enable);
		ab.setForeground(enable ? Color.BLACK : Color.LIGHT_GRAY);
    }

    /**
     * Add the panel to the outer panel and box.
     * This fixes the size of the panel, so it does not
     * expand when resized.
     * 
     * @param inner 	panel to add to the outer.
     */
    protected void addPanelToBox(JPanel inner) {
        Box box = Box.createVerticalBox();
        box.add(inner);
        add(box);
    }

    /**
     * Style the keyboard button.
     * 
     * @param ab 	button to style.
     * @param key 	text on the button.
     * @param caps 	if true, upper case, else lower case. Default: false
     */
    protected void setKeyLookAndFeel(AbstractButton ab, String key, boolean caps) {
        // Set font
        Font font = MyFont.BODYTEXT_FONT;
        if (MyFont.ICONS.containsKey(key)) {
            font = MyFont.ICON_FONT;
            ab.setText(MyFont.ICONS.get(key));
        } else {
        	ab.setText(caps ? key.toUpperCase() : key.toLowerCase());
        }
        ab.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));

        // Set look and feel
        ab.setFocusable(false);
        ab.setBackground(Color.WHITE);
        ab.setForeground(Color.BLACK);
    }
    protected void setKeyLookAndFeel(AbstractButton ab, String key) {
    	setKeyLookAndFeel(ab, key, false);
    }

    /**
     * Invoked when an action occurs.
     * 
     * @param event		the event object.
     */
    @Override
    public abstract void actionPerformed(ActionEvent event);

}
