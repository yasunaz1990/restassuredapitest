package testcases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import utility.Steps;

public class DemoTest {


    @Test
    public void assertionDemo() {




    }



    @Test
    public void practiceTest() {
        // Preparing the request.
        // Request specification

        RestAssured.baseURI = "http://3.13.86.142:3000";
        RequestSpecification reqSpec = RestAssured.given();

        // This will actually send the request
        // and gets a response back
        Steps.log("Preparing GET request to endpoint: /contacts");
        Response response = reqSpec.request(Method.GET, "/contacts");
        Steps.log("Recieved a Resposne from server");

        // This response object contains all the data sent by the server
        // we can extract
        // Status line
        // Headers
        // Body
        Steps.log(response.statusLine());
        boolean result1 = response.getStatusLine().contains("HTTP/1.1");
        boolean result2 = response.getStatusLine().contains("200");
        Steps.log("Here is the body");
        Steps.logJson(response.prettyPrint());
    }

    //@Test
    public void practiceTest2() {
        String url = "https://swapi.dev/api/people/1/";
        Response response = RestAssured.given()
                .get(url)
                .andReturn();

        response.getBody().prettyPrint();
    }

}


