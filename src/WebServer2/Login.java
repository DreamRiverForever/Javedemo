package WebServer2;


public class Login implements Servlet{
    @Override
    public void service(Request2 request,Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<meta charset=\"UTF-8\">");
        response.print("<title>");
        response.print("服务器响应成功");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("server终于回来了    "+request.getParameter("uname"));
        response.print("</body>");
        response.print("</html>");
    }
}
