package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Demo12 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
        String line;
        while ((line=br.readLine())!=null){//读一行
            System.out.println(line);
        }
        br.close();
    }
}
