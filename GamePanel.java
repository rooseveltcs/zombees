import java.awt.*;
import java.awt.event.*;
import java.net.URL; 
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{
   private Image characterImage; 
   private Image backImage;
   private Image zombieImage;
   private Image bulletImage;
   private int xCoordinate = 100, yCoordinate = 600, x = xCoordinate + 100, y = yCoordinate; 
   private int attack = 0;
   private int time = 0;
   private int randomInt = 1;
   boolean key_w, key_s, key_a, key_d, key_j;
   Timer t = new Timer(5,this);
   Timer t1 = new Timer(5,this);
   private int y1 = 400, x1 = 1100;
   private int velX = 5 ,velY = 1;
   
   public void actionPerformed(ActionEvent i){
      x += velX; 
      x1-= velY; 
      repaint();
   }


   public void paintComponent(Graphics g) {
      super.paintComponent(g); 
      g.drawImage(backImage,0,0,this);
      g.drawImage(characterImage, xCoordinate, yCoordinate, this);
      if (time == 500){draw(g);}
      if(key_w) {if(yCoordinate>400){yCoordinate -= 100;} key_w = false;  } 
      if(key_s) {if(yCoordinate<600){yCoordinate += 100;} key_s = false; } 
      if(key_a) { if(xCoordinate<=1100 && xCoordinate>=0){xCoordinate--;} else{xCoordinate = 1;} }
      if(key_d) { if(xCoordinate<=1100 && xCoordinate>=0){xCoordinate++;} else{xCoordinate = 1099;} }
      if(key_j){attack(g);}
      for (int index = 0; index < 50000000; index++) {}
      if(time<500){time++;}
      repaint();
   }
   
   public void attack(Graphics g){
       g.drawImage(bulletImage,x,y,this);
       t.start();
       //if(x >= x2){t.stop(); t1.stop(); x = xCoordinate; y = yCoordinate; key_j = false;}
       if( x > 1100){t.stop(); x = xCoordinate; y = yCoordinate; key_j = false;}
       
   }
   
   public void draw(Graphics g){
      if(randomInt == 1){
      g.drawImage(zombieImage,x1,y1,this);t1.start();
      if(x1<=0){t1.stop(); x1 = 1100; time = 0;}
      if(y==y1){t1.stop(); x1 = 1100; time = 0; y = 400;}
      }
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   private class GameInput implements KeyListener {

      public void keyTyped(KeyEvent e) {}
   
      public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() == e.VK_A) key_a = false;
         if (e.getKeyCode() == e.VK_D) key_d = false;
         if (e.getKeyCode() == e.VK_S) key_s = false;
         if (e.getKeyCode() == e.VK_W) key_w = false;
      }
   
      public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == e.VK_A && e.getKeyCode() == e.VK_D) key_a = false; key_d = false;
         if (e.getKeyCode() == e.VK_A) key_a = true;
         if (e.getKeyCode() == e.VK_S) key_s = true;
         if (e.getKeyCode() == e.VK_W) key_w = true;
         if (e.getKeyCode() == e.VK_D) key_d = true;
         if (e.getKeyCode() == e.VK_J) key_j = true;
      }
   }
   
   URL urlForImage;
   ImageIcon character;
   ImageIcon back;
   ImageIcon zombie;
   ImageIcon bullet;

   public GamePanel() {
      loadcharacterFromFile("IMG_2425.PNG"); 
      this.setFocusable(true); 
      addKeyListener(new GameInput()); 
      loadbackFromFile("IMG_2410.PNG"); 
      loadzombieFromFile("IMG_2424.PNG");
      loadbulletFromFile("IMG_2426.PNG");
   }

   public void loadcharacterFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      character = new ImageIcon(urlForImage);
      characterImage = character.getImage().getScaledInstance(200,250,50);
   }
   
   public void loadbackFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      back = new ImageIcon(urlForImage);
      backImage = back.getImage().getScaledInstance(1100,800,50);
   }
   
   public void loadzombieFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      zombie = new ImageIcon(urlForImage);
      zombieImage = zombie.getImage().getScaledInstance(175,225,50);
   }
   
   public void loadbulletFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      bullet = new ImageIcon(urlForImage);
      bulletImage = bullet.getImage().getScaledInstance(100,150,50);
   }

}