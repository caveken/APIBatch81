package post_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonplaceholderTestData;

import static io.restassured.RestAssured.given;

public class Post03Pojo extends JsonplaceholderBaseUrl {
/*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
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
    public void post03pojo() {

        //Set the Url
        spec.pathParams("first", "todos");

        //Set the expected Data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55, "Tidy your room",false);

        //Set the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        //Do Assertion
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);

        System.out.println("actualData = " + actualData);

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals(expectedData.getUserId(), actualData.getUserId());
        Assert.assertEquals(expectedData.getCompleted(), actualData.getCompleted());
        Assert.assertEquals(expectedData.getTitle(), actualData.getTitle());


    }
}
