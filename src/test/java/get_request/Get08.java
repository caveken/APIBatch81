package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {

    //De-Serialization : json datayı Java objesine cevirme
    //Serialization : Java objesini json formatına cevirme.
    //De-Serialization : Iki turlu yapacagiz.
    //1. tur => Gson : google tarafından uretilmiştir
    //2. tur=> Object Mapper : Daha popüler


    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }
*/

    //Set the Url
    //Set The Expected Data
    //Send The Request and Get The Response
    //Do Assertion

    @Test
    public void get08() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 2);

        //Set The Expected Data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        System.out.println(expectedData);

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); //De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userID"), actualData.get("userID"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());


    }


    //dinamik yontem
    @Test
    public void get08b() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 2);

        //Set The Expected Data
        JsonplaceholderTestData objJsonPlaceHolder=new JsonplaceholderTestData();
        Map<String, Object> expectedData =objJsonPlaceHolder.expectedDataMethod(1,"quis ut nam facilis et officia qui", false);

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); //De-Serialization
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userID"), actualData.get("userID"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());

    }
}
