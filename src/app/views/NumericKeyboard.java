package app.views;

import app.helpers.MyFont;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Implements a numeric keyboard.
 *
 *  [7, 8, 9]
 *  [4, 5, 6]
 *  [1, 2, 3]
 *  [0,    .]
 */
class NumericKeyboard extends Keyboard {
    private static final String[][] keys = {
        {"7", "8", "9"},
        {"4", "5", "6"},
        {"1", "2", "3"},
        {"0"     , "."}
    };
    public NumericKeyboard() {
        JPanel inner = new JPanel();
            inner.setLayout(new GridLayout(4, 1));
            for (String[] row : keys) {
                JPanel rp = new JPanel();
                for (String key : row) {
                    AbstractButton ab = new JButton();
                    Font font = MyFont.BODYTEXT_FONT;
                    ab.setText(key);
                    ab.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
                    ab.setFocusable(false);
                    if (key == "0") {
                        ab.setPreferredSize(new Dimension(
                            KEY_WIDTH * 2 + KEY_SPACING,
                            KEY_HEIGHT));
                    } else {
                        ab.setPreferredSize(new Dimension(
                            KEY_WIDTH,
                            KEY_HEIGHT));
                    }
                    key += bLookup.containsKey(key) ? "[1]" : "";
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
        frame.add(new NumericKeyboard());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
