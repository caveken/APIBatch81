package get_request;

import base_url.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;

public class Get13Pojo extends GorestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void get13Pojo() {

        //Set the url
        spec.pathParams("first", "users", "second", 2508);

        //Set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2508, "Sharmila Deshpande VM", "deshpande_sharmila_vm@becker.name", "female", "active");
        GoRestPojo expectedData = new GoRestPojo(null, goRestDataPojo);

        //Send Request and get response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //Do Assertion
        GoRestPojo actualData=response.as(GoRestPojo.class);

        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals(expectedData.getMeta(), actualData.getMeta());
        Assert.assertEquals(goRestDataPojo.getId(), actualData.getData().getId());
        Assert.assertEquals(goRestDataPojo.getName(), actualData.getData().getName());
        Assert.assertEquals(goRestDataPojo.getEmail(), actualData.getData().getEmail());
        Assert.assertEquals(goRestDataPojo.getGender(), actualData.getData().getGender());
        Assert.assertEquals(goRestDataPojo.getStatus(), actualData.getData().getStatus());
    }
}
