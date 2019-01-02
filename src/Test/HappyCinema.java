package Test;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema {
    public static void main(String[] args) {
        List<Integer> step = new ArrayList<>();
        step.add(1);
        step.add(2);
        step.add(4);
        step.add(5);
        step.add(7);

        List<Integer> step2 = new ArrayList<>();
        step2.add(1);
        step2.add(2);

        List<Integer> step3 = new ArrayList<>();
        step3.add(4);
        step3.add(5);
        step3.add(8);
        Cinema cinema = new Cinema(step,"happycinema");
        new Thread(new Customer(cinema,step2),"邓玉文").start();
        new Thread(new Customer(cinema,step3),"何佳楠").start();
    }

}

class Cinema{
    List<Integer> available;
    String name;

    public Cinema(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }
    public boolean booktickets(List<Integer> seat){
        System.out.println("欢迎购票，可用位置为："+available);
        List<Integer> copy = new ArrayList<>();
        copy.addAll(available);
        copy.removeAll(seat);
        if (available.size()-copy.size()!=seat.size()){
            return false;
        }
        available = copy;
        return true;
    }
}
class Customer implements Runnable{
    Cinema cinema;
    List<Integer> seat;

    public Customer(Cinema cinema, List<Integer> seat) {
        this.cinema = cinema;
        this.seat = seat;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            boolean flag = cinema.booktickets(seat);
            System.out.println("你所需要购买的位置为"+seat);
            if (flag) {
                System.out.println("出票成功" + Thread.currentThread().getName() + "位置为：" + seat);
            } else {
                System.out.println("出票失败，位置不够");
            }
        }
    }
}