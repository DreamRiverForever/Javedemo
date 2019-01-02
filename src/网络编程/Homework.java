package 网络编程;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Homework {//服务器，客户端是Demo——TCP
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("服务器启动绑定12345接口");
        while (true){
            final Socket socket1 = serverSocket.accept();
            new Thread(){
                @Override
                public void run() {
                    try{
                        BufferedReader br= new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                        PrintStream ps = new PrintStream(socket1.getOutputStream());
                        String line = br.readLine();
                        line = new StringBuilder(line).reverse().toString();//反转
                        System.out.println(line);
                        ps.println(line);
                        socket1.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }.start();
        }

    }

}
