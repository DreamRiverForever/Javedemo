package WebServer2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 *整合配置文件
 */
public class Server07 {
    private ServerSocket serverSocket;
    public static void main(String[] args) {
        Server07 server01 = new Server07();
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
        Request2 request = new Request2(socket);
        Response response = new Response(socket);
        Servlet servlet = null;
        servlet = WebApp.getServletFromUrl(request.getUrl());
        if (servlet!=null){
            servlet.service(request,response);
        }else {
            //错误页面
            response.push(404);
        }
        response.push(200);
    }
    //停止服务
    public void stop(){

    }
}
