package get_request;

import base_url.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get10 extends GorestBaseUrl {

    /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}
*/


    //Set the Url
    //Set The Expected Data
    //Send The Request and Get The Response
    //Do Assertion


    @Test
    public void get10() {

        //Set the Url
        spec.pathParams("first", "users", "second", 2986);

        //Set The Expected Data
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id", 2986);
        dataMap.put("name", "Navin Talwar");
        dataMap.put("email", "navin_talwar@mclaughlin.name");
        dataMap.put("gender", "male");
        dataMap.put("status", "inactive");

        Map<String, Object> expectedData= new HashMap<>();
        expectedData.put("meta", null);
        expectedData.put("data", dataMap);


        //Send The Request and Get The Response
       Response response = given().spec(spec).when().get("/{first}/{second}");

        //Do Assertion

        Map<String, Object>actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("meta"), actualData.get("meta"));
        Assert.assertEquals(dataMap.get("id"), ((Map)actualData.get("data")).get("id"));
        Assert.assertEquals(dataMap.get("name"), ((Map)actualData.get("data")).get("name"));
        Assert.assertEquals(dataMap.get("email"), ((Map)actualData.get("data")).get("email"));
        Assert.assertEquals(dataMap.get("gender"), ((Map)actualData.get("data")).get("gender"));
        Assert.assertEquals(dataMap.get("status"), ((Map)actualData.get("data")).get("status"));

    }

    @Test
    public void get10b() {

        //Set the Url
        spec.pathParams("first", "users", "second", 2986);

        //Set The Expected Data
        GoRestTestData goRestTestData = new GoRestTestData();

        Map<String, String> dataMap=goRestTestData.dataKeyMap("Navin Talwar", "navin_talwar@mclaughlin.name", "male", "inactive");
        Map<String, Object> expectedData=goRestTestData.expectedDataMethod(null, dataMap);


        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //Do Assertion

        Map<String, Object>actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("meta"), actualData.get("meta"));

        Assert.assertEquals(dataMap.get("name"), ((Map)actualData.get("data")).get("name"));
        Assert.assertEquals(dataMap.get("email"), ((Map)actualData.get("data")).get("email"));
        Assert.assertEquals(dataMap.get("gender"), ((Map)actualData.get("data")).get("gender"));
        Assert.assertEquals(dataMap.get("status"), ((Map)actualData.get("data")).get("status"));

        Assert.assertEquals(200,response.statusCode());

    }
}
