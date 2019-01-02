package 网络编程;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Dem0_TCP {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1",12345);//最多65535
        BufferedReader br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println(in.nextLine());//写;
        System.out.println(br.readLine());
        socket.close();




    }
}
