package 多线程;

public class Demo15Singleton {//单例设计模式

    public static void main(String[] args) {
        SingLeton s1 = SingLeton.getS();
        SingLeton s2 = SingLeton.getS();
        System.out.println(s1==s2);

    }
}
//饿汉式    比较好
class SingLeton{
    private SingLeton(){//私有构造方法，其他类不能访问该方法
    }
    //也可以public，但是必须加final关键字，不可改变，只能创建一个对象
    private static SingLeton s = new SingLeton();//设置为静态可以使用类名点s

    public static SingLeton getS() {//只能拿不能改
        return s;
    }
}
//懒汉式   线程不安全
class SingLeton2{
    private SingLeton2(){//私有构造方法，其他类不能访问该方法
    }

    private static SingLeton2 s;

    public static SingLeton2 getS() {//只能拿不能改
        if (s == null){
            //线程1进入等待，线程2进入，创建了两个对象不是单例设计模式
            s = new SingLeton2();
        }
        return s;
    }
}
/*
 *饿汉式空间换时间，先创建对象浪费内存，调用快
 * 懒汉式是时间换空间，先声明不创建，在判断要不要创建，浪费时间
 * 在多线程访问时饿汉式不会创建多个对象，懒汉式有可能创建多个对象
 *
 */

