package app.views;

import app.helpers.*;
import app.model.User;
import app.uitoolkit.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class HomePage extends AbstractView {

	private final SquareButton PAY_NOW; // Reference to Pay Now button

	public HomePage() {
		super("HOME", "Home");
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel(new GridLayout(2, 3));
		        inner.add(new SquareButton("USER", "USER", "My Profile", this));
		        inner.add(new SquareButton("VEHICLES", "CAR", "My Vehicles", this));
		        inner.add(new SquareButton("SUBSCIPTION", "MAIL", "Subscription", this));
		        inner.add(new SquareButton("NEW_PERMIT", "NEW", "Get Permit", this));
		        inner.add(new SquareButton("HISTORY", "FILES", "History", this));
		        inner.add(PAY_NOW = new SquareButton("PAY_NOW", "DOLLAR", "Pay Now", this));
	    add(UIToolbox.box(main, inner), BorderLayout.CENTER);
		JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			nav.add(new HorizontalButton("EXIT", "EXIT", "Logout", this, true));
		add(nav, BorderLayout.SOUTH);
		PAY_NOW.setVisible(false);
	}

	@Override
	public void update(Observable o, Object arg)  {
		User user = (User)o;
		String opcode = (String)arg;

		if ((opcode.equals("ATTACH") || opcode.equals("FINES")) && user.hasFines()) {
			PAY_NOW.setVisible(true);
		} else if (opcode.equals("DETACH")) {
			PAY_NOW.setVisible(false);
		}
	}
}
