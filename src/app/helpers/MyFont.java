package app.helpers;

import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * This class provides access to custom font faces.
 * Custom font faces include:
 *  - Open Sans Regular
 *  - Open Sans Light
 *  - Icon font
 */
public class MyFont {

    private static final MyFont my;																	// Reference to singleton instance
    private static final String FONT_PATH           = "/assets/fonts";								// Folder path to fonts files
    private static final String HEADER_FONT_URI     = FONT_PATH + "/OpenSans/OpenSans-Light.ttf";	// TrueType fonts: OpenSans Light
    private static final String BODYTEXT_FONT_URI   = FONT_PATH + "/OpenSans/OpenSans-Regular.ttf";	// TrueType fonts: OpenSans Regular
    private static final String ICON_FONT_URI       = FONT_PATH + "/MyIcons/MyIcons.ttf";			// TrueType fonts: Icon font

    // Setup the fonts families for the
    // header, body text, and icons
    public static final Font HEADER_FONT;
    public static final Font BODYTEXT_FONT;
    public static final Font ICON_FONT;
    static {
        my = new MyFont();
        HEADER_FONT = my.makeFont(HEADER_FONT_URI);
        BODYTEXT_FONT = my.makeFont(BODYTEXT_FONT_URI);
        ICON_FONT = my.makeFont(ICON_FONT_URI);
    }

    // A mapping of special icon names to the icon symbols (unicode)
    public static final Map<String, String> ICONS = new HashMap<String, String>();
    static {
    	ICONS.put("SPACE",  " ");
    	ICONS.put("PREV",   "\ue000");
    	ICONS.put("NEXT",   "\ue001");
    	ICONS.put("BKSP",   "\ue002");
    	ICONS.put("BACK",   "\ue003");
    	ICONS.put("FORW",   "\ue004"); ICONS.put("ENTER",  "\ue004");
    	ICONS.put("SHIFT",  "\ue005");
    	ICONS.put("GOOD",   "\ue006");
    	ICONS.put("ERROR",  "\ue007");
    	ICONS.put("ADD",    "\ue008");
    	ICONS.put("NOTIF",  "\ue009");
    	ICONS.put("HOME",   "\ue00a");
    	ICONS.put("USER",   "\ue00b");
    	ICONS.put("CAR",    "\ue00c");
    	ICONS.put("METER",  "\ue00d");
    	ICONS.put("MAIL",   "\ue00e");
    	ICONS.put("HELP",   "\ue00f");
    	ICONS.put("INFO",   "\ue010");
    	ICONS.put("EDIT",   "\ue011");
    	ICONS.put("TRASH",  "\ue012");
    	ICONS.put("EXIT",   "\ue013");
    	ICONS.put("NEW",    "\ue014");
    	ICONS.put("FILES",  "\ue015");
    	ICONS.put("ALERT",  "\ue016");
    	ICONS.put("KEYB",   "\ue017");
    	ICONS.put("KEY",    "\ue018");
    	ICONS.put("CHECK",  "\ue019");
    	ICONS.put("CROSS",  "\ue01a");
    	ICONS.put("SHIELD", "\ue01b");
    	ICONS.put("DOLLAR", "\ue01c");
    }

    // Load and create font objects
    private MyFont() { }
    private Font makeFont(String fontURI) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream(fontURI));
        } catch (FontFormatException e) {
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return font;
    }

}
