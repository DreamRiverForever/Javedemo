package IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Homework2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
        ArrayList<String> list = new ArrayList<>();
        String line;
        while ((line=br.readLine())!=null){
            list.add(line);
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("zzzz.txt"));
        for (int i=list.size()-1;i>=0;i--){
            bw.write(list.get(i));
            bw.newLine();
        }

        bw.close();

    }
}
