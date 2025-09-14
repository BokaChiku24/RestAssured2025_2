package test;

import api.DeleteCustomerAPI;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataUtil;
import utilities.TestUtil;

import java.util.Hashtable;

public class DeleteCustomerTest {

    @Test(dataProviderClass = DataUtil.class,dataProvider = "data")
    public void deleteCustomer(Hashtable<String,String> data){
        Response response = DeleteCustomerAPI.sendDeleteRequestToCreateCustomerAPIWithValidID(data);
        response.prettyPrint();
        String actualId = response.jsonPath().get("id").toString();
        System.out.println("Getting id from json path" + actualId);
        Assert.assertEquals(actualId, data.get("id"),"ID not matching");

        JSONObject object = new JSONObject(response.asString());
        System.out.println(object.has("id"));
        Assert.assertTrue(object.has("id"),"ID Key is not present in the response");

        actualId = TestUtil.getJsonKeyValue(response.asString(),"id");
        System.out.println(actualId);
        System.out.println(TestUtil.jsonHasKey(response.asString(),"id"));
        System.out.println(TestUtil.jsonHasKey(response.asString(),"deleted"));
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
