package WebServer2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/*
 *返回响应协议
 */
public class Server02 {
    private ServerSocket serverSocket;
    public static void main(String[] args) {
        Server02 server01 = new Server02();
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
        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<head>");
        content.append("<meta charset=\"UTF-8\">");
        content.append("<title>");
        content.append("服务器响应成功");
        content.append("</title>");
        content.append("</head>");
        content.append("<body>");
        content.append("server终于回来了    ");
        content.append("</body>");
        content.append("</html>");
        int size = content.toString().getBytes().length;
        StringBuilder responseInfo = new StringBuilder();
        String blank = " ";
        String CRLF = "\r\n";
        //返回协议
        //1.响应行：HTTP/1.1 200 OK
        responseInfo.append("HTTP/1.1");
        responseInfo.append(blank);
        responseInfo.append(200);
        responseInfo.append(blank);
        responseInfo.append("OK").append(CRLF);

        //2.响应头(最后一行空行)：
        /*
        Data:Mon,31Dec209904:25:57GMT
        Server:shsxt Server/0.0.1;charset=GBK
        Content-type:text/html
        Content-lenght:39725426
         */
        responseInfo.append("Date:").append(new Date()).append(CRLF);
        responseInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
        responseInfo.append("Content-type:text/html").append(CRLF);
        responseInfo.append("Content-lenght:").append(size).append(CRLF);
        responseInfo.append(CRLF);

        //3.正文
        responseInfo.append(content.toString());
        //System.out.println(responseInfo.toString());
        //写出到客户端
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(responseInfo.toString());
        bw.flush();
    }
    //停止服务
    public void stop(){

    }
}
