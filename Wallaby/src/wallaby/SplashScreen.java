/*
 * This file is subject to the terms and conditions defined
 * file 'LICENSE.txt', which is part of this source code package.
 */
package wallaby;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel imageLabel = new JLabel();
  //JPanel southPanel = new JPanel();
  FlowLayout southPanelFlowLayout = new FlowLayout();
  //JProgressBar progressBar = new JProgressBar();
  ImageIcon imageIcon;

  public SplashScreen(ImageIcon imageIcon) {
    this.imageIcon = imageIcon;
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  // note - this class created with JBuilder
  void jbInit() throws Exception {
    imageLabel.setIcon(imageIcon);
    this.getContentPane().setLayout(borderLayout1);
    //southPanel.setLayout(southPanelFlowLayout);
    //southPanel.setBackground(Color.BLACK);
    this.getContentPane().add(imageLabel, BorderLayout.CENTER);
    //this.getContentPane().add(southPanel, BorderLayout.SOUTH);
    //southPanel.add(progressBar, null);
    this.pack();
  }
  public void setScreenVisible(boolean b)
  {
    final boolean boo = b;
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        setVisible(boo);
      }
    });
  }
}
