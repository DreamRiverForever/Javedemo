package IO;

import java.io.*;

public class Demo13 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("zzzz.txt"));
        String line;
        while ((line=br.readLine())!=null){
            bw.write(line);
            bw.newLine();//写换行符也可以\r\n用来换行，newline全平台可用
        }
        br.close();
        bw.close();
    }
}
