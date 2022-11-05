package post_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post01 extends JsonplaceholderBaseUrl {

    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2){
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
                         }
   When
       I send POST Request to the Url
   Then
       Status code is 201
   And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/

    @Test
    public void post01() {
        //Set the url
        spec.pathParam("1", "todos");

        //set the expected data
        JsonplaceholderTestData obj = new JsonplaceholderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(55, "Tidy your room", false);

        //Send the request and get response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        //do assertion

        Map<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(actualData.get("completed"), expectedData.get("completed"));
        Assert.assertEquals(actualData.get("title"), expectedData.get("title"));
        Assert.assertEquals(actualData.get("userId"), expectedData.get("userId"));

    }
}
