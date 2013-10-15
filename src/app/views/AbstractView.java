package app.views;

import app.helpers.*;
import app.uitoolkit.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public abstract class AbstractView extends JPanel implements ActionListener, Observer {

	/**
	 * Constructs a new view.
	 * Sets the title and add itself to the multi-panel controller.
	 * 
	 * @param tp		reference to the shared title pane 
	 * @param name		the lookup name associated with the view
	 * @param text		the title text
	 */
	public AbstractView(String name, String text) {
		setLayout(new BorderLayout());
		setName(name);
		if (text != null) {
			TitlePane tp = new TitlePane();
			tp.setText(text);
			add(tp, BorderLayout.NORTH);
		}
	}

	@Override public void update(Observable o, Object arg) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		
		if (name == "EXIT") {
			// TODO
		} else {
			MultiPanel.SELF.show(name);
		}
	}
}
