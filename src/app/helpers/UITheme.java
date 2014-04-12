package app.helpers;

import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.*;

/**
 * This class defines the theme colors of the
 * user interface.
 */
public class UITheme {

    private UITheme() { } // static class, no constructor

    public static final Color FG_COLOR             = Color.BLACK;                // Text color
    public static final Color BG_COLOR             = Color.WHITE;                // Background color
    public static final Color BORDER_COLOR         = Color.LIGHT_GRAY;            // Border color
    public static final Color HOVER_FG_COLOR       = FG_COLOR;                    // Text color (hovered)
    public static final Color HOVER_BG_COLOR       = BG_COLOR;                    // Background color (hovered)
    public static final Color HOVER_BORDER_COLOR   = new Color(184, 207, 229);    // Border color (hovered)
    public static final Color PRESSED_FG_COLOR     = FG_COLOR;                    // Text color (clicked)
    public static final Color PRESSED_BG_COLOR     = new Color(184, 207, 229);    // Background color (clicked)
    public static final Color PRESSED_BORDER_COLOR = new Color(148, 182, 216);    // Border color (clicked)

    /**
     * Set the look and feel of the user interface.
     * Configures the UIManager, colors and styling.
     */
    public static void setLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // TODO: Configure theming colors. UIManager.put(property, new Color(...));
            // {@see http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html}



        } catch (Exception e) {}
    }
}
