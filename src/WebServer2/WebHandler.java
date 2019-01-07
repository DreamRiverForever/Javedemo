package WebServer2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class WebHandler extends DefaultHandler {
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
        if (qName.equals("servlet")){
            enters.add(enter);
        }else if (qName.equals("servlet-mapping")){
            mappings.add(mapping);
        }
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
    }
    public List<Enter> getEnters() {
        return enters;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}