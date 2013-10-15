package app.views;

import app.helpers.*;
import app.uitoolkit.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JPanel implements FocusListener, ActionListener {

	private final JTextField 	  USER_FIELD   = new JTextField();
	private final JPasswordField  PIN_FIELD    = new JPasswordField();
	private final NumericKeyboard KB           = new NumericKeyboard(null);

	public Login() {
		// construct the components
		setLayout(new BorderLayout());
		TitlePane tp = new TitlePane(); tp.setText("Login"); // TODO
		add(tp, BorderLayout.NORTH);
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel();
				inner.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(2, 1));
					form.add(new InputField(USER_FIELD, "Student ID", 60, 16, 500, false));
					form.add(new InputField(PIN_FIELD, "Pin", 60, 16, 500, false));
				inner.add(form);
				inner.add(KB);
		add(UIToolbox.box(main, inner), BorderLayout.CENTER);
		JPanel nav = new JPanel();
			nav.add(new HorizontalButton("ENTER", "CHECK", "Enter", this, UIToolbox.getScreenSize().width - 10));
		add(nav, BorderLayout.SOUTH);
		// attach event listeners
		USER_FIELD.addFocusListener(this);
		PIN_FIELD.addFocusListener(this);
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
        frame.add(new Login());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
