package odev;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class odevPost extends ReqresBaseUrl {
    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "64",
                                                "createdAt": "2022-11-09T16:17:21.287Z"
                                              }
*/

    @Test
    public void post01() {
        //set the url
        spec.pathParams("1","users");
        //set the expData
        ReqresTestData obj=new ReqresTestData();
        Map<String ,String> expData=obj.expDataMethod("morpheus","leader");
        //send the req get the resp
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expData).when().post("/{1}");
        response.prettyPrint();

        //do assertion
        Map<String,String> actData=response.as(HashMap.class);
        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(expData.get("name"),actData.get("name"));
        Assert.assertEquals(expData.get("job"),actData.get("job"));
    }

    @Test
    public void post02() {
        //set the url
        spec.pathParams("1","users");
        //set the expData
        ReqresPojo expData=new ReqresPojo("morpheus","leader");
        //send the req get the resp
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expData).post("/{1}");
        // do assertion
        ReqresPojo actData=response.as(ReqresPojo.class);
        Assert.assertEquals(201,response.statusCode());
        Assert.assertEquals(expData.getJob(),actData.getJob());
        Assert.assertEquals(expData.getName(),actData.getName());
    }
}
