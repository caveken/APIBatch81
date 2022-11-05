package get_request;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetOdev extends AutomationExerciseBaseUrl {

    /*
 Given
     https://automationexercise.com/api/productsList
 When
     User sends a GET Request to the url
 Then
     HTTP Status Code should be 200
 And
     Content Type should be "text/html; charset=utf-8"
 And
     Status Line should be HTTP/1.1 200 OK
 And
      There must be 12 Women, 9 Men, 13 Kids usertype in products
   */


    //Set the Url
    //Set The Expected Data
    //Send The Request and Get The Response
    //Do Assertion


    @Test
    public void getOdev() {

        //Set the Url
        spec.pathParams("first", "api", "second", "productsList");


        //Set The Expected Data
        int womenExpectedNum=12;
        int menExpectedNum=9;
        int kidsExpectedNum=13;


        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();

        List<String>womenList=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        List<String>menList=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String>kidsList=jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");


        //Do Assertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(200, response.getStatusCode());
        softAssert.assertEquals(response.contentType(), "text/html; charset=utf-8");
        softAssert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
        softAssert.assertEquals(womenList.size(), womenExpectedNum);
        softAssert.assertEquals(menList.size(), menExpectedNum);
        softAssert.assertEquals(kidsList.size(), kidsExpectedNum);

        softAssert.assertAll();

        System.out.println("Women: "+womenList.size()+"\nMen: "+menList.size()+"\nKids: "+kidsList.size());
    }
}
