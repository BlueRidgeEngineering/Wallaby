package wallaby;

import java.awt.GridLayout;
import javax.swing.*;

/**
 * This class is devoted to setting pop-ups for the GUI
 *
 * @author Jonathan
 */
public class PopUp {

    public static boolean CANCEL = false;

    public PopUp() {
    }

    /**
     * Asks user if they want to delete the file they selected, gives
     * confirmation
     *
     * @return
     */
    public static int safeToDeletePopup() {
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        // set selection
        Object[] options = {"Yes", "Cancel"};
        int selection = JOptionPane.showOptionDialog(jf,
                "Delete Saved Clipboard?",
                "Warning",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);
        return selection;
    }

    /**
     * Define frame to support input title for save data
     *
     * @return
     */
    public static String titleFrame() {
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        String title = JOptionPane.showInputDialog(jf, "Enter Name of New Clipboard");

        while (title.length() == 0) {
            noSaveDataFrame("PLEASE ENTER A TITLE");
            title = JOptionPane.showInputDialog(jf, "Enter Name of New Clipboard");

            if (title.length() > 0) {
                return title;
            }
        }
        return title;
    }

    /**
     * If not data, show warning message there is none to save
     */
    public static void noSaveDataFrame(String data) {
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(jf, data);
    }

    public static int saveClipboard() {
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        Object[] options = {"Yes", "No"};
        int selection = JOptionPane.showOptionDialog(jf,
                "Delete Saved Clipboard?",
                "Warning",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[0]);
        return selection;
    }

    /**
     * gets user input if user wants to save the clipboard
     */
    public static void saveClipboardContent() {
        JFrame jf = new JFrame();
        jf.setLayout(new GridLayout(2, 1, 0, 0));
        jf.setAlwaysOnTop(true);
        Object[] options = {"Yes", "No"};
        int selection = JOptionPane.showOptionDialog(jf,
                "Save Clipboard?",
                "***  Wallaby  ***",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (selection == 0) {
            Settings.IS_SAVE = true;
        }
    }
}
