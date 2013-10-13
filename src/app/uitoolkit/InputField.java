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

	public InputField(Component input, String text, int isize, int lsize, int width, boolean labelOnTop) {
		int ih, lh;
		// default -1 values
		if (lsize < 0) {lsize = LABEL_SIZE;}
		if (isize < 0) {isize = INPUT_SIZE;}
		if (width < 0) {width = WIDTH;} 
		// store references and construct components
		this.INPUT = input;
		this.LABEL = text;
		this.INNER = new JPanel();
			INNER.setLayout(new BorderLayout());
			JLabel label = new JLabel(text.toUpperCase());
				label.setHorizontalAlignment(SwingConstants.LEFT);
				label.setFont(MyFont.SEMIBOLD_FONT.deriveFont((float)lsize));
				lh = label.getFontMetrics(input.getFont()).getHeight();
			INNER.add(label, labelOnTop ? BorderLayout.NORTH : BorderLayout.SOUTH);
				input.setFont(MyFont.REGULAR_FONT.deriveFont((float)isize));
				ih = input.getFontMetrics(input.getFont()).getHeight() + 20;
				UITools.setSize(input, new Dimension(WIDTH + 20, ih));
			INNER.add(input, BorderLayout.CENTER);
		UITools.setSize(this, new Dimension(WIDTH + 20, ih + lh + 10));
		UITools.box(this, INNER);
	}
	public InputField(Component input, String text, boolean labelOnTop) {
		this(input, text, -1, -1, -1, labelOnTop);}
	public InputField(Component input, String text, int width) {
		this(input, text, -1, -1, width, true);}
	public InputField(Component input, String text) {
		this(input, text, WIDTH);}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
    	UITheme.setLookAndFeel();
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        {JPanel inner = new JPanel();
	        	inner.setLayout(new BoxLayout(inner, BoxLayout.PAGE_AXIS));
	        	inner.add(new InputField(new JTextField(), "Student ID:"));
	        	inner.add(new InputField(new JPasswordField(), "PIN:"));
	        frame.add(UITools.box(new JPanel(), inner));}
        {JPanel inner = new JPanel();
	        	inner.setLayout(new BoxLayout(inner, BoxLayout.PAGE_AXIS));
	        	inner.add(new InputField(new JComboBox<String>((new java.text.DateFormatSymbols()).getMonths()), "Months:"));
	        	inner.add(new InputField(new JSpinner(), "Year:"));
	        frame.add(UITools.box(new JPanel(), inner));}
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
