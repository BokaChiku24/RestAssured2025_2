package api;

import frameworksetup.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CreateCustomerAPI extends BaseTest {

    public static Response sendPostRequestToCreateCustomerAPIWithValidAPI(Hashtable<String,String> data){
        return given().auth().basic(config.getProperty("validSecreteKey"), "")
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerAPIEndPoint"));
    }

    public static Response sendPostRequestToCreateCustomerAPIWithInValidAPI(Hashtable<String,String> data){
        return given().auth().basic(config.getProperty("validSecreteKey"), "")
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerAPIEndPoint"));
    }
}
