package get_request;

import base_url.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class Get11 extends GorestBaseUrl {
/*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
    And
        The female users are less than or equals to male users
 */

    //Set the Url
    //Set The Expected Data
    //Send The Request and Get The Response
    //Do Assertion

    @Test
    public void get11() {

        //Set the Url
        spec.pathParams("first", "users");

        //Set The Expected Data
        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion

        //The value of "pagination limit" is 10
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Pres. Amarnath Dhawan","Sujata Chaturvedi","Navin Panicker"));


        //kadın ve erkek sayısını karsılastırma
        //1.Yol
        List<String>genderList=response.jsonPath().getList("data.gender");
        int female=0;
        for (String w:genderList
             ) {
            if (w.equalsIgnoreCase("female")){
                female++;
            }
        }

        Assert.assertTrue(female<=genderList.size()-female);

        //2. Yol
        List<String>femaleList=response.jsonPath().getList("data.findAll{it.gender=='female'}.gender");
        List<String>maleList=response.jsonPath().getList("data.findAll{it.gender=='male'}.gender");
        //System.out.println("femaleList.size() = " + femaleList.size());
        //System.out.println("maleList.size() = " + maleList.size());
        //System.out.println(femaleList);
        Assert.assertFalse(femaleList.size()<=maleList.size());

    }
}
