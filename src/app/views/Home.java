package app.views;

import app.helpers.*;
import app.uitoolkit.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Home extends JPanel implements ActionListener {
	
	public Home() {
		setLayout(new BorderLayout());
		TitlePane tp = new TitlePane(); tp.setText("Home"); // TODO
		add(tp, BorderLayout.NORTH);
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel(new GridLayout(2, 3));
		        inner.add(new SquareButton("USER", "USER", "My Profile", this));
		        inner.add(new SquareButton("VEHICLES", "CAR", "My Vehicles", this));
		        inner.add(new SquareButton("SUBSCIPTION", "MAIL", "Subscription", this));
		        inner.add(new SquareButton("NEW_PERMIT", "NEW", "Get Permit", this));
		        inner.add(new SquareButton("HISTORY", "FILES", "History", this));
		        inner.add(new SquareButton("PAY_NOW", "DOLLAR", "Pay Now", this));
	    add(UIToolbox.box(main, inner), BorderLayout.CENTER);
		JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			nav.add(new HorizontalButton("EXIT", "EXIT", "Logout", this, true));
		add(nav, BorderLayout.SOUTH);
	}

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
        frame.add(new Home());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
