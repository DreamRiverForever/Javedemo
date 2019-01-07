package WebServer2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 *多线程处理加入分发器
 */
public class Server08 {
    private ServerSocket serverSocket;
    private boolean isRunning;
    public static void main(String[] args) {
        Server08 server01 = new Server08();
        server01.start();

    }
    //启动服务
    public void start(){
        try {
            serverSocket = new ServerSocket(8888);
            isRunning = true;
                receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //接受连接
    public void receive(){
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println(socket);
                    new Thread(new Dispatcher(socket)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    //停止服务
    public void stop(){
        isRunning = false;
        try {
            serverSocket.close();
            System.out.println("服务器停止");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
