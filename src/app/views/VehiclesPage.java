package app.views;

import app.helpers.*;
import app.model.*;
import app.uitoolkit.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class VehiclesPage extends AbstractView {

	private final JLabel MAKE, MODEL, MODEL_YEAR, LICENSE_PLATE, POLICY, EXPIRY_DATE, INSURER;
	private final JButton DELETE, EDIT;
	private final JList<Vehicle> CAR_LIST;
	
	public VehiclesPage() {
		super("VEHICLES", "My Vehicles");
		JPanel main = new JPanel(new BorderLayout());
			JPanel inner = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
				CAR_LIST = new JList<Vehicle>();
					CAR_LIST.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					CAR_LIST.setLayoutOrientation(JList.VERTICAL_WRAP);
					CAR_LIST.setVisibleRowCount(-1);
					CAR_LIST.setBorder(new LineBorder(Color.LIGHT_GRAY));
					new JScrollPane(CAR_LIST);
					UIToolbox.setSize(CAR_LIST, new Dimension(300, 535));
				inner.add(CAR_LIST);
				JPanel detailsPane = new JPanel(new BorderLayout());
					detailsPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
					final int PADDING = 10;
					final int WIDTH = 375;
					JPanel details = new JPanel(new GridLayout(4, 2));
						details.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
						details.add(new InputField(MAKE = new JLabel(), "Make", 40, 16, WIDTH, false));
						details.add(new InputField(MODEL = new JLabel(), "Model", 40, 16, WIDTH, false));
						details.add(new InputField(LICENSE_PLATE = new JLabel(), "License Plate", 40, 16, WIDTH, false));
						details.add(new InputField(MODEL_YEAR = new JLabel(), "Model Year", 40, 16, WIDTH, false));
						details.add(new InputField(INSURER = new JLabel(), "Insurer", 40, 16, WIDTH, false));
						details.add(new InputField(POLICY = new JLabel(), "Policy", 40, 16, WIDTH, false));
						details.add(new InputField(EXPIRY_DATE = new JLabel(), "Expiry Date", 40, 16, WIDTH, false));
					detailsPane.add(details, BorderLayout.CENTER);
					JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						controls.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
						controls.add(DELETE = new HorizontalButton("DELETE", "DELETE", "Delete", this, true));
						controls.add(EDIT = new HorizontalButton("EDIT", "EDIT", "Edit", this, true));
					detailsPane.add(controls, BorderLayout.SOUTH);
				inner.add(detailsPane);
			main.add(UIToolbox.box(new JPanel(new GridBagLayout()), inner), BorderLayout.CENTER);
		add(main, BorderLayout.CENTER);
		JPanel nav = new JPanel(new BorderLayout());
			JPanel navLeft = new JPanel();
				navLeft.add(new HorizontalButton("HOME", "HOME", "Home", this));
			nav.add(navLeft, BorderLayout.WEST);
			JPanel navCenter = new JPanel(new GridLayout(1, 1));
			nav.add(navCenter, BorderLayout.CENTER);
			JPanel navRight = new JPanel();
				navRight.add(new HorizontalButton("EXIT", "EXIT", "Logout", this, true));
			nav.add(navRight, BorderLayout.EAST);
		add(nav, BorderLayout.SOUTH);
	}

	@Override
	public void prepareView(Object... args) {
		super.prepareView(args);
		// TODO: fill-in the CAR_LIST by querying database
		// TODO: select first item of the CAR_LIST
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//JButton button = (JButton)e.getSource();
		//String name = button.getName();
		// TODO: fill-in the details panes
	}
}
