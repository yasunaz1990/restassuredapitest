package tests;

import commons.ApiTestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.Steps;

public class EndpointVerificationTest extends ApiTestConfig {



    @Test(description = "[POST] Client can register a user")
    public void PBTW_237() {

        String payload = generateNewUserPayload();

        Steps.log("Send POST Request ");
            RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
            Response response = RestAssured.given()
                    .contentType("Application/json")
                    .body(payload)
                    .post("/user/register");

        Steps.log("With the following payload:");
        Steps.logJson(payload);
        Steps.log("We got the response");

        Steps.log("Verify that response status code is 201 Created");
            SoftAssert softAssert = new SoftAssert();
            int actualStatusCode = response.statusCode();
            softAssert.assertEquals(actualStatusCode, 201);

        Steps.log("Response body contains following payload:");
        Steps.logJson(response.getBody().asString());
        Steps.log("Verify the response body contains id for new user");
            String responsePayload = response.getBody().asString();
            setToken( extractData(responsePayload, "$.token") );
            String id = extractData( responsePayload, "$.user._id");
            softAssert.assertNotNull(id);
            softAssert.assertAll();
    }


    @Test(description = "[POST] Client can login a registered user")
    public void PBTW_240() {
        String payload = getCredentialPayload();

        Steps.log("Send POST login request");
            RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
            Response response = RestAssured.given()
                    .contentType("Application/json")
                    .body(payload)
                    .post("/user/login");

        Steps.log("Request payload:");
        Steps.logJson(payload);

        Steps.log("Verify that response status code is 200 OK");
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(response.statusCode(), 200);
        Steps.log("Verify the response body contains user session token");
            String responsePayload = response.getBody().asString();

        Steps.logJson(responsePayload);
            String sessionToken  = extractData(responsePayload, "$.token");
            softAssert.assertNotNull(sessionToken);
            softAssert.assertAll();
    }
}
