package WebServer2;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable{
    private Socket socket;
    private Request2 request;
    private Response response;
    public Dispatcher(Socket socket){
        this.socket = socket;
        try {
            request = new Request2(socket);
            response = new Response(socket);
        } catch (IOException e) {
            e.printStackTrace();
            this.relase();
        }
    }
    @Override
    public void run() {
        try {
            Servlet servlet = null;
            servlet = WebApp.getServletFromUrl(request.getUrl());
            if (servlet!=null){
                servlet.service(request,response);
            }else {
                //错误页面
                response.push(404);
            }
            response.push(200);

        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.push(500);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        relase();
    }
    private void relase(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
