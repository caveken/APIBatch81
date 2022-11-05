package patch_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class Patch01 extends JsonplaceholderBaseUrl {

    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "title": "Wash the dishes"
           }
    When
I send PATCH Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 10,
                   "title": "Wash the dishes",
                   "completed": true,
                   "id": 198
                   }
 */

    @Test
    public void patch01() {
        //set the url
        spec.pathParams("first", "todos", "second", "198");

        //Set the expected data
        JsonplaceholderTestData obj = new JsonplaceholderTestData();
        Map<String, Object> expectedDataMap = obj.expectedDataMethod(null, "Wash the dishes", null);
        System.out.println("expectedDataMap = " + expectedDataMap);

        //Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object>actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));

    }
}
