package WebServer2;

import java.util.HashSet;
import java.util.Set;

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
