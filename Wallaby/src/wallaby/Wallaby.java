/*
 * This file is subject to the terms and conditions defined
 * file 'LICENSE.txt', which is part of this source code package.
 */
package wallaby;

import javax.swing.ImageIcon;

/**
 *
 * @author Jonathan L Pearson
 */
public class Wallaby {

    SplashScreen screen; // define instnace of splash
    public static boolean value = false;
    
    private void splashScreenDestruct() {
        screen.setScreenVisible(false);
    }

    private void splashScreenInit() {
        ImageIcon myImage = new ImageIcon("splash");
        screen = new SplashScreen(myImage);
        screen.setLocationRelativeTo(null);
        //screen.setProgressMax(100);
        screen.setScreenVisible(true);
    }

    private void setCommandLineValue(String s){
        if (s.equals("tech")){
            this.value = true;
        }
    }
    
    // define instance of Table class
    public static void display() {
        Tab tab = new Tab();
        tab.setVisible(true);
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Wallaby w = new Wallaby();
        w.splashScreenInit();
        Thread.sleep(3000);
        w.splashScreenDestruct();

        // set true if there is an argument
        if (args.length > 0){
            w.setCommandLineValue(args[0]);
        }
        
        display();     
    }
}
