package app.helpers;

import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * This static class contains a collection of commonly
 * used methods for constructing, displaying, manipulating
 * user interface components.
 */
public class UIToolbox {

	private static UIToolbox self = new UIToolbox();

	private UIToolbox() { } // static class, no constructor

	/**
	 * Return the screen size.
	 * 
	 * @return the screen size.
	 */
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();  
	}

    /**
     * Add the panel to the outer panel and box.
     * This fixes the size of the panel, so it does not
     * expand when resized.
     * 
     * @param outer		panel to be added to.
	 * @param inner		panel to add to the outer.
	 * @return			the outer panel 
	 */
    public static JPanel box(JPanel outer, JPanel inner) {
    	Box box = Box.createVerticalBox();
        box.add(inner);
        outer.add(box);
        return outer;
    }

	/**
	 * Set the size of the given of the component.
	 * 
	 * @param comp	component to set the size of
	 * @param size	width and height of the panel
	 * @return		the component that was given. 
	 */
	public static Component setSize(Component comp, Dimension size) {
		comp.setPreferredSize(size);
		comp.setMinimumSize(size);
		comp.setMaximumSize(size);
		comp.setSize(size);
		return comp;
	}

	/**
	 * Makes the given JFrame display as fullscreen. 
	 * 
	 * @param frame 	the JFrame to make fullscreen.
	 */
    public static void fullscreen(JFrame frame) {
    	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    	frame.setUndecorated(true);
    	frame.setAlwaysOnTop(true);
    }

    /**
     * Reads HTML document at the given address and
     * returns its content as a string.
     * 
     * @param address 	of the HTML document to read
     * @return 			the content of the HTML document
     */
	public static String getHTML(String address) {
		InputStream fis = null;
		BufferedReader reader = null;
		String html = "";
		try {
			fis = self.getClass().getResourceAsStream(address);
			reader = new BufferedReader(new InputStreamReader(fis));
			String line;
            for (;;) {
            	line = reader.readLine();
            	if (line == null) {break;}
            	html += line;
            }
		} catch (FileNotFoundException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (IOException e) {
        	System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException e) {
            	System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
		return html;
	}    

}
