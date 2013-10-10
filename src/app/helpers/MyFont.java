package app.helpers;

import java.awt.*;
import java.io.*;

/**
 * This class provides access to custom font faces.
 * Custom font faces include:
 *  - Open Sans Regular
 *  - Open Sans Light
 *  - Icon font
 */
public class MyFont {

    private static final MyFont my;
    private static final String FONT_PATH           = "/assets/fonts";
    private static final String HEADER_FONT_URI     = FONT_PATH + "/OpenSans/OpenSans-Light.ttf";
    private static final String BODYTEXT_FONT_URI   = FONT_PATH + "/OpenSans/OpenSans-Regular.ttf";
    private static final String ICON_FONT_URI       = FONT_PATH + "/Icons/Icons.ttf";

    public static final Font HEADER_FONT;
    public static final Font BODYTEXT_FONT;
    public static final Font ICON_FONT;

    static {
        my = new MyFont();
        HEADER_FONT = my.makeFont(HEADER_FONT_URI);
        BODYTEXT_FONT = my.makeFont(BODYTEXT_FONT_URI);
        ICON_FONT = my.makeFont(ICON_FONT_URI);
    }

    private MyFont() { }
    private Font makeFont(String fontURI) {
        Font font = null;
        try {
            font = Font.createFont(
                    Font.TRUETYPE_FONT,
                    this.getClass().getResourceAsStream(fontURI)
                );
        } catch (FontFormatException e) {
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return font;
    }

}
