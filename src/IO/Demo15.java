package IO;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Demo15 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));
        Map<Character, Integer> tm = new TreeMap<>();
        int ch;
        while ((ch=br.read())!=-1){
            char c = (char)ch;
            tm.put(c,!tm.containsKey(c)? 1:tm.get(c)+1);
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("times.txt"));
        for (Character key:tm.keySet()) {
            switch (key){
                case '\t':
                    bw.write("\\t" +"="+tm.get(key));
                    break;
                case '\n':
                    bw.write("\\n" +"="+tm.get(key));
                    break;
                case '\r':
                    bw.write("\\r" +"="+tm.get(key));
                    break;
                default:
                    bw.write(key +"="+tm.get(key));
            }
            bw.newLine();
        }
        bw.close();
    }
}
