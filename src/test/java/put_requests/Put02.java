package put_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Put02 extends DummyRestApiBaseUrl {
    /*Given
    URL: https://dummy.restapiexample.com/api/v1/update/21
When
    PUT Request
      Request body: {
                    "employee_name": "Ali Can",
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                 }
Then
    i) Status code is 200
And
    ii) Response body should be like the following
                {
                    "status": "success",
                    "data": {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                } */

    @Test
    public void put02() {
       spec.pathParams("1","update","2",21);
        DummyRestApiDataPojo dummyRestApiDataPojo=new DummyRestApiDataPojo("Ali Can",111111,23,"Perfect image");
        DummyRestApiPojo expData=new DummyRestApiPojo("success",dummyRestApiDataPojo,"Successfully! Record has been updated.");
        System.out.println("expData = " + expData);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(dummyRestApiDataPojo).when().put("/{1}/{2}");
        response.prettyPrint();

        DummyRestApiResponseBodyPojo actData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actData = " + actData);
    }
}
