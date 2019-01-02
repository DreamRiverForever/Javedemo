package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class homework {
    public static void main(String[] args)throws IOException {
        Scanner in = new Scanner(System.in);
        FileOutputStream fos = new FileOutputStream("text.txt",true);
        while (true){
            String temp = in.nextLine();
            if ("quit".equals(temp)){
                break;
            }
            fos.write(temp.getBytes());
            fos.write("\r\n".getBytes());
        }
        fos.close();
    }
}
