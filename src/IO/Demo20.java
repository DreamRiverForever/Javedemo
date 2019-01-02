package IO;

import javafx.scene.web.WebHistory;
import jdk.nashorn.api.tree.WhileLoopTree;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Demo20 {
    /*
     * 内存输出流
     */
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("zw.txt");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int b;
        while ((b=fis.read())!=-1){
            bos.write(b);//写入内存中
        }
       /* byte[] arr = bos.toByteArray();//转为字节数组
        System.out.println(new String(arr));//转为字符串*/
        System.out.println(bos.toString());//将缓冲区的内容转化为字符串
        fis.close();

    }
}
