package app.uitoolkit;

import app.helpers.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TitlePane extends JPanel {

	private static final int HORIZONTAL_MARGIN = 100; 
	private static final int PADDING           = 30;
	private static final int SUBTITLE_SIZE     = 20;
	private static final int TITLE_SIZE        = 60;
	private static final String SUBTITLE_TEXT  = "York Parking";

	private final JLabel TITLE;
	
	public TitlePane(String text) {
		JPanel inner = new JPanel();
			inner.setLayout(new BorderLayout());
			TITLE = new JLabel();
				TITLE.setFont(MyFont.LIGHT_FONT.deriveFont((float)TITLE_SIZE));
				TITLE.setHorizontalAlignment(SwingConstants.LEFT);
				setText(text);
			inner.add(TITLE, BorderLayout.CENTER);
			JLabel subtitle = new JLabel();
				subtitle.setFont(MyFont.BOLD_FONT.deriveFont((float)SUBTITLE_SIZE));
				subtitle.setHorizontalAlignment(SwingConstants.LEFT);
				subtitle.setText(SUBTITLE_TEXT.toUpperCase());
			inner.add(subtitle, BorderLayout.NORTH);
			inner.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
			UIToolbox.setSize(inner, new Dimension(
				UIToolbox.getScreenSize().width - HORIZONTAL_MARGIN * 2,
				TITLE.getFontMetrics(TITLE.getFont()).getHeight() +
				subtitle.getFontMetrics(subtitle.getFont()).getHeight() + PADDING * 2));
		UIToolbox.box(this, inner);
	}

	private void setText(String text) {
		TITLE.setText(text);
	}

    // FOR TESTING PURPOSES ONLY

    public static void main(String[] args) throws Exception {
    	UITheme.setLookAndFeel();
        JFrame frame = new JFrame();
        frame.add(new TitlePane("Welcome"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
