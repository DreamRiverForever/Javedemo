package WebServer;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Xml {
    /*xml解析
     *熟悉sax流程
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        PHandler handler = new PHandler();

        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("WebServer/p.xml"),handler);
        List<Person> personList = handler.getPersonList();
        for (Person person:
             personList) {
            System.out.println("名字是"+person.getName()+"  年龄为"+person.getAge());

        }
    }
}


class PHandler extends DefaultHandler{
    private List<Person> personList;
    private Person person;
    private String tag;

    public List<Person> getPersonList() {
        return personList;
    }

    @Override
    public void startDocument() throws SAXException {
        personList = new ArrayList<Person>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName+"-->解析开始"+tag);
        if (qName!=null){
            tag = qName;
        }
        if (qName.equals("person")){
            person = new Person();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch,start,length).trim();//有空的情况，因为xml文档中有空格
        if (tag!=null){//解决空问题
            if (tag.equals("name")){
                person.setName(contents);
            }else if(tag.equals("age")){
                if (contents.length()>0){
                    person.setAge(Integer.valueOf(contents));
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName+"-->解析结束"+tag);
            if (qName.equals("person")){
                personList.add(person);
            }
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("解析文档结束");
    }
}



class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person(){

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}