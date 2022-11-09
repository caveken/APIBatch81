package post_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import base_url.DummyRestApiBaseUrl;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {
    /*
      URL: https://dummy.restapiexample.com/api/v1/create
      HTTP Request Method: POST Request
      Request body:
                    {
                       "employee_name": "Tom Hanks",
                       "employee_salary": 111111,
                       "employee_age": 23,
                       "profile_image": "Perfect image",
                       "id": 4891
                    }

      Test Case: Type by using Gherkin Language
      Assert:

               i) Status code is 200
               ii) Response body should be like the following
                   {
                       "status": "success",
                       "data": {
                           "employee_name": "Tom Hanks",
                           "employee_salary": 111111,
                           "employee_age": 23,
                           "profile_image": "Perfect image",
                           "id": 4891
                       },
                       "message": "Successfully! Record has been added."
                   }
    */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/create

    And    {
            "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891
          }
     When
        User sends POST request

     Then
        Status code is 200
     And
        Response body should be like the following
        {
            "status": "success",
            "data": {
                "employee_name": "Tom Hanks",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image",
                "id": 4891
            },
            "message": "Successfully! Record has been added."
        }


     */
    @Test
    public void post06(){
        //set the url
        spec.pathParams("1","create");
        //set the exp Data
        DummyRestApiDataPojo dummyRestApiDataPojo=new DummyRestApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyRestApiPojo expData=new DummyRestApiPojo("success",dummyRestApiDataPojo,"Successfully! Record has been added.");
        System.out.println("expData = " + expData);

        //set the req get the resp
        Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyRestApiDataPojo).when().post("/{1}");
        response.prettyPrint();

        //do assertion
        DummyRestApiPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expData.getData().getEmployee_name(),actData.getData().getEmployee_name());
        assertEquals(expData.getData().getEmployee_salary(),actData.getData().getEmployee_salary());
        assertEquals(expData.getData().getEmployee_age(),actData.getData().getEmployee_age());
        assertEquals(expData.getData().getProfile_image(),actData.getData().getProfile_image());





    }




}