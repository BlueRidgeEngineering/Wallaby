/*
 * This file is subject to the terms and conditions defined
 * file 'LICENSE.txt', which is part of this source code package.
 */
package wallaby;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author Jonathan L Pearson
 */
public class ReadInFile {

    private String file;
    
    public ReadInFile(String file) {
        this.file = file;
    }

    public ArrayList<String> ReadFile() {
        ArrayList<String> list = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File(this.file));
            while (scanner.hasNextLine()) {
                // add each line to a list for a default selection
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
