package app.views;

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

    // A mapping of special key code names to the icon symbols
    protected static final Map<String, String> specialKeys = new HashMap<String, String>();
    static {
        specialKeys.put("SPACE", " ");
        specialKeys.put("BKSP", "\ue004");
        specialKeys.put("SHIFT", "\ue005");
        specialKeys.put("ENTER", "\ue006");
        specialKeys.put("BACK", "\ue000");
        specialKeys.put("FORW", "\ue001");
    }

    // Reference to the input field being modified
    protected JTextField field = null;
    
    // A mapping of key code names and the Button objects
    public final Map<String, AbstractButton> BLOOKUP = new HashMap<String, AbstractButton>();

    protected Keyboard(JTextField field) {
    	setInputComponent(field);
    }

    public void setInputComponent(JTextField field) {
        this.field = field;
    	for (String name : BLOOKUP.keySet()) {
    		setEnabled(BLOOKUP.get(name), field != null);
    	}
    }

    protected void setEnabled(AbstractButton ab, boolean enable) {
    	ab.setEnabled(enable);
    	ab.setEnabled(enable);
		ab.setOpaque(enable);
		ab.setContentAreaFilled(enable);
		ab.setForeground(enable ? Color.BLACK : Color.LIGHT_GRAY);
    }

    protected void addPanelToBox(JPanel inner) {
        Box box = Box.createVerticalBox();
        box.add(inner);
        add(box);
    }

    protected void setKeyLookAndFeel(AbstractButton ab, String key) {
        // Set font
        Font font = MyFont.BODYTEXT_FONT;
        if (specialKeys.containsKey(key)) {
            font = MyFont.ICON_FONT;
            ab.setText(specialKeys.get(key));
        } else {
        	ab.setText(key.toLowerCase());
        }
        ab.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));

        // Set look and feel
        ab.setFocusable(false);
        ab.setBackground(Color.WHITE);
        ab.setForeground(Color.BLACK);
    }

    public abstract void actionPerformed(ActionEvent event);

}
