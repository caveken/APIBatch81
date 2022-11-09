package odev;

import base_url.AutomationExerciseBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class odevGet extends AutomationExerciseBaseUrl {
    /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void get01() {
        //1. set the url
        spec.pathParams("1","api","2","brandsList");
        //2. set the expData
        //3. set the req get the resp
        Response response=given().spec(spec).when().get("/{1}/{2}");
        //response.prettyPrint();
        //do assert
        JsonPath jsonPath=response.jsonPath();
        jsonPath.prettyPrint();
        response.then().statusCode(200)
                .contentType("text/html; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");
        List<String> brandHMList=jsonPath.getList("brands.findAll{it.brand=='H&M'}.brand");
        List<String> brandPoloList=jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        Assert.assertEquals(brandHMList.size(),brandPoloList.size());


    }
}
