package app.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * This class provides at abstract definition of a keyboard.
 */
abstract class Keyboard extends JPanel implements ActionListener {
    public static final int KEY_HEIGHT   = 45;
    public static final int KEY_WIDTH    = 75;
    public static final int FONT_SIZE    = 20;
    public static final int KEY_SPACING  = 5;
    protected static final Map<String, String> specialKeys = new HashMap<String, String>();
    static {
        specialKeys.put("SPACE", " ");
        specialKeys.put("BKSP", "\ue004");
        specialKeys.put("SHIFT", "\ue005");
        specialKeys.put("ENTER", "\ue006");
        specialKeys.put("BACK", "\ue000");
        specialKeys.put("FORW", "\ue001");
    }
    public final Map<String, AbstractButton> bLookup;
    protected Keyboard() {
        bLookup = new HashMap<String, AbstractButton>();
    }
    protected void addPanelToBox(JPanel inner) {
        Box box = Box.createVerticalBox();
        box.add(inner);
        add(box);
    }

    public void setTextField(JTextField tf) {
        // TODO
    }

    public void actionPerformed(ActionEvent event) {
        // TODO
    }

}
