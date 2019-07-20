/*
 * This file is subject to the terms and conditions defined
 * file 'LICENSE.txt', which is part of this source code package.
 */
package wallaby;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Jonathan L Pearson
 */
public class Table extends Panel implements MouseListener {

    // set table parameters
    private Object[][] rowData = {};
    private Object[] columnName = {"Copied Data"};

    // define class variables
    private JTable table;
    private DefaultTableModel dtf;
    public static ArrayList<String> list = new ArrayList<>();
    private String recent = "";

    /**
     * Default constructor to set frame parameters
     */
    public Table() {
        super();
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

        // set the system clipboard
        StringSelection stringSelection = new StringSelection("");
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        } catch (Exception e) {
            System.out.println(e + "\nSystem clipboard not ready");
        }
        return panel;
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
     * Adds a row to the table if there are less than 15 items
     *
     * @param data value to add to table cell
     */
    private void addRowToTable(String data) {
        if (/*this.table.getRowCount() < 15 &&*/ !checkIfExists(data)) {
            getRowData().addRow(new Object[]{data});
        }
    }

    /**
     * Remove the row from the table after it has been copied to system
     * clipboard
     */
    private void removeSelectedFromTable() {

        int numRows = table.getSelectedRows().length;
        for (int i = 0; i < numRows; i++) {
            dtf.removeRow(table.getSelectedRow());
            //list.remove(this.recent);
        }

        // clear list if we remove all rows
    }

    /**
     * check if copied data already exists in the table
     *
     * @param data value to check if we have the item in the table
     *
     * @return ture if the list contains the value, false to add the value
     */
    private boolean checkIfExists(String data) {
        if (list.contains(data)) {
            return true;
        } else {
            list.add(data);
        }

        return false;
    }

    /**
     * This method gets the current system clipboard item and copies it to a
     * String that we will use to insert into our table
     *
     * @return String value
     */
    private String ItemCopy() {
        // define new clipboard to get copy data
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = cb.getContents(null);

        boolean hasStringText = (t != null) && t.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasStringText) {
            try {
                return (String) t.getTransferData(DataFlavor.stringFlavor);
            } catch (IOException | UnsupportedFlavorException ex) {
                System.out.println(ex);
            }
        }

        return null;
    }

    /**
     * public method to return current list
     *
     * @return
     */
    public ArrayList<String> GetCurrentList() {
        ArrayList<String> l = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            l.add((String) table.getModel().getValueAt(table.convertRowIndexToModel(i), 0));
        }

        return l;
    }

    /**
     * when the mouse is clicked on a cell, it copies the data to a clipboard if
     * the data is not the same as any row, add a row
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        String row = getTableRow();

        StringSelection stringSelection = new StringSelection(row);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        // if RIGHT click, remove selected row and set clipboard
        if (e.getButton() == MouseEvent.BUTTON3) {
            // remove new copied item from table
            removeSelectedFromTable();
            list.remove(row); //remove from list so we can recopy
            this.recent = row;
        }
        // If LEFT click, select row to set clipboard
        if (e.getButton() == MouseEvent.BUTTON1) {
            list.remove(row); //remove from list so we can recopy
            this.recent = row;
        }

    }

    /**
     * check if the item exist, otherwise copy to table
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        String str = ItemCopy();

        if (str != null && !str.isEmpty() && !recent.equals(str)) {
            addRowToTable(ItemCopy());
            this.recent = str;
        }
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
