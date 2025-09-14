package api;

import frameworksetup.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class DeleteCustomerAPI extends BaseTest {
    public static Response sendDeleteRequestToCreateCustomerAPIWithValidID(Hashtable<String,String> data){
        return given().auth().basic(config.getProperty("validSecreteKey"),"")
                .delete(config.getProperty("customerAPIEndPoint")+"/"+data.get("id"));

    }
}
