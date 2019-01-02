package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo7 {
    public static void main(String[] args)throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream("xxx.txt");
            fos = new FileOutputStream("yyy.txt");
            int b;
            while ((b=fis.read())!=-1){
                fos.write(b);
            }
        }finally {
            try{
                if (fis!=null)
                {
                    fis.close();
                }
            }finally {
                if (fos!=null)
                {
                    fos.close();
                }
            }
        }


    }
}
