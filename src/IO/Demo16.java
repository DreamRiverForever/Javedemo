package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo16 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("xxxx.txt"));
        String line;
        line = br.readLine();
        br.close();
        int time = Integer.parseInt(line);
        if (time>0){
            System.out.println("您还有"+time--+"次机会");
        }else{
            System.out.println("使用次数使用完，请购买正版软件");
        }
        FileWriter fw = new FileWriter("xxxx.txt");
        fw.write(time+"");
        fw.close();
    }
}
