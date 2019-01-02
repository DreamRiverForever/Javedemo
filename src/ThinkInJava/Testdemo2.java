package ThinkInJava;

public class Testdemo2 extends Amphibian{
    @Override
    public String show() {
        return "demo "+name+" "+age;
    }

    public Testdemo2(String name, int age) {
        super(name, age);
    }

    public static void main(String[] args){
        Amphibian testdemo2 = new Testdemo2("kks",22);
        System.out.println(testdemo2.show());

    }
}


class Amphibian{
    protected String name;
    protected int age;
    public String show(){
        return name+' '+age;

    }

    public Amphibian(String name, int age) {
        this.name = name;
        this.age = age;
    }
}