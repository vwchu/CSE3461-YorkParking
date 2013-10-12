package app.views;

import app.helpers.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * This class implements a large square button (tile)
 * consisting of a large icon and a label text. 
 */
public class BoxButton extends JPanel implements MouseListener {
	private static final int SIZE = 200;										// Button size (width and height)
	private static final int ICON_SIZE = 100;									// Icon font size
	private static final int LABEL_SIZE = 16;									// Label font size
	
	private static final Color FG_COLOR             = Color.BLACK;				// Text color
	private static final Color BG_COLOR             = Color.WHITE;				// Background color
	private static final Color BORDER_COLOR         = Color.LIGHT_GRAY;			// Border color

	private static final Color HOVER_FG_COLOR       = FG_COLOR;					// Text color (hovered)
	private static final Color HOVER_BG_COLOR       = BG_COLOR;					// Background color (hovered)
	private static final Color HOVER_BORDER_COLOR   = new Color(184, 207, 229);	// Border color (hovered)

	private static final Color PRESSED_FG_COLOR     = FG_COLOR;					// Text color (clicked)
	private static final Color PRESSED_BG_COLOR     = new Color(184, 207, 229);	// Background color (clicked)
	private static final Color PRESSED_BORDER_COLOR = new Color(148, 182, 216);	// Border color (clicked)

	protected final JPanel INNER;												// Inner (content) panel

	/**
	 * Construct a new button.
	 * 
	 * @param name		of the button. Used to reference in it.
	 * @param icon		to show in the center of the button. (large)
	 * @param label		to show in the bottom-left of the button
	 * @param listener	to perform an action when clicked.
	 */
	public BoxButton(String name, String icon, String label, ActionListener listener) {
		this.INNER = new JPanel();
			INNER.setLayout(new BorderLayout());
				JButton ib = new JButton();
					ib.setFont(MyFont.ICON_FONT.deriveFont(Font.PLAIN, ICON_SIZE));
					ib.setName(name);
					ib.setText(MyFont.ICONS.get(icon));
					ib.setToolTipText(label);
					ib.addMouseListener(this);
					ib.addActionListener(listener);
					makeTransparentButton(ib);
				INNER.add(ib, BorderLayout.CENTER);
				JButton lb = new JButton();
					lb.setFont(MyFont.BODYTEXT_FONT.deriveFont(Font.PLAIN, LABEL_SIZE));
					lb.setName(name);
					lb.setText(label);
					lb.setHorizontalAlignment(SwingConstants.LEFT);
					lb.setToolTipText(label);
					lb.addMouseListener(this);
					lb.addActionListener(listener);
					makeTransparentButton(lb);					
				INNER.add(lb, BorderLayout.SOUTH);
			setPanelSize(INNER, new Dimension(SIZE, SIZE));
			INNER.setOpaque(true);
		addPanelToBox(INNER);
		styleButton(BG_COLOR, FG_COLOR, BORDER_COLOR, 1);
	}

	/**
	 * Make the real button transparent and borderless
	 * to the outer box look like one button. 
	 * 
	 * @param b		button to style
	 */
	protected void makeTransparentButton(JButton b) {
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setFocusable(false);
	}

	/**
	 * Set the size of the given of the component.
	 * 
	 * @param comp	component to set the size of
	 * @param size	width and height of the panel 
	 */
	protected void setPanelSize(Component comp, Dimension size) {
		comp.setPreferredSize(size);
		comp.setMinimumSize(size);
		comp.setMaximumSize(size);
		comp.setSize(size);
	}

	/**
	 * Add the inner to the outer box.
	 * 
	 * @param inner	panel to add to the outer. 
	 */
    protected void addPanelToBox(JPanel inner) {
    	Box box = Box.createVerticalBox();
        box.add(inner);
        add(box);
    }	

    /**
     * Style the button with a look and feel.
     * 
     * @param bg				the background color
     * @param fg				the foreground color
     * @param border			the border color
     * @param borderThickness	the thickness of the border in pixels
     */
    protected void styleButton(Color bg, Color fg, Color border, int borderThickness) {
    	INNER.setBorder(new LineBorder(border, borderThickness));
		INNER.setBackground(bg);
		for (int i = 0 ; i < INNER.getComponentCount(); i++) {
			INNER.getComponent(i).setForeground(fg);
		}
    }

    // Mouse Listener Methods

	@Override public void mouseClicked(MouseEvent e)  {styleButton(PRESSED_BG_COLOR, PRESSED_FG_COLOR, PRESSED_BORDER_COLOR, 3);}
	@Override public void mousePressed(MouseEvent e)  {styleButton(PRESSED_BG_COLOR, PRESSED_FG_COLOR, PRESSED_BORDER_COLOR, 3);}
	@Override public void mouseReleased(MouseEvent e) {styleButton(PRESSED_BG_COLOR, PRESSED_FG_COLOR, PRESSED_BORDER_COLOR, 3);}
	@Override public void mouseEntered(MouseEvent e)  {styleButton(  HOVER_BG_COLOR,   HOVER_FG_COLOR,   HOVER_BORDER_COLOR, 3);}
	@Override public void mouseExited(MouseEvent e)   {styleButton(        BG_COLOR,         FG_COLOR,         BORDER_COLOR, 1);}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        JPanel outer = new JPanel();
        JPanel inner = new JPanel();
        Box box = Box.createVerticalBox();
	        inner.setLayout(new GridLayout(2, 3));
	        ActionListener al = new ActionListener() {
	        	public void actionPerformed(ActionEvent event) {
	        		System.out.println(((JButton)event.getSource()).getName());
	        	}
	        };
	        inner.add(new BoxButton("USER", "USER", "My Profile", al));
	        inner.add(new BoxButton("VEHICLES", "CAR", "My Vehicles", al));
	        inner.add(new BoxButton("SUBSCIPTION", "MAIL", "Subscription", al));
	        inner.add(new BoxButton("NEW_PERMIT", "NEW", "Get Permit", al));
	        inner.add(new BoxButton("HISTORY", "FILES", "History", al));
	        inner.add(new BoxButton("PAY_NOW", "DOLLAR", "Pay Now", al));
        box.add(inner);
        outer.add(box);
        frame.add(outer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
