package app.views;

import app.helpers.*;
import app.uitoolkit.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ChangePIN extends JPanel implements FocusListener, ActionListener {

	private final JPasswordField  OLD_PIN      = new JPasswordField();
	private final JPasswordField  NEW_PIN      = new JPasswordField();
	private final JPasswordField  NEW_PIN2     = new JPasswordField();
	private final NumericKeyboard KB           = new NumericKeyboard(null);

	public ChangePIN() {
		// construct the components
		setLayout(new BorderLayout());
		TitlePane tp = new TitlePane(); tp.setText("Change PIN"); // TODO
		add(tp, BorderLayout.NORTH);
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel();
				inner.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(3, 1));
					form.add(new InputField(OLD_PIN,  "Old Pin", 40, 16, 500, false));
					form.add(new InputField(NEW_PIN,  "New Pin", 40, 16, 500, false));
					form.add(new InputField(NEW_PIN2, "Retype New Pin", 40, 16, 500, false));
				inner.add(form);
				inner.add(KB);
		add(UIToolbox.box(main, inner), BorderLayout.CENTER);
		JPanel nav = new JPanel(new BorderLayout());
			JPanel navLeft = new JPanel();
				navLeft.add(new HorizontalButton("HOME", "HOME", "Home", this));
				navLeft.add(new HorizontalButton("BACK", "BACK", "Back", this));
			nav.add(navLeft, BorderLayout.WEST);
			JPanel navCenter = new JPanel(new GridLayout(1, 1));
				navCenter.add(new HorizontalButton("SUBMIT", null, "Submit", this));
				navCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
			nav.add(navCenter, BorderLayout.CENTER);
			JPanel navRight = new JPanel();
				navRight.add(new HorizontalButton("EXIT", "EXIT", "Logout", this, true));
			nav.add(navRight, BorderLayout.EAST);
		add(nav, BorderLayout.SOUTH);
		// attach event listeners
		OLD_PIN.addFocusListener(this);
		NEW_PIN.addFocusListener(this);
		NEW_PIN2.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		KB.setInputComponent((JTextField)e.getSource());
	}

	@Override
	public void focusLost(FocusEvent fe) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((JComponent)e.getSource()).getName());
		// TODO
	}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
    	UITheme.setLookAndFeel();
        JFrame frame = new JFrame();
        UIToolbox.fullscreen(frame);
        frame.add(new ChangePIN());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
