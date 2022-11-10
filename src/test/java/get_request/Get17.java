package get_request;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */
    /*{
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
}*/

    @Test
    public void get17() {
        spec.pathParams("1","employee","2",1);
        DummyRestApiDataPojo dummyRestApiDataPojo=new DummyRestApiDataPojo("Tiger Nixon",320800,61,"");
        DummyRestApiResponseBodyPojo expData=new DummyRestApiResponseBodyPojo("success",dummyRestApiDataPojo,"Successfully! Record has been fetched.");
        System.out.println("expData = " + expData);

        Response response=given().spec(spec).when().get("/{1}/{2}");
        response.prettyPrint();

        DummyRestApiResponseBodyPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actData = " + actData);

        assertEquals(200,response.statusCode());
        assertEquals(expData.getStatus(),actData.getStatus());
        assertEquals(expData.getData().getEmployee_name(),actData.getData().getEmployee_name());
        assertEquals(expData.getData().getEmployee_salary(),actData.getData().getEmployee_salary());
        assertEquals(expData.getData().getEmployee_age(),actData.getData().getEmployee_age());
        assertEquals(expData.getData().getProfile_image(),actData.getData().getProfile_image());


    }
}
