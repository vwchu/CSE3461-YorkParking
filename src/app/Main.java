package app;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import app.helpers.*;
import app.model.*;
import app.views.*;

/**
 * This class is the root class of the application.
 */
public class Main {

    public static User USER;

    public static void createAndShowGUI() {
        UITheme.setLookAndFeel();
        JFrame frame = new JFrame();
        UIToolbox.fullscreen(frame);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                DBManager.SELF.destroy();
            }
        });
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    DBManager.SELF.destroy();
                    System.exit(0);
                }
                return false;
            }
        });
        frame.setLayout(MultiPanel.SELF.setParent(frame));
            MultiPanel.SELF.add(new WelcomePage());
            MultiPanel.SELF.add(new LoginPage());
            MultiPanel.SELF.add(new HomePage());
            MultiPanel.SELF.add(new UserPage());
               MultiPanel.SELF.add(new ChangePINPage());
            MultiPanel.SELF.add(new VehiclesPage());
               MultiPanel.SELF.add(new EditVehiclePage());
               MultiPanel.SELF.add(new InsurancePage());
            MultiPanel.SELF.add(new SubscriptionPage());
            MultiPanel.SELF.add(new NewPermitPage());
            MultiPanel.SELF.add(new PermitDeniedPage());
            MultiPanel.SELF.add(new CreateVehiclePage());
            MultiPanel.SELF.add(new LicensePage());
            MultiPanel.SELF.add(new PayNowPage());
            MultiPanel.SELF.add(new ReceiptPage());
            MultiPanel.SELF.add(new HistoryPage());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] arg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() { createAndShowGUI(); }
        });
    }
}
