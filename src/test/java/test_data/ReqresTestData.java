package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {
    public Map<String,String> expDataMethod(String name, String job){
        if (job!=null){
        Map<String,String> expDataMap=new HashMap<>();
        expDataMap.put("name",name);
        expDataMap.put("job",job);
        return expDataMap;
    }
    Map<String,String> expDataMap=new HashMap<>();
        expDataMap.put("name",name);
        return expDataMap;
    }

}
