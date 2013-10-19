package app.views;

import app.Main;
import app.helpers.*;
import app.model.*;
import app.uitoolkit.*;
import app.uitoolkit.keyboards.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SubscriptionPage extends AbstractView {

	private final JTextField EMAIL = new JTextField(); 	// User's email address
	private final SquareButton UNSUBSCRIBE;				// Unsubscribe from email
	private final InputField EMAIL_FIELD;               // Input field for user's email address
	private final HorizontalButton SUBMIT;				// the submit button

	public SubscriptionPage() {
		super("SUBSCRIPTION", "Subscription");
		AlphaNumericKeyboard kb = new AlphaNumericKeyboard(null);
		JPanel main = new JPanel(new BorderLayout());
			JPanel inner = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(1, 1));
					form.add(EMAIL_FIELD = new InputField(EMAIL, "Email", 40, 16, 500, false));
				inner.add(form);
				JPanel options = new JPanel();
					options.add(UNSUBSCRIBE = new SquareButton("UNSUBSCRIBE", "MAIL", "Unsubscribe", this));
					UNSUBSCRIBE.setVisible(false);
				inner.add(options);
			main.add(UIToolbox.box(new JPanel(new GridBagLayout()), inner), BorderLayout.CENTER);
			main.add(kb, BorderLayout.SOUTH);
		add(main, BorderLayout.CENTER);
		JPanel nav = new JPanel(new BorderLayout());
			JPanel navLeft = new JPanel();
				navLeft.add(new HorizontalButton("HOME", "HOME", "Home", this));
			nav.add(navLeft, BorderLayout.WEST);
			JPanel navCenter = new JPanel(new GridLayout(1, 1));
				navCenter.add(SUBMIT = new HorizontalButton("SUBMIT", null, "Submit", this));
				navCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
			nav.add(navCenter, BorderLayout.CENTER);
			JPanel navRight = new JPanel();
				navRight.add(new HorizontalButton("EXIT", "EXIT", "Logout", this, true));
			nav.add(navRight, BorderLayout.EAST);
		add(nav, BorderLayout.SOUTH);
		// attach event listeners
		EMAIL.addFocusListener(kb);		
	}

	@Override
	public boolean prepareView(Object... args) {
		if (!super.prepareView(args)) {return false;}
		EMAIL_FIELD.showError(false);
		UNSUBSCRIBE.setVisible(Main.USER.hasEmail());
		if (Main.USER.hasEmail()) {
			EMAIL.setText(Main.USER.getEmail());
			SUBMIT.setText(SUBMIT.getText().replace("Subscribe", "Submit"));
		} else {
			EMAIL.setText(null);
			SUBMIT.setText(SUBMIT.getText().replace("Submit", "Subscribe"));
		}
		EMAIL.requestFocusInWindow();
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		if (name == "SUBMIT") {
			String email = EMAIL.getText();
			if (!EmailFormatValidator.validate(email)) {
				EMAIL_FIELD.showError(true);
			} else {
				Main.USER.setEmail(email.isEmpty() ? null : email);
				MultiPanel.SELF.show("HOME");
			}
		} else if (name == "UNSUBSCRIBE") {
			Main.USER.setEmail(null);
			MultiPanel.SELF.show("HOME");
		} else {
			super.actionPerformed(e);
		}
	}

}
