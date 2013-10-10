package app.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Implements an alphanumeric keyboard.
 *
 *  [Q,     W, E, R, T, Y, U, I, O, P,     BKSP] [7, 8, 9]
 *  [A,     S, D, F, G, H, J, K, L, +,    ENTER] [4, 5, 6]
 *  [SHIFT, Z, X, C, V, B, N, M, ., -, !, SHIFT] [1, 2, 3]
 *  [CLRS,  @, SPACE,     , _, .COM, PREV, NEXT] [0,    .]
 */
class AlphaNumericKeyboard extends Keyboard {
    public AlphaNumericKeyboard() {
        AlphabeticKeyboard ak = new AlphabeticKeyboard();
        NumericKeyboard nk = new NumericKeyboard();
        JPanel inner = new JPanel();
            inner.add(ak);
            inner.add(nk);
        addPanelToBox(inner);
        for (String key : ak.bLookup.keySet()) {
            bLookup.put(key, ak.bLookup.get(key));
        }
        for (String key : nk.bLookup.keySet()) {
            AbstractButton ab = nk.bLookup.get(key);
            if (bLookup.containsKey(key)) {
                ab.setName(key + "[1]");
            }
            bLookup.put(key, ab);
        }
    }

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.add(new AlphaNumericKeyboard());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
