/*
 * This file is subject to the terms and conditions defined
 * file 'LICENSE.txt', which is part of this source code package.
 */
package wallaby;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Jonathan L Pearson
 */
public class DefinedTable extends Panel implements MouseListener {

    // define table parameters
    private final Object[][] rowData = {};
    private Object[] columnName = {"Copied Data"};

    // define class variables
    private JTable table;
    private DefaultTableModel dtf;
    private String file;

    // define instance of class packages
    private ReadInFile config;

    /**
     * Default constructor to set frame parameters
     *
     * @param file
     * @param title
     */
    public DefinedTable(String file, String title) {
        super();
        this.file = file;
        this.columnName[0] = title; // set the table title
        config = new ReadInFile(this.file); //set the default file

        pack();
    }

    /**
     * tabbed pane to set the options and view
     *
     * @return
     */
    @Override
    public JTabbedPane tabbedPane() {
        JTabbedPane pane = new JTabbedPane(JTabbedPane.BOTTOM);
                     
        return pane;
    }
    
    /**
     * Define the table that will display the copied text
     *
     * @return
     *
     */
    @Override
    public JScrollPane copyTable() {
        this.dtf = new DefaultTableModel(rowData, columnName);
        // define instance of JTable
        this.table = new JTable(dtf);
        // define a panel to nest the table, future enhancement
        JScrollPane panel = new JScrollPane(table);

        this.table.addMouseListener(this); // add mouse listener
        this.table.setSelectionBackground(GUIElements.setHighlightColor());
        panel.addMouseListener(this); //add mouse listener to panel

        StringSelection stringSelection = new StringSelection("");
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        } catch (Exception e) {
            System.out.println(e + "\nSystem clipboard not ready");
        }

        // set the panel
        defaultTable();

        return panel;
    }

    // default code for tables
    public void defaultTable() {
        ArrayList<String> l = config.ReadFile();

        for (String data : l) {
            addRowToTable(data);
        }
    }

    /**
     * sets a TableModel to return the current table
     *
     * @return
     */
    private DefaultTableModel getRowData() {
        return (DefaultTableModel) this.table.getModel();
    }

    /**
     * get the cell value
     *
     * @return value of cell in table
     */
    private String getTableRow() {
        return (String) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
    }

    /**
     * Adds a row to the table per the length of the *.wal file
     *
     * @param data value to add to table cell
     */
    private void addRowToTable(String data) {
        getRowData().addRow(new Object[]{data});
    }

    /**
     * when the mouse is clicked on a cell, it copies the data to the system
     * clipboard.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        String row = getTableRow();

        StringSelection stringSelection = new StringSelection(row);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
