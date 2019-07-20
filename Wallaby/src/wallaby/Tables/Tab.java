package wallaby;

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author Jonathan
 */
public final class Tab extends Panel implements MouseListener {

    private int cnt = 1; // we always have the clipboard and settings for count 0 and 1

    // define class instances
    private Settings setting = new Settings();
    private Table table = new Table();
    private DefinedTable dt;
    private JTabbedPane pane;
    private boolean isEdit = false;

    // path will be static, will need to update for portability
    //public static String PATH = "C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\NetBeansProjects\\Wallaby\\dist\\config";
    public static String PATH = System.getProperty("user.dir") + "\\projects\\config";

    public Tab() {
        super();
        this.isEdit = Wallaby.value;
        add(tabbedPane());
        pack();
    }

    /**
     *
     * @return
     */
    @Override
    public JTabbedPane tabbedPane() {
        // set tabbed pane parameters
        UIManager.put("TabbedPane.selected", GUIElements.setHighlightColor());
        this.pane = new JTabbedPane(JTabbedPane.BOTTOM);
        getFilesInFolder(); // bring in all saved data
        //this.pane.addTab("Settings", setting.settingsPanel()); // settings tab to be static
        //setTabSize();
        setTabColor(); // set look and feel of tabs      

        return pane;
    }

    /**
     *
     * @return
     */
    @Override
    public JScrollPane copyTable() {
        JScrollPane panel = new JScrollPane();
        return panel;
    }

    /**
     *
     * @return
     */
    private void getFilesInFolder() {

        // load in each *.wal file for predefined configs
        File dir = new File(this.PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (int i = 0; i < directoryListing.length; i++) {
                String wal = directoryListing[i].toString();

                wal = wal.replace(this.PATH + "\\", "");
                if (wal.equals("_default.wal")) {
                    if (!isEdit) {
                        this.pane.insertTab("Clipboard", null, table.copyTable(), null, 0);
                        // set default index
                        this.pane.setSelectedIndex(this.pane.getSelectedIndex() - 1);
                    }
                } else {
                    // load saved data
                    wal = wal.replace(".wal", "");

                    // define new instance of DefinedTable
                    dt = new DefinedTable(directoryListing[i].toString(), wal);
                    // add tab with removable button
                    this.pane.addTab(wal, dt.copyTable());
                    initTabComponent(i);
                    this.cnt++; // step up
                }
            }
        }
    }

    /**
     * set look and feel of tabs
     */
    private void setTabColor() {
        // set all tabs the same colors
        for (int i = 0; i < this.pane.getTabCount(); i++) {
            this.pane.getComponentAt(i).setBackground(Color.WHITE);
        }
        this.pane.setOpaque(true);
    }

    /**
     * Initialize tab to contain remove button "X"
     *
     * @param i
     */
    private void initTabComponent(int i) {
        // set only if not technician version
        if (!isEdit) {
            this.pane.setTabComponentAt(i, new ButtonTabComponent(this.pane));
        }
    }

    /**
     *
     */
    private void setTabSize() {
        // set tab size
        JLabel label = new JLabel();
        label.setMaximumSize(new Dimension(125, 18));

        // itterate through all tabs
        for (int i = 0; i <= this.cnt; i++) {
            this.pane.setTabComponentAt(i, label);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
