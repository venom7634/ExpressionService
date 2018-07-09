package servlets;

import java.util.HashMap;
import java.util.Map;

public class JSONObjectByMR {

    HashMap<String,Object> map;
    public JSONObjectByMR(){
        this.map = new HashMap<String, Object>();
    }

    public String toString() {
        String result = "{";
        for(Map.Entry<String, Object> element : this.map.entrySet()){
            result+= "\""+element.getKey()+"\""+":"+valueString(element.getValue())+", ";
        }
        result = result.substring(0,result.length()-2);
        result+= "}";
        return result;
    }

    public void put(String key, Object value){
        this.map.put(key,value);
    }

    private String valueString(Object str){
        if(str instanceof Number){
            return str+"";
        }
        if(str instanceof String){
            return "\""+str+"\"";
        }
        if(str == null){
            return "null";
        }

        return str.toString();
    }
}
