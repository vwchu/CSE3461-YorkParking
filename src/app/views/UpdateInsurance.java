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
import javax.swing.event.*;

/**
 * This class provides a view that allowing users to 
 * update their vehicle's insurance policy information.
 */
public class UpdateInsurance extends AbstractView {

	private final JComboBox<String> INSURER = new JComboBox<String>(); 
	private final JTextField POLICY   		= new JTextField();
	private final JComboBox<String> MONTH   = new JComboBox<String>();
	private final JSpinner YEAR 			= new JSpinner();
	private Vehicle VEHICLE;
	private HorizontalButton SUBMIT;

	public UpdateInsurance() {
		super("UPDATE_INSURANCE", "Update Insurance");
		NumericKeyboard kb = new NumericKeyboard(null);
		JPanel main = new JPanel();
			main.setLayout(new GridBagLayout());
			JPanel inner = new JPanel();
				inner.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
				JPanel form = new JPanel(new GridLayout(3, 1));
					form.add(new InputField(INSURER,  "Insurer", 40, 16, 500, false));
					form.add(new InputField(POLICY,  "Policy No.", 40, 16, 500, false));
					JPanel expiryDate = new JPanel(new GridLayout(1, 2));
						expiryDate.add(YEAR);
						expiryDate.add(MONTH);
						YEAR.setFont(MyFont.REGULAR_FONT.deriveFont((float)40));
						YEAR.addChangeListener(new ChangeListener() {
							@Override
							public void stateChanged(ChangeEvent e) {
								int year = Integer.parseInt((String)YEAR.getValue());
								MONTH.removeAllItems();
								UIToolbox.fillMonthComboBox(MONTH, year == Calendar.getInstance().get(Calendar.YEAR));
								MONTH.setSelectedIndex(0);
							}
						});
						MONTH.setFont(MyFont.REGULAR_FONT.deriveFont((float)40));
						MONTH.setEditable(false);
					form.add(new InputField(expiryDate, "Expiry Date (Year, Month)", 40, 16, 500, false));
				inner.add(form);
				inner.add(kb);
		add(UIToolbox.box(main, inner), BorderLayout.CENTER);
		JPanel nav = new JPanel(new BorderLayout());
			JPanel navLeft = new JPanel();
				navLeft.add(new HorizontalButton("HOME", "HOME", "Home", this));
				navLeft.add(new HorizontalButton("BACK", "BACK", "Back", this));
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
		POLICY.addFocusListener(kb);		
	}

	@Override
	public boolean prepareView(Object... args) {
		if (!super.prepareView(args)) {return false;}
		// clear
		INSURER.removeAllItems();
		POLICY.setText(null);
		MONTH.removeAllItems();
		SUBMIT.setForeground(null);
		SUBMIT.setBorder((new JButton()).getBorder());
		// fill-in fields
		for (String insurer : DBManager.SELF.getInsurers()) {INSURER.addItem(insurer);}
		if (args.length >= 1) {
			VEHICLE = (Vehicle)args[0];
			INSURER.setSelectedItem(VEHICLE.getInsurer());
			POLICY.setText(VEHICLE.getPolicy());
			UIToolbox.fillYearSpinner(YEAR, Calendar.getInstance().get(Calendar.YEAR), 100);
			if (VEHICLE.getExpiry().after(new java.util.Date())) {
				Calendar date = Calendar.getInstance();
				date.setTime(VEHICLE.getExpiry());
				YEAR.setValue("" + date.get(Calendar.YEAR));
				if (date.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
					UIToolbox.fillMonthComboBox(MONTH, date.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
					MONTH.setSelectedIndex(date.get(Calendar.MONTH) - Calendar.getInstance().get(Calendar.MONTH));
				} else {
					MONTH.setSelectedIndex(date.get(Calendar.MONTH));
				}
			} else {
				UIToolbox.fillMonthComboBox(MONTH, true);
				MONTH.setSelectedIndex(0);
			}
		}
		INSURER.requestFocusInWindow();
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String name = button.getName();
		if (name == "BACK") {
			MultiPanel.SELF.show("EDIT_VEHICLE", VEHICLE);
		} else if (name == "SUBMIT") {
			Calendar date = Calendar.getInstance();
				int year = Integer.parseInt((String)YEAR.getValue());
				date.set(Calendar.YEAR, year);
				if (year == Calendar.getInstance().get(Calendar.YEAR)) {
					date.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) + MONTH.getSelectedIndex());
				} else {
					date.set(Calendar.MONTH, MONTH.getSelectedIndex());
				}	
				date.set(Calendar.DATE, date.getActualMaximum(Calendar.DATE));
				date.set(Calendar.HOUR, date.getActualMaximum(Calendar.HOUR));
				date.set(Calendar.MINUTE, date.getActualMaximum(Calendar.MINUTE));
				date.set(Calendar.SECOND, date.getActualMaximum(Calendar.SECOND));
				date.set(Calendar.MILLISECOND, 0);
				date.set(Calendar.ZONE_OFFSET, 0);
			System.out.println(UIToolbox.convertToSQLDate(date));
			if (VEHICLE.updateInsurance((String)INSURER.getSelectedItem(),
					POLICY.getText(), UIToolbox.convertToSQLDate(date))) {
				MultiPanel.SELF.show("EDIT_VEHICLE", VEHICLE);
			} else {
				SUBMIT.setForeground(Color.RED);
				SUBMIT.setBorder(new LineBorder(Color.RED));
			}
		} else {
			super.actionPerformed(e);
		} 
	}
}
