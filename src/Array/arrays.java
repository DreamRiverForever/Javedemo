package Array;

public class arrays {
    public static void main(String[] args){

        int[] ary;
        String[] str;
        User[] user;//对象数组也可以User user[]
        int array[] = new int[5];


    }

}

class User{
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
