package odev;

import base_url.ReqresBaseUrl;
import org.junit.Test;
import test_data.ReqresTestData;

public class odevPatch extends ReqresBaseUrl {
    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void patch01() {
        //set the url
        spec.pathParams("1","users","2",2);
        // set the expData
        ReqresTestData obj=new ReqresTestData();

    }
}
