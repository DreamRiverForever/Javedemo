package IO;

import java.io.FileReader;
import java.io.IOException;

public class Demo10 {
    public static void main(String[] args)throws IOException {
        FileReader fid = new FileReader("zw.txt");//字符流
        int x;
        while ((x=fid.read())!=-1){
            System.out.print((char)x);
        }
        fid.close();
    }
}
