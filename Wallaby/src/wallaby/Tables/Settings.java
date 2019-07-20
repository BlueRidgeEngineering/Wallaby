/*
 * This file is subject to the terms and conditions defined
 * file 'LICENSE.txt', which is part of this source code package.
 */
package wallaby;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jonathan
 */
public class Settings extends Panel {

    // set clear button for main panel
    // set save check box for main to open with default
    public static boolean IS_SAVE = false;

    public Settings() {
        super();
    }

    public JTabbedPane tabbedPane() {
        JTabbedPane pane = new JTabbedPane(JTabbedPane.BOTTOM);
        pane.addTab("Settings", settingsPanel());

        return pane;
    }

    public JPanel settingsPanel() {
        JPanel panel = new JPanel();

        //panel.add(sizeSetting());
        panel.add(checkBoxPanel());

        return panel;
    }

    /**
     * Sets the size of the GUI
     *
     * @return
     */
//    private JPanel sizeSetting() {
//        JPanel p = new JPanel();
//        //p.setLayout(new GridLayout(1, 3, 5, 5));
//        p.setLayout(new FlowLayout());
//        p.setPreferredSize(new Dimension(GUIElements.GUIWIDTH, 60));
//
//        // define radio buttons to determine size
//        JRadioButton small = new JRadioButton("COMPACT");
//        JRadioButton medium = new JRadioButton("STANDARD");
//        JRadioButton large = new JRadioButton("LARGE");
//
//        // add to panel
//        p.add(small);
//        p.add(medium);
//        p.add(large);
//
//        // add buttons to group
//        ButtonGroup bg = new ButtonGroup();
//        bg.add(small);
//        bg.add(medium);
//        bg.add(large);
//
//        // listener
//        ActionListener listener = (ActionEvent actionEvent) -> {
//            AbstractButton btn = (AbstractButton) actionEvent.getSource();
//            System.out.println("Selected: " + btn.getText());
//
//            switch (btn.getText()) {
//                case "COMPACT":
//                    GUIElements.GUIHEIGHT = 320;
//                    GUIElements.GUIWIDTH = 270;
//                    repaintGUI();
//                    break;
//                case "STANDARD":
//                    GUIElements.GUIHEIGHT = 400;
//                    GUIElements.GUIWIDTH = 320;
//                    repaintGUI();
//                    break;
//                case "LARGE":
//                    GUIElements.GUIHEIGHT = 500;
//                    GUIElements.GUIWIDTH = 500;
//                    repaintGUI();
//                    break;
//                default:
//                    break;
//            }
//        };
//
//        // add listeners to buttons
//        small.addActionListener(listener);
//        medium.addActionListener(listener);
//        large.addActionListener(listener);
//
//        //medium.setSelected(true); // set small as default
//
//        return p;
//    }

    /**
     * have a button to click and open a window about the program
     */
    private void aboutPanel() {

    }

    /**
     * public method to get the checkBox panel
     *
     * @return
     */
    public static JPanel getCheckBoxPanel() {
        return checkBoxPanel();
    }

    /**
     *
     * @return
     */
    private static JPanel checkBoxPanel() {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(GUIElements.GUIWIDTH, 30));

        // add checkbox
        JCheckBox isSaveCB = new JCheckBox("Save on Exit");
        isSaveCB.setSelected(false);
        p.add(isSaveCB);

        isSaveCB.addActionListener((ActionEvent e) -> {
            // set status to save session clipboard
            if (!IS_SAVE) {
                IS_SAVE = true;
            } else {
                IS_SAVE = false;
            }
        });
        return p;
    }
}
