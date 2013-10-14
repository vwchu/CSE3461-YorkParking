package app.uitoolkit;

import app.helpers.*;
import app.model.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class TitlePane extends JPanel implements Observer {

	private static final int HORIZONTAL_MARGIN = 100; 
	private static final int PADDING           = 20;
	private static final int SUBTITLE_SIZE     = 20;
	private static final int TITLE_SIZE        = 60;
	private static final String SUBTITLE_TEXT  = "York Parking";
	private static String HTML                 = null;

	private final JLabel TITLE;		// label of the title of the view.
	private final JLabel USER_TAG;	// label of the name of the user logged in.

	/**
	 * Construct the title panel of the views.
	 */
	public TitlePane() {
		JPanel inner = new JPanel();
			inner.setLayout(new BorderLayout());
			TITLE = new JLabel();
				TITLE.setFont(MyFont.LIGHT_FONT.deriveFont((float)TITLE_SIZE));
				TITLE.setHorizontalAlignment(SwingConstants.LEFT);
			inner.add(TITLE, BorderLayout.CENTER);
			JPanel subtitle = new JPanel(new GridLayout(1, 2));
				JLabel apptitle = new JLabel();
					apptitle.setFont(MyFont.BOLD_FONT.deriveFont((float)SUBTITLE_SIZE));
					apptitle.setHorizontalAlignment(SwingConstants.LEFT);
					apptitle.setText(SUBTITLE_TEXT.toUpperCase());
				subtitle.add(apptitle);
				USER_TAG = new JLabel();
					USER_TAG.setFont(MyFont.REGULAR_FONT.deriveFont((float)SUBTITLE_SIZE));
					USER_TAG.setHorizontalAlignment(SwingConstants.RIGHT);
				subtitle.add(USER_TAG);
			inner.add(subtitle, BorderLayout.NORTH);
			inner.add(new JLabel(getHTML()), BorderLayout.EAST);
			inner.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
			UIToolbox.setSize(inner, new Dimension(
				UIToolbox.getScreenSize().width - HORIZONTAL_MARGIN * 2,
				TITLE.getFontMetrics(TITLE.getFont()).getHeight() +
				subtitle.getFontMetrics(subtitle.getFont()).getHeight() + PADDING * 2 + 10));
		UIToolbox.box(this, inner);
	}

	private String getHTML() {
		if (HTML != null) {return HTML;}
		HTML = UIToolbox.getHTML("/assets/htdocs/about.html");
		return HTML;
	}

	public void setText(String text) {
		TITLE.setText(text);
	}

	public void update(Observable o, Object arg)  {
		User user = (User)o;
		String opcode = (String)arg;

		if (opcode.equals("ATTACH") || opcode.equals("NAME")) {
			USER_TAG.setText("Logged in: " + user.getFirstName() + " " + user.getSurName());
		} else if (opcode.equals("DETACH")) {
			USER_TAG.setText("");
		}
	}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
    	UITheme.setLookAndFeel();
        JFrame frame = new JFrame();
        TitlePane tp = new TitlePane();
        	tp.setText("Welcome");
        	User u = new User(0, "Bob", "Smith", null, 0, true);
        	u.addObserver(tp);
        frame.add(tp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Simulate name change
	        Thread.sleep(2000L);
	    	u.setName("John", "McDonald");
	    // Simulate logout
	    	Thread.sleep(2000L);
        	u.deleteObserver(tp);
    }
}
