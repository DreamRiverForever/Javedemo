package Test;

public class CoTest {//生产者消费者实现管程法
    public static void main(String[] args) {
        SynContainer sy = new SynContainer();
        Productor p = new Productor(sy);
        Consumer c = new Consumer(sy);
        p.start();
        c.start();


    }
}

class Productor extends Thread{
    SynContainer container;
    public Productor( SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("生产第 "+i);
            container.push(new Steamedbum(i));
        }
    }
}
class Consumer extends Thread{
    private SynContainer container;
    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("消费第 "+container.pop().id);
        }
    }
}
class SynContainer{
    Steamedbum[] bums = new Steamedbum[10];
    int count = 0;
    public synchronized void push(Steamedbum bum){//生产
        if (count==10){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        bums[count] = bum;
        count++;
        this.notify();
    }
    public synchronized Steamedbum pop(){//消费
        if (count==0){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        count--;
        Steamedbum bum = bums[count];
        this.notify();
        return bum;

    }


}
class Steamedbum{
     int id;

    public Steamedbum(int id) {
        this.id = id;
    }
}