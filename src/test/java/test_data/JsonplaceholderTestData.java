package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonplaceholderTestData {
    public Map<String, Object> expectedDataMethod(Integer userId, String title, Boolean completed){

        Map<String, Object> expectedDataMap = new HashMap<>();
        if (userId!=null){
           expectedDataMap.put("userID", userId);
        }

        if (title!=null){
            expectedDataMap.put("title", title);
        }

        if (completed!=null){
            expectedDataMap.put("completed", completed);
        }

        return expectedDataMap;
    }

    public String expectedDataInString (int userId, String title, boolean completed) {//Dinamik expected data methodu: Json data yı string bir container olarak return ediyor.

        String expectedData= "{\n" +
                "                 \"userId\": "+userId+",\n" +
                "                 \"title\": \""+title+"\",\n" +
                "                 \"completed\": "+completed+"\n" +
                "               }";

        return expectedData;

    }

}
