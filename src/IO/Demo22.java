package IO;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Demo22 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("a.txt","rw");
        raf.write(97);
        raf.seek(10);//在指定位置设定值
        raf.write(98);
        raf.close();

    }
}
