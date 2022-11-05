package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get09 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
 */

    //Set the Url
    //Set The Expected Data
    //Send The Request and Get The Response
    //Do Assertion

    @Test
    public void get09() {

        //Set the Url
        spec.pathParams("first", "booking", "second", 91);

        //Set The Expected Data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2013-02-23");
        bookingdatesMap.put("checkout", "2014-10-23");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println("expectedData = " + expectedData);

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //Do Assertion

        Map<String, Object> actualData=response.as(HashMap.class); //De-Serialization yaptık burada

        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData, actualData);//topluca karşılastırma


        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname")); //tek tek karşılaştırma
        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        Assert.assertEquals(expectedData.get("bookingdates"), actualData.get("bookingdates"));//

        Assert.assertEquals(bookingdatesMap.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(bookingdatesMap.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
        //yukarıdaki iki satırda Key-Value ikilileri String-Object şeklinde olduğundan,
        // Bookingdata value kısmını casting ile Map yaptık.

        Assert.assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

    }
}
