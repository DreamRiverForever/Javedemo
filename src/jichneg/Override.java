package jichneg;

public class Override {
    public static void main(String[] args){
        Horse horse=new Horse();
        horse.run();
        horse.stop();
    }
}


class Vehicle{
    public void run(){
        System.out.println("run forever");
    }
    public void stop(){
        System.out.println("stop right now");
    }
}
class Horse extends Vehicle{
    public void run(){
        System.out.println("run");//重写父类中的run方法
    }

}