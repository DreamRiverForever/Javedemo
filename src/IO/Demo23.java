package IO;

import java.io.*;

public class Demo23 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("aa.txt"));
        Person p1 = new Person("qqq",20);
        Person p2 = new Person("www",20);
        oos.writeObject(p1);
        oos.writeObject(p2);//也可以把多个对象加入list在存入文件中writeObject(list)
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("aa.txt"));
        Person p3 = (Person) ois.readObject();
        Person p4 = (Person) ois.readObject();//通过foreach读list
        System.out.println(p3);
        System.out.println(p4);
        ois.close();


    }
}
class Person implements Serializable{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name="+name+" "+"age="+age;
    }
}
