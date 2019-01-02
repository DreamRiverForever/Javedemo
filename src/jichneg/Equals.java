package jichneg;

import java.util.Objects;

public class Equals {
    public static void main(String[] args){
        User u1=new User(1000,"dengyuwen","123456");
        User u2=new User(1000,"dengyuwen","123456");
        System.out.println(u1==u2);
        System.out.println(u1.equals(u2));

    }
}

class User{
    int age;
    String name;
    String pwd;

    public User(int age, String name, String pwd) {
        this.age = age;
        this.name = name;
        this.pwd = pwd;
    }

    @java.lang.Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

}