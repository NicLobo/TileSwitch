///FOR TILE SWITCH
public class tile{
  
  int posx,posy,xspeed,yspeed,width=100,height=100,order,counter=0,checker=0;

  public void setpos(int x,int y,int ord){
    
     posx=x;
     posy=y;
     xspeed=0;
     yspeed=0;
     order=ord;
    }

  
  public void updatespeed(){   
    
    if (xspeed!=0 || yspeed!=0 && counter<=10){
    posx+=xspeed;  
    posy+=yspeed; 
    counter+=1;
    checker=0;
    }
    if (counter==10){
    counter=0;
    checker=1;
    xspeed=0;
    yspeed=0;
    }
  }
}