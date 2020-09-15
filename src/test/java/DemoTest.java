
import groovy.json.JsonOutput;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    public void practiceTest() {
       String url = "https://reqres.in/api/users/2";
       RestAssured
           .given()
               .get(url)
               .andReturn()
               .getBody()
               .prettyPrint();
    }

    @Test
    public void practiceTest2() {
        String url = "https://swapi.dev/api/people/1/";
        Response response = RestAssured.given()
                .get(url)
                .andReturn();

        response.getBody().prettyPrint();
    }
}


