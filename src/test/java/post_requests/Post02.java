package post_requests;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post02 extends RestfulBaseUrl {
    /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/


    @Test
    public void post02() {

        //set the url
        spec.pathParam("first", "booking");

        //set the expected data
        RestfulTestData obj = new RestfulTestData();
        Map<String, String> bookingdatesMap = obj.bookingdatesMethod("2021-09-09", "2021-09-21");
        Map<String, Object> expectedDataMap = obj.expectedDataMethod("John", "Doe", 11111, true, bookingdatesMap);

        //send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");

        //Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedDataMap.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), ((Map)actualDataMap.get("booking")).get("depositpaid"));
        Assert.assertEquals(bookingdatesMap.get("checkin"), ((Map)((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(bookingdatesMap.get("checkout"), ((Map)((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));
    }
}
