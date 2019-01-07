package WebServer2;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

public class WebApp {
    private static WebContent webContent;
    static {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            WebHandler handler = new WebHandler();

            parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("WebServer2/webserver.xml"),handler);
            List<Enter> enters = handler.getEnters();
            List<Mapping> mappings = handler.getMappings();
            webContent = new WebContent(enters,mappings);

        }catch (Exception e){
            System.out.println("解析配置文件错误");
        }
    }
    //通过url获取配置文件
    public static Servlet getServletFromUrl(String url){
        String className = webContent.getClz("/"+url);
        Class clz = null;
        try {
            clz = Class.forName(className);
            Servlet servlet = (Servlet) clz.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
