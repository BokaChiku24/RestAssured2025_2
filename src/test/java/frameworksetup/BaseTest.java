package frameworksetup;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.ExcelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static Properties config = new Properties();
    private FileInputStream fileInputStream;
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata2.xlsx");

    @BeforeSuite
    public void setUp() throws IOException {
        fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/config.properties");
        config.load(fileInputStream);
        RestAssured.baseURI = config.getProperty("baseURI");
        RestAssured.basePath = config.getProperty("basePath");
    }

    @AfterSuite
    public void tearDown() {

    }

}
