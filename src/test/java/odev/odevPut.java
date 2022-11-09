package odev;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RedirectSpecification;
import org.junit.Assert;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class odevPut extends ReqresBaseUrl {
    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.
/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/

    @Test
    public void put01() {

    //set the url
    spec.pathParams("1","users","2",2);
    //set the expData
        ReqresTestData obj=new ReqresTestData();
        Map<String ,String > expData=obj.expDataMethod("morpheus","zion president");
        //send req get the resp
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expData).put("/{1}/{2}");
        response.prettyPrint();
        //do assertion
        Map<String,String> actData=response.as(HashMap.class);
        assertEquals(response.statusCode(),200);
        assertEquals(actData.get("name"),expData.get("name"));
        assertEquals(actData.get("job"),expData.get("job"));
}


    @Test
    public void put02() {

        //set the url
        spec.pathParams("1","users","2",2);
        //set the expData
        ReqresPojo expData=new ReqresPojo("morpheus","zion president");
        //send the req get the resp
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expData).put("/{1}/{2}");
        //do assertion
        ReqresPojo actData=response.as(ReqresPojo.class);
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expData.getName(),actData.getName());
        Assert.assertEquals(expData.getJob(),actData.getJob());







    }
}


