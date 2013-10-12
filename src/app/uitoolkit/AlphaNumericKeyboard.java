package app.uitoolkit;

import app.helpers.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Implements an alphanumeric keyboard.
 *
 *  [Q,     W, E, R, T, Y, U, I, O, P,     BKSP] [7, 8, 9]
 *  [CAPS,  A, S, D, F, G, H, J, K, L,    ENTER] [4, 5, 6]
 *  [SHIFT, Z, X, C, V, B, N, M, ., -, +, SHIFT] [1, 2, 3]
 *  [CLRS,  @, SPACE,     , _, .COM, PREV, NEXT] [0,    .]
 */
class AlphaNumericKeyboard extends Keyboard implements ActionListener {

	private final AlphabeticKeyboard AK;	// reference to alphabetic keyboard
	private final NumericKeyboard NK;		// reference to numeric keyboard

	/**
	 * Create and layout the alphabetic (QWERTY) keyboard with
	 * a numeric (number pad) keyboard.
	 * 
	 * @param field text input
	 */
	public AlphaNumericKeyboard(JTextField field) {
		super(field);
        AK = new AlphabeticKeyboard(field);
        NK = new NumericKeyboard(field, false, true);
        JPanel inner = new JPanel();
            inner.add(AK);
            inner.add(NK);
        addPanelToBox(inner);
        for (String key : AK.BLOOKUP.keySet()) {
        	AbstractButton ab = AK.BLOOKUP.get(key);
            BLOOKUP.put(key, ab);
            ab.removeActionListener(AK);
            ab.addActionListener(this);
        }
        for (String key : NK.BLOOKUP.keySet()) {
            AbstractButton ab = NK.BLOOKUP.get(key);
            if (BLOOKUP.containsKey(key)) {
                ab.setName(key + "[1]");
            }
            BLOOKUP.put(key, ab);
            ab.removeActionListener(NK);
            ab.addActionListener(this);
        }
    } // AlphaNumericKeyboard

    /**
     * Associates the text input with the keyboard.
     *  
     * @param field 	text input to link to the keyboard.
     */
	@Override
    public void setInputComponent(JTextField field) {
        // Check to protect against null pointer exception
		// when super constructor calls method via polymorphism before
		// AK and NK initialized in constructor
		if (AK != null && NK != null) {
        	AK.setInputComponent(field);
        	NK.setInputComponent(field);
        }
    }

    /**
     * Invoked when an action occurs.
     * Use the alphabetic keyboard's.
     * 
     * @param event		the event object.
     */
	@Override
	public void actionPerformed(ActionEvent event) {
		AK.actionPerformed(event);
	}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setLayout(new java.awt.GridLayout(2, 1));
        JTextField tf = new JTextField();
        tf.setFont(MyFont.HEADER_FONT.deriveFont(Font.PLAIN, 36));
        frame.add(tf);
        frame.add(new AlphaNumericKeyboard(tf));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
