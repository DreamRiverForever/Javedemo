package WebServer2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/*
 *封装请求协议：获取method uri以及请求参数
 */
public class Request {
    //协议信息
    private String requestInfo;
    //请求方式
    private String method;
    //请求url
    private String url;
    //请求参数
    private String queryStr;
    private final String CRLF = "\r\n";


    public Request(Socket socket) throws IOException{
        this(socket.getInputStream());
    }

    public Request(InputStream is){
        byte[] bytes = new byte[1024*1024];
        int len = 0 ;
        try {
            len = is.read(bytes);
            this.requestInfo = new String(bytes,0,len);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        paeseRequest();
    }

    private void paeseRequest(){
        System.out.println("获取请求方式---到第一个  /  ");
        this.method = this.requestInfo.substring(0,this.requestInfo.indexOf("/")).trim();
        System.out.println("获取请求url---第一个 / 到 HTTP/ ");
        int idx = this.requestInfo.indexOf("/")+1;
        int endIdx = this.requestInfo.indexOf("HTTP/");
        this.url = this.requestInfo.substring(idx,endIdx);
        //如果中间有请求参数？，要分割
        int queryIdx = url.indexOf("?");
        if (queryIdx>=0){
            String[] urlArray = url.split("\\?");
            this.url = urlArray[0];
            queryStr = urlArray[1];
        }

        //如果是GET方式，请求参数获取完成，如果在POST方式，信息可能在requestInfo最后一行，所以要重新提取
        if (method.equals("POST")){
            String qStr = this.requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
            if (queryStr == null){
                queryStr = qStr;
            }else {
                queryStr+="&"+qStr;
            }
        }
        System.out.println(method+"---"+url+"---"+queryStr);

    }



}
