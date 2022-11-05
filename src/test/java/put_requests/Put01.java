package put_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Put01 extends JsonplaceholderBaseUrl {

    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 21,
                   "title": "Wash the dishes",
                   "completed": false
                  }
 */


    @Test
    public void put01() {
        //Set the url
        spec.pathParams("1", "todos", "2", "198");

        //Set theexpected data
        JsonplaceholderTestData obj = new JsonplaceholderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(21, "Wash the dishes", false);

        //send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{1}/{2}");

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
    }
}
