/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallaby;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class SaveDefault {

    private ArrayList<String> list;
    private String title;

    public SaveDefault() {
    }

    public void write(String title) throws IOException {
        this.title = title;
        WriteDefault();
    }

    /**
     *
     * @throws IOException
     */
    private void WriteDefault() throws IOException {
        // define class list
        this.list = Table.list;

        if (!this.list.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Tab.PATH + "\\" + this.title + ".wal"))) {
                if (this.list != null) {
                    for (String item : this.list) {
                        writer.write(item + "\n");
                    }
                }
            }
        }
    }

    /**
     * erase the contents of the select title
     *
     * @throws IOException
     */
    public void eraseFile() throws IOException {
        eraseContents();
    }

    /**
     * private method to erase the file contents
     *
     * @throws IOException
     */
    private void eraseContents() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Tab.PATH + "\\_default.wal"))) {
            writer.write("");
        }
    }

    /**
     * public helper method to delete the file
     * 
     * @param file
     * @throws IOException 
     */
    public void deleteFile(String file) throws IOException {
        deleteFileInDirectory(file);
    }

    /**
     * delete unwanted files
     * 
     * @param file
     * @throws IOException 
     */
    private void deleteFileInDirectory(String file) throws IOException {       
        Path fileToDeletePath = Paths.get(Tab.PATH + "\\" + file + ".wal");
        Files.delete(fileToDeletePath);
    }
}
