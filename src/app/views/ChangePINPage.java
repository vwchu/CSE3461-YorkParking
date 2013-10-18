package app.views;

import app.helpers.*;
import app.uitoolkit.*;
import app.uitoolkit.keyboards.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * This class provides a view that allowing users to 
 * change their access PIN.
 */
public class ChangePINPage extends AbstractView {

	private final JPasswordField  OLD_PIN      = new JPasswordField(4); // Field for old PIN
	private final JPasswordField  NEW_PIN      = new JPasswordField(4); // Field for new PIN
	private final JPasswordField  NEW_PIN2     = new JPasswordField(4); // Field for confirming new PIN

	private final InputField OLD_PIN_FIELD;		// Input Field for old PIN
	private final InputField NEW_PIN_FIELD;		// Input Field for new PIN
	private final InputField NEW_PIN2_FIELD;	// Input Field for confirming new PIN

	public ChangePINPage() {
		super("CHANGE_PIN", "Change PIN");
		NumericKeyboard kb = new NumericKeyboard(null);
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel();
				inner.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(3, 1));
					form.add(OLD_PIN_FIELD = new InputField(OLD_PIN,  "Old Pin", 40, 16, 500, false));
					form.add(NEW_PIN_FIELD = new InputField(NEW_PIN,  "New Pin", 40, 16, 500, false));
					form.add(NEW_PIN2_FIELD = new InputField(NEW_PIN2, "Retype New Pin", 40, 16, 500, false));
				inner.add(form);
				inner.add(kb);
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
		OLD_PIN.addFocusListener(kb);
		NEW_PIN.addFocusListener(kb);
		NEW_PIN2.addFocusListener(kb);
	}

	@Override
	public void prepareView(Object... args) {
		super.prepareView(args);
		OLD_PIN.setText(""); OLD_PIN_FIELD.showError(false);
		NEW_PIN.setText(""); NEW_PIN_FIELD.showError(false);
		NEW_PIN2.setText(""); NEW_PIN2_FIELD.showError(false);
		OLD_PIN.requestFocusInWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		
		if (name == "BACK" || name == "SUBMIT") {
			if (name == "SUBMIT") {
				USER_OBJ.changePIN(
					Integer.parseInt(OLD_PIN.getPassword().toString()),
					Integer.parseInt(NEW_PIN.getPassword().toString()),
					Integer.parseInt(NEW_PIN2.getPassword().toString()));
			}
			MultiPanel.SELF.show("USER");
		} else {
			super.actionPerformed(e);
		}
	}
}
