package app.views;

import java.awt.event.*;
import javax.swing.*;
import app.helpers.*;

public class WelcomePage extends AbstractView {

	public WelcomePage() {
		super("WELCOME", null);
		JButton start = new JButton();
			start.setText("Tap to start"); // TODO
			start.setOpaque(false);
			start.setContentAreaFilled(false);
			start.addActionListener(this);
		add(start);
	}

	@Override
	public boolean prepareView(Object... args) {
		return false; // Do nothing
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MultiPanel.SELF.show("LOGIN");
	}
}
