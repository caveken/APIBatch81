package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get04b extends RestfulBaseUrl {

     /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

 */

    @Test
    public void get04() {

        //Set the Url

        spec.pathParam("first", "booking").queryParams("firstname", "Almedin", "lastname", "Alikadic");

        //Set The Expected Data

        //Send The Request and Get The Response

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion


        List<String> respList = new ArrayList<>(Collections.singleton(response.asString()));

        String[] bookingArr=respList.get(0).split("},");
        System.out.println("kayit sayisi: "+bookingArr.length);


        JsonPath jsonPath = response.jsonPath();
        System.out.println("jsonPath yontemi ile kayıt sayısı: "+jsonPath.getList("bookingid").size());




        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));
    }
}
