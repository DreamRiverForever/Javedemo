package Test;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest01 {//任务定时调度

    public static void main(String[] args) {
        Timer timer = new Timer();
        //timer.schedule(new MyTest(),1000,200);//一秒后执行任务一次，每200ms执行一次
        timer.schedule(new MyTest(),new Date(),200);//设置执行时间
    }
}

class MyTest extends TimerTask{
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("hello word");
        }
        System.out.println("结束");
    }
}