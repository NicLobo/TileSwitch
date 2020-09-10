//RUN THIS
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Rectangle;
public class tileswitch extends JFrame implements ActionListener, Runnable, KeyListener,MouseListener, MouseMotionListener{ 
  
  MyDrawPanel drawpanel1;  
  int xstart=0,ystart=0;
  tile [] tiles;
  int xtiles[]={0,100,200,300,0,100,200,300,0,100,200,300,0,100,200,300};
  int ytiles[]={0,0,0,0,100,100,100,100,200,200,200,200,300,300,300,300};
  int swaps=0,timer=0,startgame=3,gameselect=0;
  long time,endtime,starttime;
  int shuffleflag=0,px,py;
  int snipx2[]={100,200,300,400,100,200,300,400,100,200,300,400,100,200,300,400};
  int snipy2[]={100,100,100,100,200,200,200,200,300,300,300,300,400,400,400,400};
  int checklist[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  BufferedImage chooseimage=null,info=null,titlescreen=null,endcard=null,back=null;
  Boolean disable=true;
  Thread th = new Thread (this); 
  int elapsed=0, minute=0,zeroer=0,pikcounter=0;
  Image  pikapika = Toolkit.getDefaultToolkit().getImage("pikapika.gif"),pikapika2 = Toolkit.getDefaultToolkit().getImage("pikapika2.gif"),pikapika3 = Toolkit.getDefaultToolkit().getImage("pikapika3.gif"),pikapikaend = Toolkit.getDefaultToolkit().getImage("pikapikaend.gif");
  public static void main(String[ ] args)   
  {
    new tileswitch();
  }  
  
  public tileswitch(){
    try {titlescreen=ImageIO.read(new File("titlescreen.png"));
      back=ImageIO.read(new File("back.jpg"));
      chooseimage=ImageIO.read(new File("chooseimage.png"));
      info=ImageIO.read(new File("info.png"));
      endcard=ImageIO.read(new File("endcard.png"));
      
      
    }catch(IOException e){
      System.out.println("no work");}
    
    tiles= new tile[16];
    for (int z=0;z<16;z++){
      tiles[z]=new tile();
    }
    tiles[15].setpos(xtiles[15],ytiles[15],15);
    addKeyListener(this);    
    drawpanel1=new MyDrawPanel();
    
    addMouseMotionListener(this);
    addMouseListener(this);
    this.add (drawpanel1);     
    this.setSize(700,540);
    this.setVisible(true);
    this.setResizable(false);
    
    th.start ();
  } 
  class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
      Font f1= new Font("Bernard MT Condensed", Font.BOLD,30);
      Font f2= new Font("Bernard MT Condensed", Font.BOLD,40);
      Font f3= new Font("Bernard MT Condensed", Font.BOLD,55);
      g2.setFont(f1);
      g2.setColor(Color.BLACK);
      g2.fillRect(0,0,800,800);
      g2.drawImage(back,0,0,700,540,null);
      if(startgame==3){
        g2.drawImage(titlescreen,0,0,700,530,null);  
      }
      
      if(startgame==4){
        g2.drawImage(chooseimage,0,0,700,530,null);  
        g2.drawImage(pikapika,50,100,200,200,null);
        g2.drawImage(pikapika2,250,100,200,200,null);
        g2.drawImage(pikapika3,450,100,200,200,null);
      }
      
      
      if(startgame==1){
        g2.setColor(Color.BLACK);
        if (pikcounter==1){
          g2.drawImage(pikapika,400,0,300,420,null);
        }
        if (pikcounter==2){
          g2.drawImage(pikapika2,400,0,300,420,null);
        }
        if (pikcounter==3){
          g2.drawImage(pikapika3,400,0,300,420,null);
        }
        for (int z=0;z<15;z++){
           
          if (pikcounter==1){
          
            g2.drawImage(pikapika,tiles[z].posx,tiles[z].posy,tiles[z].posx+100,tiles[z].posy+100,xtiles[tiles[z].order],ytiles[tiles[z].order],xtiles[tiles[z].order]+100,ytiles[tiles[z].order]+100,null);
          }
          if (pikcounter==2){
            g2.drawImage(pikapika2,tiles[z].posx,tiles[z].posy,tiles[z].posx+100,tiles[z].posy+100,xtiles[tiles[z].order],ytiles[tiles[z].order],xtiles[tiles[z].order]+100,ytiles[tiles[z].order]+100,null);
          }
          if (pikcounter==3){
            g2.drawImage(pikapika3,tiles[z].posx,tiles[z].posy,tiles[z].posx+100,tiles[z].posy+100,xtiles[tiles[z].order],ytiles[tiles[z].order],xtiles[tiles[z].order]+100,ytiles[tiles[z].order]+100,null);
          }
           g2.setStroke(new BasicStroke(3));
           g2.drawRect(tiles[z].posx,tiles[z].posy,100,100);
        }

        g2.drawImage(info,0,400,700,150,null);
        g2.setColor(Color.BLACK);
        g2.drawString(""+swaps+"",125,490);
        if (elapsed>=10){ 
          g2.drawString(minute+":"+elapsed+"",125,445);
        }
        else{    
          g2.drawString(minute+":"+zeroer+""+elapsed+"",125,445);
        }
      }    
      g2.setColor(Color.WHITE);
      

      
      if (startgame==2){
        g2.setFont(f3);
        g2.drawImage(pikapikaend,0,0,700,600,null);
         g2.drawImage(endcard,0,100,700,163,null);
  
       
      }
      
    }
  }
  public void actionPerformed(ActionEvent e)
  {
    
  } 
  
  public void run () 
  {  
    Thread.currentThread().setPriority(Thread.MIN_PRIORITY); 
    
    
    while (true) 
    { 
      
      if (startgame==1){
        for (int z=0;z<16;z++){
          tiles[z].updatespeed();     
          if (tiles[z].checker==1){
            disable=false;    
            tiles[z].checker=0;
          }
        }
        if (shuffleflag==0){
        checkwinner();
        }
        endtime=System.nanoTime();     
        time=(endtime-starttime);
        if (time>1000000000)
        {
          elapsed++;
          if (elapsed==60)
          {
            minute++;
            elapsed=0;
          }
          starttime=System.nanoTime(); 
        }
      }
      repaint();
      
      try 
      {  
        Thread.sleep (30); 
      } 
      catch (InterruptedException ex) 
      {  
      } 
      
      Thread.currentThread().setPriority(Thread.MAX_PRIORITY); 
    }
  }
  
  public void keyReleased (KeyEvent e){
    int code =e.getKeyCode();
    if (code == KeyEvent.VK_ENTER)   
    { 
      if(startgame==3){
        startgame=4;
      }
      if(startgame==2){
        startgame=3;
      }
    }
    if (code == KeyEvent.VK_1)   
    { 
      if(startgame==4){
        startgame=1;
        shuffle();
        pikcounter=1;   
        starttime=System.nanoTime(); 
        disable=false;
      }
    }
    if (code == KeyEvent.VK_2)   
    { 
      if(startgame==4){
        startgame=1;
        shuffle();
        pikcounter=2;
        starttime=System.nanoTime(); 
        disable=false;
      }
    }
    if (code == KeyEvent.VK_3)   
    { 
      if(startgame==4){
        startgame=1;
        shuffle();
        pikcounter=3;      
        starttime=System.nanoTime(); 
        disable=false;
      }
    }
  }
  public void keyTyped(KeyEvent e){}  
  public void keyPressed (KeyEvent e)
  {
    int code =e.getKeyCode(); 
    if (code == KeyEvent.VK_SPACE)   
    { 
      if (startgame==2){
        
        
        starttime=System.nanoTime(); 
        disable=false;
        startgame=1;
      }
    }
    
  }
  
  
  
  public void shuffle(){
    int order=0;
    int setter=0;
    int dd=0;
    shuffleflag=1;
    for (int z=0;z<15;z++){
      
      do{
        setter=z;      
        
        if (checklist[setter]==0){
          order=setter;
          checklist[setter]=1;
          tiles[setter].setpos(xtiles[z],ytiles[z],order);
          dd=1;
          
        }
        else{
          dd=0;
          
        }
      }while(dd!=1);
      
      
    }   
    shuffle2();
  }
  public void shuffle2()
  {

    shuffleflag=1;
    for (int z=0;z<1000;z++){
      int setter=(int)(Math.random()*15);  
      checkmove(tiles[setter].posx+10,tiles[setter].posy+10);
      
    }
    shuffleflag=0;
  }
  
  
  
  public void mouseClicked(MouseEvent me){
    int ymouse=me.getY();
    int xmouse=me.getX();
    if (disable==false){
      
      checkmove(xmouse,ymouse);
    }
  }
  public void mouseEntered(MouseEvent me){}
  public void mouseExited(MouseEvent me){}
  public void mousePressed(MouseEvent me){ }
  public void mouseReleased(MouseEvent me){}
  public void mouseDragged(MouseEvent me){}
  public void mouseMoved(MouseEvent me){} 
  
  public void checkmove(int x,int y){
    
          
    int checkswitchx=0;
    int checkswitchy=0;
    int whatmove=0;
    for (int z=0;z<15;z++){
      if (x>tiles[z].posx && x<tiles[z].posx+100 && y>tiles[z].posy && y<tiles[z].posy+100){
        checkswitchx=tiles[z].posx;
        checkswitchy=tiles[z].posy; 
       
        whatmove=z;
        if (checkswitchx+100==tiles[15].posx && checkswitchy==tiles[15].posy){
        
          moveanimation(2,whatmove);
        }
        else if (checkswitchx-100==tiles[15].posx && checkswitchy==tiles[15].posy){
          
          moveanimation(4,whatmove);
        }
        else if (checkswitchy+100==tiles[15].posy && checkswitchx==tiles[15].posx ){
          
          moveanimation(3,whatmove);
        }
        else if (checkswitchy-100==tiles[15].posy && checkswitchx==tiles[15].posx){
          
          moveanimation(1,whatmove);
        }
        
        
        
        
      }    
    }
  }
  public void moveanimation (int movemove, int whatmove){
    px=tiles[15].posx;
    py=tiles[15].posy; 
    if (movemove==1){
      if (shuffleflag==0){
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;
        tiles[whatmove].yspeed=-10;
        swaps+=1;
        disable=true;
      }
    
    else {
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;
        tiles[whatmove].posx=px;
        tiles[whatmove].posy=py;
      }
    }
    
  
      if (movemove==2){
          if (shuffleflag==0){
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;
        tiles[whatmove].xspeed=10;  
        disable=true;
        swaps+=1; 
      }
    
      else{
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;
        tiles[whatmove].posx=px;
        tiles[whatmove].posy=py;
      }
      }
    
   
      if (movemove==3){
         if (shuffleflag==0){
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;
        tiles[whatmove].yspeed=10;  
        disable=true;
        swaps+=1;
      }
      else{
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;
        tiles[whatmove].posx=px;
        tiles[whatmove].posy=py;
      }
    }
   
      if (movemove==4){
         if (shuffleflag==0){
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;   
        tiles[whatmove].xspeed=-10;
        disable=true;
        swaps+=1;
      }
         else{
        tiles[15].posx=tiles[whatmove].posx;
        tiles[15].posy=tiles[whatmove].posy;
        tiles[whatmove].posx=px;
        tiles[whatmove].posy=py;
      }
    }
    
    
    
  }
  public void checkwinner(){
    int counter22=0;
    for (int z=0;z<15;z++){  
      if(tiles[z].posx==xtiles[z] && tiles[z].posy== ytiles[z]){
        counter22+=1;         
        
      }
    }
    if (counter22==15){
      startgame=2;
      for (int z=0;z<15;z++){
        checklist[z]=0;
        elapsed=0;
        minute=0;
        swaps=0;
      }
      counter22=0;
      
    }
    
    
    
  }
  
  
}





