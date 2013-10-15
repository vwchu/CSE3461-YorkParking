package app.views;

import app.helpers.*;
import app.model.*;
import app.uitoolkit.*;
import app.uitoolkit.keyboards.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserPage extends AbstractView {

	private final JLabel USER_ID          = new JLabel();
	private final JTextField FIRST_NAME   = new JTextField();
	private final JTextField SURNAME      = new JTextField();

	public UserPage() {
		super("USER", "My Profile");
		AlphabeticKeyboard kb = new AlphabeticKeyboard(null, false, true);
		JPanel main = new JPanel(new BorderLayout());
			JPanel inner = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(2, 2, 20, 0));
					form.add(new InputField(USER_ID, "Student ID", 40, 16, 300, false));
					form.add(new JPanel()); // dummy
					form.add(new InputField(FIRST_NAME, "Given Name", 40, 16, 300, false));
					form.add(new InputField(SURNAME, "Surname", 40, 16, 300, false));
				inner.add(form);
				JPanel options = new JPanel();
					options.add(new SquareButton("CHANGE_PIN", "KEY", "Change PIN", this));
				inner.add(options);
			main.add(UIToolbox.box(new JPanel(new GridBagLayout()), inner), BorderLayout.CENTER);
			main.add(kb, BorderLayout.SOUTH);
		add(main, BorderLayout.CENTER);
		JPanel nav = new JPanel(new BorderLayout());
			JPanel navLeft = new JPanel();
				navLeft.add(new HorizontalButton("HOME", "HOME", "Home", this));
			nav.add(navLeft, BorderLayout.WEST);
			JPanel navCenter = new JPanel(new GridLayout(1, 1));
				navCenter.add(new HorizontalButton("SUBMIT", null, "Submit", this));
				navCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
			nav.add(navCenter, BorderLayout.CENTER);
			JPanel navRight = new JPanel();
				navRight.add(new HorizontalButton("EXIT", "EXIT", "Logout", this, true));
			nav.add(navRight, BorderLayout.EAST);
		add(nav, BorderLayout.SOUTH);
		//USER_ID.setText("123456789");
		UIToolbox.setSize(USER_ID, new Dimension(USER_ID.getWidth(), USER_ID.getHeight() - 20));
		// attach event listeners
		FIRST_NAME.addFocusListener(kb);
		SURNAME.addFocusListener(kb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		
		if (name == "SUBMIT") {
			// TODO
			MultiPanel.SELF.show("HOME");
		} else if (name == "CHANGE_PIN") {
			MultiPanel.SELF.show("CHANGE_PIN");
		} else {
			super.actionPerformed(e);
		}
	}

	@Override
	public void update(Observable o, Object arg)  {
		String opcode = (String)arg;
		if (o instanceof User) {
			User user = (User)o;
			if (opcode.equals("ATTACH")) {
				USER_ID.setText("" + user.getID());
				FIRST_NAME.setText(user.getFirstName());
				SURNAME.setText(user.getSurName());
			} else if (opcode.equals("NAME")) {
				FIRST_NAME.setText(user.getFirstName());
				SURNAME.setText(user.getSurName());
			} else if (opcode.equals("DETACH")) {
				USER_ID.setText("");
				FIRST_NAME.setText("");
				SURNAME.setText("");
			}
		}
	}
}
