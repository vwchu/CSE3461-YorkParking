package app.uitoolkit;

import app.helpers.*;
import java.awt.*;
import javax.swing.*;

/**
 * Base definition of the an input field. 
 * Untyped; does not specify the input field type.
 */
public class InputField extends JPanel {

	private static final int LABEL_SIZE = 14;	// label font size
	private static final int INPUT_SIZE = 24;	// input field font size
	private static final int WIDTH 		= 300;	// total width of this component
	
	public final Component INPUT;	// reference to the input field
	public final String LABEL;		// the label string
	public final JPanel INNER;		// reference to the inner panel

	public InputField(JComponent input, String text, int isize, int lsize, int width, boolean labelOnTop) {
		int ih, lh;
		// default values
		if (lsize <= 0) {lsize = LABEL_SIZE;}
		if (isize <= 0) {isize = INPUT_SIZE;}
		if (width <= 0) {width = WIDTH;} 
		// store references and construct components
		this.INPUT = input;
		this.LABEL = text;
		this.INNER = new JPanel();
			INNER.setLayout(new BorderLayout());
			JLabel label = new JLabel(text.toUpperCase());
				label.setHorizontalAlignment(SwingConstants.LEFT);
				label.setFont(MyFont.SEMIBOLD_FONT.deriveFont((float)lsize));
				lh = label.getFontMetrics(label.getFont()).getHeight();
			INNER.add(label, labelOnTop ? BorderLayout.NORTH : BorderLayout.SOUTH);
				input.setFont(MyFont.REGULAR_FONT.deriveFont((float)isize));
				ih = input.getFontMetrics(input.getFont()).getHeight() + 20;
				UIToolbox.setSize(input, new Dimension(width + 20, ih));
			INNER.add(input, BorderLayout.CENTER);
		UIToolbox.setSize(this, new Dimension(width + 20, ih + lh + 10));
		UIToolbox.box(this, INNER);
	}
	public InputField(JComponent input, String text, boolean labelOnTop) {
		this(input, text, -1, -1, -1, labelOnTop);}
	public InputField(JComponent input, String text, int width) {
		this(input, text, -1, -1, width, true);}
	public InputField(JComponent input, String text) {
		this(input, text, WIDTH);}

	/**
	 * Displays an icon beside the input field.
	 * The icon can indicate an error, warning, information,
	 * notification, help tip, etc.
	 * 
	 * @param icon		the icon to display.
	 * @param isIcon	flag for whether the icon string is an icon or normal text. (default: true)
	 * @param rightIcon	flag for displaying the icon on the right, if false, display on the left. (default: true)
	 */
	public void appendIcon(String icon, boolean isIcon, boolean rightIcon) {
		JLabel errIcon = new JLabel();
			errIcon.setFont((isIcon ? MyFont.ICON_FONT : MyFont.REGULAR_FONT).deriveFont((float)INPUT.getFont().getSize()));
			errIcon.setText(isIcon && MyFont.ICONS.containsKey(icon) ? MyFont.ICONS.get(icon) : icon);
			errIcon.setHorizontalAlignment(SwingConstants.CENTER);
		int width = errIcon.getFontMetrics(errIcon.getFont()).stringWidth(errIcon.getText()) + 20;
		UIToolbox.setSize(INPUT, new Dimension(INPUT.getWidth() - width, INPUT.getHeight()));
		UIToolbox.setSize(errIcon, new Dimension(width, INPUT.getHeight()));
		INNER.add(errIcon, rightIcon ? BorderLayout.EAST : BorderLayout.WEST);
	}
	public void appendIcon(String icon) {
		appendIcon(icon, true, true);}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
    	UITheme.setLookAndFeel();
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JPanel inner = new JPanel();
	        	inner.setLayout(new BoxLayout(inner, BoxLayout.PAGE_AXIS));
	        	inner.add(new InputField(new JTextField(), "Student ID:"));
	        	InputField inf = new InputField(new JPasswordField(), "PIN:");
	        		inf.appendIcon("ERROR");
	        	inner.add(inf);
	        	inner.setLayout(new BoxLayout(inner, BoxLayout.PAGE_AXIS));
	        	inner.add(new InputField(new JComboBox<String>((new java.text.DateFormatSymbols()).getMonths()), "Months:"));
	        	inner.add(new InputField(new JSpinner(), "Year:"));
	        	InputField cash = new InputField(new JFormattedTextField(), "Amount:");
	        		cash.appendIcon("$", false, false);
	        	inner.add(cash);
	    frame.add(UIToolbox.box(new JPanel(), inner));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
