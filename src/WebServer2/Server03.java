package WebServer2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*
 *封装响应协议
 * 1.内容可以动态添加
 * 2.关注状态码
 */
public class Server03 {
    private ServerSocket serverSocket;
    public static void main(String[] args) {
        Server03 server01 = new Server03();
        server01.start();

    }
    //启动服务
    public void start(){
        try {
            serverSocket = new ServerSocket(8888);
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //接受连接
    public void receive() throws IOException {
        Socket socket = serverSocket.accept();
        System.out.println("一个客户端建立了连接");
        InputStream is= socket.getInputStream();
        byte[] bytes = new byte[1024*1024];
        int len = is.read(bytes);
        String requestInfo = new String(bytes,0,len);
        System.out.println(requestInfo);

        Response response = new Response(socket);
        StringBuilder content = new StringBuilder();
        response.print("<html>");
        response.print("<head>");
        response.print("<meta charset=\"UTF-8\">");
        response.print("<title>");
        response.print("服务器响应成功");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("server终于回来了    ");
        response.print("</body>");
        response.print("</html>");
        response.push(200);
    }
    //停止服务
    public void stop(){

    }
}
