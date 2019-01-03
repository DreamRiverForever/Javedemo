package Test;

public class CoTest2 {//信号灯法
    public static void main(String[] args){
        Tv tv = new Tv();
        new Player(tv).start();
        new Watch(tv).start();


    }
}

class Tv{
    String voice;
    boolean flag = true;//为真演员表演，观众等待，为假相反
    public synchronized void play(String voice) {
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表演了"+voice);
        this.voice = voice;
        flag = !flag;
        this.notifyAll();
    }



    public synchronized void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("听到了"+voice);
        flag = !flag;
        this.notifyAll();
    }
}

class Player extends Thread{
    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            if (i%2==0){
                this.tv.play("奇葩说");
            }else {
                this.tv.play("太污了");
            }
        }
    }
}
class Watch extends Thread {
    Tv tv;

    public Watch(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.tv.watch();
        }
    }
}