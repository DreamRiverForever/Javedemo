package Reflect;

import java.io.BufferedReader;
import java.io.FileReader;

public class Demo_Reflect {//Reflect
    public static void main(String[] args) throws Exception{
        //多态
        /*Juicer j = new Juicer();
        j.run(new Apple());
        j.run(new Orange());*/
        //Reflect
        BufferedReader br = new BufferedReader(new FileReader("config.properties"));

        Class clazz = Class.forName(br.readLine());
        Fruit f = (Fruit) clazz.newInstance();//父类引用指向子类对象
        Juicer j = new Juicer();
        j.run(f);

    }
}


interface Fruit{
    public void squeeze();

}
class Apple implements Fruit{
    public void squeeze(){
        System.out.println("榨苹果汁");
    }
}
class Orange implements Fruit{
    public void squeeze(){
        System.out.println("榨橘子汁");
    }
}
class Juicer{
    public void run(Fruit f){
        f.squeeze();
    }
}

