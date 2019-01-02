package 多线程;

public class  Demo1 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();//开启线程
        for (int i=0;i<100;i++){
            System.out.println("bb");
        }
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++)
        System.out.println("aaaaa");
    }
}