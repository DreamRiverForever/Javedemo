package WebServer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WebServerXml {
    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        WebHandler handler = new WebHandler();

        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("WebServer/webserver.xml"),handler);
        List<Enter> enters = handler.getEnters();
        List<Mapping> mappings = handler.getMappings();
        WebContent webContent = new WebContent(enters,mappings);
        //假设输入  /login
        String className = webContent.getClz("/reg");
        Class clz = Class.forName(className);
        Servlet servlet = (Servlet) clz.getConstructor().newInstance();
        servlet.service();


    }
}


class Enter{
    String name;
    String clz;

    public Enter() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }

    public String getName() {
        return name;
    }

    public String getClz() {
        return clz;
    }
}

class Mapping{
    String name;
    Set<String> patten;
    public Mapping(){
        patten = new HashSet<>();
    }

    public Mapping(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatten(Set<String> patten) {
        this.patten = patten;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPatten() {
        return patten;
    }

    public void addPatten(String patten){
        this.patten.add(patten);
    }

}


class WebHandler extends DefaultHandler {
    private List<Enter> enters ;
    private List<Mapping> mappings;
    private Enter enter;
    private Mapping mapping;
    private String tag;
    private boolean isMapping = false;

    @Override
    public void startDocument() throws SAXException {
        enters = new ArrayList<Enter>();
        mappings = new ArrayList<Mapping>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName+"-->解析开始"+tag);
        if (qName!=null){
            tag = qName;
        }
        if (tag.equals("servlet")){
            enter = new Enter();
            isMapping = false;
        }else if (tag.equals("servlet-mapping")){
            mapping = new Mapping();
            isMapping = true;
        }
    }



    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch,start,length).trim();//有空的情况，因为xml文档中有空格
        if (tag!=null){//解决空问题
            if (isMapping){//操作servlet-mapping
                if (tag.equals("servlet-name"))
                {
                    mapping.setName(contents);
                }else if(tag.equals("url-patten"))
                {
                    mapping.addPatten(contents);
                }
            }else {
                if (tag.equals("servlet-name"))
                {
                    enter.setName(contents);
                }else if(tag.equals("servlet-class"))
                {
                        enter.setClz(contents);
                }
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName+"-->解析结束"+tag);
        if (qName.equals("servlet")){
            enters.add(enter);
        }else if (qName.equals("servlet-mapping")){
            mappings.add(mapping);
        }
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("解析文档结束");
    }
    public List<Enter> getEnters() {
        return enters;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}