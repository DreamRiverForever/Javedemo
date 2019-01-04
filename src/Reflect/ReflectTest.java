package Reflect;

import java.lang.reflect.InvocationTargetException;

public class ReflectTest {
    /*
     *获取class文件的三种方式
     *clz.getConstructor().newInstance()
     */
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Iphone iphone = new Iphone();
        Class clz = iphone.getClass();//1

        clz = Iphone.class;//2

        try {
            clz = Class.forName("Reflect.Iphone");//3
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Iphone iphone1 = (Iphone)clz.getConstructor().newInstance();
        System.out.println(iphone1);
    }

}

class Iphone{
    public Iphone(){

    }
}