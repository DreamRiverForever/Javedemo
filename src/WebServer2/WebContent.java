package WebServer2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContent {
    private List<Enter> enters = null;
    private List<Mapping> mappings = null;

    //key-->servlet-name  value-->servlet-class
    private Map<String,String> entersMap = new HashMap<>();

    //key-->url-patten  value-->servlet-name
    private Map<String,String> mappingsMap = new HashMap<>();

    public WebContent(List<Enter> enters, List<Mapping> mappings) {
        this.enters = enters;
        this.mappings = mappings;
        for (Enter enter:enters
             ) {//将List转化为Map
            entersMap.put(enter.getName(),enter.getClz());

        }
        for (Mapping mapping:mappings
        ) {
            for (String patten:mapping.getPatten()
                 ) {
                mappingsMap.put(patten,mapping.getName());
            }
        }
    }

    //通过url路径找到相应的类，在通过反射
    public String getClz(String patten){
        String name = mappingsMap.get(patten);
        return entersMap.get(name);
    }
}
