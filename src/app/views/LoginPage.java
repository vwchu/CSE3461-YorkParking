package app.views;

import app.Main;
import app.helpers.*;
import app.model.*;
import app.uitoolkit.*;
import app.uitoolkit.keyboards.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class provides a view that allows users
 * to log into the application.
 */
public class LoginPage extends AbstractView {

	private final JTextField USER;			// User student ID field
	private final JPasswordField PIN;		// Access PIN field
	private final InputField USER_FIELD;	// input field for user student ID
	private final InputField PIN_FIELD;		// input filed for user PIN

	// TODO: Setup timeout after idling for one minute and switch welcome screen.
	// TODO: Check input of fields to ensure input is only numbers
	//       and 9 characters long for user ID
	//       and 4 characters long for PIN.

	public LoginPage() {
		super("LOGIN", "Login");
		NumericKeyboard kb = new NumericKeyboard(null);
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel();
				inner.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(2, 1));
					form.add(USER_FIELD = new InputField(USER = new JTextField(), "Student ID", 60, 16, 500, false));
					form.add(PIN_FIELD = new InputField(PIN = new JPasswordField(), "Pin", 60, 16, 500, false));
				inner.add(form);
				inner.add(kb);
		add(UIToolbox.box(main, inner), BorderLayout.CENTER);
		JPanel nav = new JPanel();
			nav.add(new HorizontalButton("ENTER", "CHECK", "Enter", this, UIToolbox.getScreenSize().width - 10));
		add(nav, BorderLayout.SOUTH);
		// attach event listeners
		USER.addFocusListener(kb);
		PIN.addFocusListener(kb);
	}

	@Override
	public boolean prepareView(Object... args) {
		USER.setText(""); USER_FIELD.showError(false);
		PIN.setText(""); PIN_FIELD.showError(false);
		USER.requestFocusInWindow();
		return false; // Unless
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		if (name == "ENTER") {
			// TODO: Format check needed
			long id = Integer.parseInt(USER.getText());
			int pin = Integer.parseInt(new String(PIN.getPassword()));
			if ((Main.USER = DBManager.SELF.getUser(id, pin)) != null) {
				System.out.println("LOGGED IN AS: " + Main.USER.getID());
				MultiPanel.SELF.show("HOME");
			} else {
				System.out.println("LOGIN ATTEMPT FAILED");
				USER_FIELD.showError(true);
				PIN_FIELD.showError(true);
				PIN.setText("");
				if (!DBManager.SELF.userExists(id)) {
					USER.setText("");
					USER.requestFocusInWindow();
				} else {
					PIN.requestFocusInWindow();
				}
			}
		}
	}
}
