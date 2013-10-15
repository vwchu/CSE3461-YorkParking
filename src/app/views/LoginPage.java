package app.views;

import app.helpers.*;
import app.uitoolkit.*;
import app.uitoolkit.keyboards.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginPage extends AbstractView {

	private final JTextField 	  USER_FIELD   = new JTextField();
	private final JPasswordField  PIN_FIELD    = new JPasswordField();

	public LoginPage() {
		super("LOGIN", "Login");
		NumericKeyboard kb = new NumericKeyboard(null);
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel();
				inner.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(2, 1));
					form.add(new InputField(USER_FIELD, "Student ID", 60, 16, 500, false));
					form.add(new InputField(PIN_FIELD, "Pin", 60, 16, 500, false));
				inner.add(form);
				inner.add(kb);
		add(UIToolbox.box(main, inner), BorderLayout.CENTER);
		JPanel nav = new JPanel();
			nav.add(new HorizontalButton("ENTER", "CHECK", "Enter", this, UIToolbox.getScreenSize().width - 10));
		add(nav, BorderLayout.SOUTH);
		// attach event listeners
		USER_FIELD.addFocusListener(kb);
		PIN_FIELD.addFocusListener(kb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		
		if (name == "ENTER") {
			// TODO
			MultiPanel.SELF.show("HOME");
		}
	}

	public static void main(String[] arg) {
    	UITheme.setLookAndFeel();
    	JFrame frame = new JFrame();
    	UIToolbox.fullscreen(frame);
    	frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLayout(MultiPanel.SELF.setParent(frame));
    		MultiPanel.SELF.add(new LoginPage());
    		MultiPanel.SELF.add(new HomePage());
    		MultiPanel.SELF.add(new UserPage());
    		MultiPanel.SELF.add(new ChangePINPage());
        frame.pack();
        frame.setVisible(true);
	}
}
