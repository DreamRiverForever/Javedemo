package IO;

import java.io.FileOutputStream;
import java.io.IOException;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("yyy.txt");//如果想续写在第二个参数传true
        int count=120;
        while (count>99){
            fos.write(count);//写出的是int数据，文件上自动去除前24位0，形成8字节数据
            count--;
        }
       fos.close();

    }
}
