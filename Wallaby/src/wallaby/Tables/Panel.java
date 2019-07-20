/*
 * This file is subject to the terms and conditions defined
 * file 'LICENSE.txt', which is part of this source code package.
 */
package wallaby;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;

/**
 *
 * @author Jonathan L Pearson
 */
public class Panel extends JFrame implements WindowListener {

    // Icon image
    private ImageIcon img = new ImageIcon("wallabyIMG");

    /**
     * Default constructor to set frame parameters
     */
    public Panel() {
        setTitle("***  WALLABY  ***");
        setPreferredSize(new Dimension(GUIElements.GUIWIDTH, GUIElements.GUIHEIGHT));
        setMinimumSize(new Dimension(350, 200));
        setAlwaysOnTop(true);
        //setResizable(false);
        setIconImage(img.getImage()); // need to draw new image, Wallaby black and white???
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // listener for window closing event
        addWindowListener(this);
        pack();
    }

    /**
     * tabbed pane to set the options and view
     *
     * @return
     */
    public JTabbedPane tabbedPane() {
        JTabbedPane pane = new JTabbedPane(JTabbedPane.BOTTOM);
        Table t = new Table();
        pane.addTab("DATA", t.copyTable());

        return pane;
    }

    /**
     *
     * @return
     */
    public JScrollPane copyTable() {

        JScrollPane panel = new JScrollPane();

        return panel;
    }

    /**
     * Event listener to save clipboard data if user selects yes
     *
     * @param windowEvent
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        SaveDefault sd = new SaveDefault();

        // check if user wants to save clipboard content
        if (!Wallaby.value) {
            PopUp.saveClipboardContent();
        }

        if (Settings.IS_SAVE && !Table.list.isEmpty()) {
            try {
                // get the title from popup and 
                // pass to the SaveDefault class
                sd.write(PopUp.titleFrame());
            } catch (IOException ex) {
                System.out.println("Cannot write Default: " + ex);
            }

            System.exit(0);
        } else if (!Settings.IS_SAVE) {
            // erase contents of file
            try {
                sd.eraseFile();
            } catch (IOException ex) {
                System.out.println("Cannot erase Default: " + ex);
            }

            System.exit(0);
        } else {
            // inform user there is no data to save
            PopUp.noSaveDataFrame("NO CONTENT TO SAVE!");
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
