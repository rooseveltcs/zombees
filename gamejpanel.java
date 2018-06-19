import java.awt.*;
import java.awt.event.*;
import java.net.URL; 
import javax.swing.*;

public class gamejpanel extends JFrame {   
   public gamejpanel() {
      this.setTitle("Zombees");
      this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
      this.setSize(new Dimension(1100,830)); 
      this.setLocationRelativeTo(null);
      this.getContentPane().setLayout(new BorderLayout());
      JPanel subPanel = new JPanel();
      this.getContentPane().add(new GamePanel());
      this.setResizable(false); 
      this.setVisible(true);
   }
}