package IO;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class Demo19 {
    public static void main(String[] args) throws IOException {//可以用于歌曲串烧，多首个合在一起
        Vector<InputStream> v = new Vector<>();
        v.add(new FileInputStream("xx.txt"));
        v.add(new FileInputStream("xxx.txt"));
        v.add(new FileInputStream("xxxx.txt"));
        Enumeration<InputStream> en = v.elements();
        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream("x.txt");
        int b;
        while ((b=sis.read())!=-1){
            fos.write(b);
        }
        sis.close();
        fos.close();

    }
}
