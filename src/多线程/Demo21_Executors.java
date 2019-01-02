package 多线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo21_Executors {//线程池
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(new Thread(new MyRunnable()));
        pool.submit(new Thread(new MyRunnable()));
        pool.shutdown();//关闭线程池

    }
}
