package app.uitoolkit;

import java.awt.*;
import javax.swing.*;

public class AppWindow extends JFrame {
    public AppWindow() {
        fullscreen();
        //TODO
        pack();
        setVisible(true);
    }
    private void fullscreen() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setAlwaysOnTop(true);
    }
}
