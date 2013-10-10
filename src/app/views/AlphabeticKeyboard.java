package app.views;

import app.helpers.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Implements an alphabetic keyboard.
 *
 *  [Q,     W, E, R, T, Y, U, I, O, P,     BKSP]
 *  [A,     S, D, F, G, H, J, K, L, +,    ENTER]
 *  [SHIFT, Z, X, C, V, B, N, M, ., -, !, SHIFT]
 *  [CLRS,  @, SPACE,     , _, .COM, PREV, NEXT]
 */
class AlphabeticKeyboard extends Keyboard {
    private static final String[][] keys = {
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "BKSP"},
        {"A", "S", "D", "F", "G", "H", "J", "K", "L", "+", "ENTER"},
        {"SHIFT", "Z", "X", "C", "V", "B", "N", "M", ".", "-", "!", "SHIFT"},
        {"CLEAR", "@", "SPACE", "_", ".COM", "BACK", "FORW"}
    };
    private static final int KEYBOARD_WIDTH = (KEY_WIDTH + KEY_SPACING) * keys[0].length + KEY_SPACING;
    public AlphabeticKeyboard() {
        JPanel inner = new JPanel();
            inner.setLayout(new GridLayout(4, 1));
            for (String[] row : keys) {
                JPanel rp = new JPanel();
                for (String key : row) {
                    AbstractButton ab;
                    if (key == "SHIFT") {
                        ab = new JToggleButton();
                    } else {
                        ab = new JButton();
                    }
                    Font font = MyFont.BODYTEXT_FONT;
                    if (specialKeys.containsKey(key)) {
                        font = MyFont.ICON_FONT;
                        ab.setText(specialKeys.get(key));
                    } else {
                        ab.setText(key.toLowerCase());
                    }
                    ab.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
                    ab.setFocusable(false);
                    if (key == "SPACE") {
                        ab.setPreferredSize(new Dimension(
                            KEYBOARD_WIDTH - 7 * (KEY_WIDTH + KEY_SPACING) - KEY_SPACING * 2,
                            KEY_HEIGHT));
                    } else if (key == "BKSP" || key == "ENTER" || key == ".COM" || key == "CLEAR") {
                        ab.setPreferredSize(new Dimension(
                            KEY_WIDTH * 2 + KEY_SPACING,
                            KEY_HEIGHT));
                    } else {
                        ab.setPreferredSize(new Dimension(
                            KEY_WIDTH,
                            KEY_HEIGHT));
                    }
                    key += bLookup.containsKey(key) ? "[1]" : "";
                    bLookup.put(key, ab);
                    ab.setName(key);
                    ab.setBackground(Color.WHITE);
                    ab.setForeground(Color.BLACK);
                    rp.add(ab);
                }
                inner.add(rp);
            }
        addPanelToBox(inner);
    }

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.add(new AlphabeticKeyboard());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
