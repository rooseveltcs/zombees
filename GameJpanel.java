import java.awt.*;
import java.awt.event.*;
import java.net.URL; 
import javax.swing.*;
//contorl all the setting of the Jframe and Jpanel and call the draw method
public class GameJpanel extends JFrame {   
   public GameJpanel() {
      this.setTitle("Zombees");
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);//end progem
      this.setSize(new Dimension(1100,820)); //jpanel size
      this.setLocationRelativeTo(null);
      this.getContentPane().setLayout(new BorderLayout());
      JPanel subPanel = new JPanel();
      this.getContentPane().add(new GamePanel());
      this.setResizable(false); 
      this.setVisible(true);
   }
}
