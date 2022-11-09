package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
                "firstname": "Guoqiang",
                "lastname": "Morante Briones",
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
    public void get15() {
        //set the url
        spec.pathParams("1", "booking", "2", 22);

        //set the expData
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expData = new BookingPojo("Guoqiang", "Morante Briones", 111, true, bookingDatesPojo, "Breakfast");

        //send the req get the res
        Response response = given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        //do assertion
        BookingPojo actData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);

        //soft assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actData.getFirstname(), expData.getFirstname());
        softAssert.assertEquals(actData.getLastname(), expData.getLastname());
        softAssert.assertEquals(actData.getTotalprice(), expData.getTotalprice());
        softAssert.assertEquals(actData.getDepositpaid(),expData.getDepositpaid(),"depositpaid uyusmadi");
        softAssert.assertEquals(bookingDatesPojo.getCheckin(), actData.getBookingdates().getCheckin());
        softAssert.assertEquals(bookingDatesPojo.getCheckout(), actData.getBookingdates().getCheckout());
        softAssert.assertAll();


        //hard assertion
        assertEquals(200, response.statusCode());
        assertEquals(expData.getFirstname(), actData.getFirstname());
        assertEquals(expData.getLastname(), actData.getLastname());
        assertEquals(expData.getTotalprice(), actData.getTotalprice());
        assertEquals(expData.getDepositpaid(), actData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actData.getBookingdates().getCheckout());



    }
}
