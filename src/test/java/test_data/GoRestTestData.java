package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {


    public Map<String, String> dataKeyMap(String name,String email,String gender,String status){
        Map<String, String> dataKeyMp=new HashMap<>();

        dataKeyMp.put("name", name);
        dataKeyMp.put("email", email);
        dataKeyMp.put("gender", gender);
        dataKeyMp.put("status", status);

        return dataKeyMp;
    }



    public Map<String, Object> expectedDataMethod(Object meta, Map <String,String> data){

        Map<String, Object>expectedDt = new HashMap<>();
        expectedDt.put("meta",meta);
        expectedDt.put("data", data);

        return expectedDt;
    }
}
