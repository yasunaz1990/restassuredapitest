package testcases;

import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class EndpointVerificationTest extends ApiConfig {


    @Test
    public void PBTW_T227() {

        JSONObject json = new JSONObject();
        json.put("name", "Nijat Muhtar");
        json.put("email", "nmuhtar23sdfasddadf23@gmail.com");
        json.put("password", "1232323123!");
        json.put("age", new Integer(20));
        String payload = json.toString();


        Response response = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/register");

        response.getBody().prettyPrint();
        setUserToken(extractData(response.getBody().asString(), "$.token"));
        System.out.println("here we got: " +  getUserToken());
    }

    @Test
    public void login_single_user() {

        String payload = "{" +
                "\"email\": \"nmuhtar@gmail.com\"," +
                "\"password\": \"12345678\"" +
                "}";

        Response respons = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/login");

        //System.out.println(respons.statusCode());
        //respons.getBody().prettyPrint();
        String sessionToken = extractData(respons.getBody().asString(), "$.token");
        System.out.println("Session Token:  >> " + sessionToken);
    }


    @Test
    public void test_case() {
        //PUT update on previously extracted user ID
        String updatePayload = getPayload("updateSingleContact");
        String endpoint = "/contacts/";
        System.out.println(endpoint);
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(updatePayload)
                .put(endpoint);

        response.getBody().prettyPrint();
        String responseStatusLine = response.statusLine();
        int responseStatusCode = response.statusCode();
        System.out.println(responseStatusLine);
        System.out.println(responseStatusCode);
    }

}
