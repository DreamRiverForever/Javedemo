package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

public class Demo18 {
    public static void main(String[] args) throws IOException {
        SequenceInputStream si = new SequenceInputStream(new FileInputStream("xxx.txt"),new FileInputStream("xxxxx.txt"));
        FileOutputStream fos = new FileOutputStream("xx.txt");
        int b;
        while ((b=si.read())!=-1){
            fos.write(b);
        }
        si.close();
        fos.close();

    }
}
