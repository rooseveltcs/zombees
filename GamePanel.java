import java.awt.*;
import java.awt.event.*;
import java.net.URL; 
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{
   private Image characterImage; 
   private Image backImage;
   private Image zombieImage;
   private Image bulletImage;
   private int xCoordinate = 200; 
   private int yCoordinate = 400; 
   private int attack = 0;
   private int time = 0,time2 = 0,time3 = 0;
   private int randomInt = 0;
   boolean key_w, key_s, key_a, key_d, key_j;
   private int x = xCoordinate,y = yCoordinate;
   private int x1 = 1100, x2 = 1100, x3 = 1100;
   private int y1 = 400, y2 = 500, y3 = 600;
   private int velX = 3 ,velY = 1;
   private int count = 0;
   Timer t = new Timer(5,this);
   Timer t1 = new Timer(5,this);
   
   //control the image move arcoss the panel
   public void actionPerformed(ActionEvent i){
      x += velX; 
      x1-= velY;   
      repaint();
   }

   //drawing all the graphic show up in the game panel incould control
   public void paintComponent(Graphics g) {
      super.paintComponent(g); 
      g.drawImage(backImage,0,0,this);
      g.drawImage(characterImage, xCoordinate, yCoordinate, this);
      if (time == 500){draw(g);}//it draw the image before reach 500
      if(key_w) {if(yCoordinate>400){yCoordinate -= 100;} key_w = false;  } 
      if(key_s) {if(yCoordinate<600){yCoordinate += 100;} key_s = false; } 
      if(key_a) { if(xCoordinate<=1100 && xCoordinate>=0){xCoordinate--;} else{xCoordinate = 1;} }
      if(key_d) { if(xCoordinate<=1100 && xCoordinate>=0){xCoordinate++;} else{xCoordinate = 1099;} }
      if(key_j){attack(g);}
      for (int index = 0; index < 50000000; index++) {}
      if(time<500){time++;}//the computer don't count the time right
      repaint();
   }
   
   // it draw the rock also clear the drawing when it meet the zombie//after attack the x coordinate will wrong it fix it after first attack
   public void attack(Graphics g){
       g.drawImage(bulletImage,x + 100,y,this);
       t.start();
       if( x > 1100){t.stop(); x = xCoordinate; y = yCoordinate; key_j = false;}
       // two image should cannel whrn it pass each other
       if(x > x1 && key_j == true && y1 == y && x1 > xCoordinate){t.stop(); t1.stop(); key_j = false; x = xCoordinate; y = yCoordinate; x1 = 1100; time = 0; count=0;} 
   }
   
   //it use random number to draw the zombie//when the random number is on it will not draw
   public void draw(Graphics g){
      randomNum();
      if(randomInt == 1){g.drawImage(zombieImage,x1,y1,this);t1.start();if(x1<=0){t1.stop(); x1 = 1100; time = 0;count=0;}}
      if(randomInt == 2){g.drawImage(zombieImage,x1,y1,this);t1.start();if(x1<=0){t1.stop(); x1 = 1100; time = 0;count=0;}}
      if(randomInt == 3){g.drawImage(zombieImage,x1,y1,this);t1.start();if(x1<=0){t1.stop(); x1 = 1100; time = 0;count=0;}}
   }
    
   public void randomNum(){
      while(count==0){
         double randomDouble = Math.random();
         double randomDoubleNum = randomDouble * 3 + 1;
         randomInt = (int) randomDoubleNum;
         y1 = 300 + randomInt * 100;
         count=1;
      }
   }

   // control all the key action on the Jpanel
   private class GameInput implements KeyListener {

      public void keyTyped(KeyEvent e) {}
      
      //check the key when it released
      public void keyReleased(KeyEvent e) {
         if (e.getKeyCode() == e.VK_A) key_a = false;
         if (e.getKeyCode() == e.VK_D) key_d = false;
         if (e.getKeyCode() == e.VK_S) key_s = false;
         if (e.getKeyCode() == e.VK_W) key_w = false;
      }
      
      //check the key whrn it pressed
      public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == e.VK_A && e.getKeyCode() == e.VK_D) key_a = false; key_d = false;
         if (e.getKeyCode() == e.VK_A) key_a = true;
         if (e.getKeyCode() == e.VK_S) key_s = true;
         if (e.getKeyCode() == e.VK_W) key_w = true;
         if (e.getKeyCode() == e.VK_D) key_d = true;
         if (e.getKeyCode() == e.VK_J) key_j = true;
      }
   }
   
   //out of time to make it replace the url load image
   /*Image[] images = new Image[4];
       for (int i = 0; i < clubs.length; i++ ) {
            images[i] = getImage( getDocumentBase(), "IMG_" + (i + 1) + ".PNG");
       }*/
   
   //everything below is using URL and ImageIcon to read the image and save inage
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
   
   //call image character
   public void loadcharacterFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      character = new ImageIcon(urlForImage);
      characterImage = character.getImage().getScaledInstance(200,250,50);
   }
   
   //call image background
   public void loadbackFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      back = new ImageIcon(urlForImage);
      backImage = back.getImage().getScaledInstance(1100,800,50);
   }
   
   //call image zombie
   public void loadzombieFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      zombie = new ImageIcon(urlForImage);
      zombieImage = zombie.getImage().getScaledInstance(175,225,50);
   }
   
   //call image bullet
   public void loadbulletFromFile(String filename) {
      urlForImage = getClass().getResource(filename);
      bullet = new ImageIcon(urlForImage);
      bulletImage = bullet.getImage().getScaledInstance(100,150,50);
   }

}