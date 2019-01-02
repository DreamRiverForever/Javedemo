package jichneg;

public class extend {
    public static void main(String[] args){
        Student stu=new Student();
        stu.name="邓玉文";
        stu.age=22;
        stu.height=178;
        stu.rest();
        stu.show();
        System.out.println(stu instanceof Student);//instanceof用来判断类型
        System.out.println(stu instanceof Person);
    }
}

class Person{
    String name;
    int age;
    public void rest(){
        System.out.println("rest a time");
    }
}
class Student extends Person{

    int height;

    public void study(){
        System.out.println("study two hour");
    }
    public void show(){
        System.out.println(height+" "+age+" "+name);
    }

}
