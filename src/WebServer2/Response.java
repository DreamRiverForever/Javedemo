package WebServer2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
    private BufferedWriter bw;
    //正文
    private StringBuilder content;

    //协议头信息
    private StringBuilder headInfo;
    private int len;//正文的字节数

    private final String blank = " ";
    private final String CRLF = "\r\n";

    public Response(){
        content = new StringBuilder();
        headInfo = new StringBuilder();
        len = 0;
    }
    public Response(Socket socket){
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Response(OutputStream os){
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }
    //动态添加内容
    public Response print(String info){
        content.append(info).append(CRLF);
        len+=(info+CRLF).getBytes().length;
        return this;
    }
    //推送头信息
    public void push(int code) throws IOException{
        createHeadInfo(code);
        bw.append(headInfo);
        bw.append(content);
        bw.flush();
    }

    //构建头信息
    private void createHeadInfo(int code){
        //1.响应行：HTTP/1.1 200 OK
        headInfo.append("HTTP/1.1");
        headInfo.append(blank);
        headInfo.append(code).append(blank);
        switch (code){
            case 200:
                headInfo.append("OK").append(CRLF);
                break;
            case 404:
                headInfo.append("Not Found").append(CRLF);
                break;
            case 505:
                headInfo.append("Server Error").append(CRLF);
                break;
        }

        //2.响应头(最后一行空行)

        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
        headInfo.append("Content-type:text/html").append(CRLF);
        headInfo.append("Content-lenght:").append(len).append(CRLF);
        headInfo.append(CRLF);

    }
}
