package 多线程;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Demo17Timer {
    public static void main(String[] args) {//定时器,指定时间安排指定任务
        Timer t = new Timer();
        t.schedule(new MyTimerTask(),new Date());//参数安排任务，执行的时间，过多长时间在重复执行

    }
}

class MyTimerTask extends TimerTask{
    @Override
    public void run() {
        System.out.println("起来了背书了");
    }
}