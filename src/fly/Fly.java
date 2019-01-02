package fly;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class Fly extends Frame {

    private Graphics g;
    Image planeImg=GameUtil.getImage("imgs/plane.png");
    Image bg=GameUtil.getImage("imgs/bg.jpg");
    Plane plane = new Plane(planeImg,250,250);
    Shell shell = new Shell();
    Shell[] shells = new Shell[50];
    Explode explode;
    Date starttime=new Date();
    Date endtime;
    int priTime;

    public static void main(String[] args){
        Fly fly=new Fly();
        fly.launchFram();
    }


    private Image offScreenImage = null;

    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(500,500);//这是游戏窗口的宽度和高度

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void launchFram(){
        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(500,500);
        this.setLocation(300,300);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        new PaintTread().start();
        addKeyListener(new KeyMonitor());
        for (int i=0;i<50;i++){
            shells[i]=new Shell();
        }
    }


   public void paint(Graphics g){
        super.paint(g);
       g.drawImage(bg,0,0,null);
       plane.drawSelf(g);

       for (int i=0;i<shells.length;i++){
           shells[i].drawSelf(g);
           if (shells[i].getRect().intersects(plane.getRect()))
           {
               plane.live=false;
               if (explode==null){
                   explode=new Explode(plane.x,plane.y);
                   endtime=new Date();
                   priTime=(int)(((Date) endtime).getTime()-starttime.getTime())/1000;
               }

               explode.draw(g);
           }
           if (!plane.live){
               Font f = new Font("宋体",Font.BOLD,20);
               g.setFont(f);
               g.drawString("游戏时间"+priTime+" 秒",(int)plane.x,(int)plane.y);
           }
       }


   }

   class PaintTread extends Thread{
       @Override
       public void run() {
           super.run();
           while(true)
           {
               repaint();//重画窗口

               try
               {
                   Thread.sleep(40);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
       }
   }
   class KeyMonitor extends KeyAdapter{
       @Override
       public void keyPressed(KeyEvent e) {
           super.keyPressed(e);
          plane.addDirection(e);
       }

       @Override
       public void keyReleased(KeyEvent e) {
           plane.minusDirection(e);
       }
   }


}
