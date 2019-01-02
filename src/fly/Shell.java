package fly;

import java.awt.*;

public class Shell extends GameObject{
    double degree;
    public Shell(){
        x=200;
        y=200;
        width=10;
        height=10;
        speed=4;
        degree = Math.random()*Math.PI*2;
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        g.setColor(Color.yellow);
        g.fillOval((int)x,(int)y,width,height);
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);
        if (x<10||x>480)
        {
            degree=Math.PI-degree;
        }
        if (y>480||y<30)
        {
            degree=-degree;
        }

    }
}
