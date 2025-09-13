package test;

import frameworksetup.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateCustomerTest extends BaseTest {
    @Test
    public void validateAPIWithValidSecretKey() {
        Response response = given().auth().basic(config.getProperty("validSecreteKey"), "")
                .get(config.getProperty("chargesAPIEndPoint"));
        // response.prettyPrint();
        // System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test(dependsOnMethods = {"validateAPIWithValidSecretKey"}, dataProvider = "getData")
    public void validateCreateCustomer(String name, String email, String description) {
        Response response = given().auth().basic(config.getProperty("validSecreteKey"), "")
                .formParam("email", email)
                .formParam("description", description)
                .post(config.getProperty("customerAPIEndPoint"));
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test(dependsOnMethods = {"validateCreateCustomer"})
    public void validateAPIWithInValidSecretKey() {
        Response response = given().auth().basic(config.getProperty("invalidSecreteKey"), "")
                .get(config.getProperty("chargesAPIEndPoint"));
        // response.prettyPrint();
        // System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 401);
    }

    @DataProvider
    public Object[][] getData(){
        String sheetName = "testdata";
        int row = excel.getRowCount(sheetName);
        int col = excel.getColumnCount(sheetName);
        Object[][] data = new Object[row-1][col];
        for(int rowNum = 2; rowNum<=row; rowNum++){
            for(int colNum=0; colNum<col; colNum++){
               data[rowNum-2][colNum]= excel.getCellData(sheetName, colNum,rowNum);
            }
        }
        return data;
    }
}
