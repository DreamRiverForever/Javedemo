package WebServer2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/*
 *封装请求协议：封装请求参数
 */
public class Request2 {
    //协议信息
    private String requestInfo="";
    //请求方式
    private String method="";
    //请求url
    private String url="";

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getQueryStr() {
        return queryStr;
    }

    //请求参数
    private String queryStr ="";
    private final String CRLF = "\r\n";

    //存储参数
    private Map<String, List<String>> parameterMap;


    public Request2(Socket socket) throws IOException{
        this(socket.getInputStream());
    }

    public Request2(InputStream is){
        parameterMap = new HashMap<>();
        byte[] bytes = new byte[1024*1024];
        int len = 0 ;
        try {
            len = is.read(bytes);
            this.requestInfo = new String(bytes,0,len);
        } catch (IOException e) {
            System.out.println(len);
            e.printStackTrace();
        }
        paeseRequest();
    }

    private void paeseRequest(){
        this.method = this.requestInfo.substring(0,this.requestInfo.indexOf("/")).trim();
        int idx = this.requestInfo.indexOf("/")+1;
        int endIdx = this.requestInfo.indexOf("HTTP/");
        this.url = this.requestInfo.substring(idx,endIdx).trim();
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

        //转成Map
        convertMap();

    }

    private void convertMap(){
        //按照& 分割字符串
        String[] keyValues = this.queryStr.split("&");
        for (String query:keyValues
             ) {
            //再次分割=
            String[] kv = query.split("=");
            kv = Arrays.copyOf(kv,2);
            String key = kv[0];
            String value = kv[1]==null?null:deCode(kv[1],"UTF-8");
            //存入Map
            if (!parameterMap.containsKey(key)){//如果吧不存在键，就创建一个空的链表
                parameterMap.put(key,new ArrayList<String>());
            }
            parameterMap.get(key).add(value);

        }

    }

    public String[] getParameterValues(String key){
        List<String> values = this.parameterMap.get(key);
        if (values==null){
            return null;
        }
        return values.toArray(new String[0]);
    }

    public String getParameter(String key){
        String[] values = getParameterValues(key);
        return values==null?null:values[0];

    }
    //处理中文
    private String deCode(String value,String enc){
        try {
            return java.net.URLDecoder.decode(value,enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
