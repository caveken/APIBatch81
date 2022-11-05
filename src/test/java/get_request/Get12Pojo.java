package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;

public class Get12Pojo extends RestfulBaseUrl {

    /*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like:
                        {
                    "firstname": "Dane",
                    "lastname": "Combs",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
}
  */

    @Test
    public void get12Pojo() {

        //Set the url
        spec.pathParams("first", "booking", "second", 18);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        System.out.println(bookingDatesPojo);
        BookingPojo expectedData = new BookingPojo("Dane", "Combs", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        Assert.assertEquals(expectedData.getLastname(), actualData.getLastname());
        Assert.assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        Assert.assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        Assert.assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        //1. yol
        Assert.assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        Assert.assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

        //2. yol == tavsiye edilen
        Assert.assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        Assert.assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());

    }
}
