package IO;

import java.io.FileWriter;
import java.io.IOException;

public class Demo11 {
    public static void main(String[] args)throws IOException {
        FileWriter fiw = new FileWriter("zw3.txx");
        fiw.write("你好我是何佳楠");
        fiw.close();

    }
}
