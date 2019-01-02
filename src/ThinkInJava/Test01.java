package ThinkInJava;

public class Test01 extends C{
    Test01(){
        System.out.println("A的构造器");
    }
    public static void main(String[] args){
        C c = new C();

    }
}

class B{
    B(){
        System.out.println("B的构造器");
    }

}

class C{
    B b = new B();
}

