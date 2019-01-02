package IO;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo1 {
    public static void main(String[] args) throws IOException{
        FileInputStream fis = new FileInputStream("xxx.txt");
        int value;
        while ((value=fis.read())!=-1)
        {
            System.out.println( value);
        }
            fis.close();


    }
}
