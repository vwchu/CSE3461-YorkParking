package app.views;

import app.helpers.*;
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
	public void prepareView() {
		USER.setText(""); USER_FIELD.showError(false);
		PIN.setText(""); PIN_FIELD.showError(false);
		USER.requestFocusInWindow();
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		if (name == "ENTER") {
			//if (AppModel.initSession(USER.getText(), PIN.getPassword().toString())) {
				MultiPanel.SELF.show("HOME");
			//} else {
				USER_FIELD.showError(true);
				PIN_FIELD.showError(true);
			//}
		}
	}
}
