package testcases;

import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ToDoListTest extends ApiConfig {


    @Test
    public void register_single_user() {
        String payload = getPayload("todoNewUser");
        RestAssured.baseURI = todoList_base_uri;
        Response respons = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/register");
        System.out.println(respons.statusCode());
        System.out.println(respons.getBody().asString());
    }

    @Test
    public void login_single_user() {

        String payload = "{" +
                "\"email\": \"nmuhtar@gmail.com\"," +
                "\"password\": \"12345678\"" +
                "}";

        RestAssured.baseURI = todoList_base_uri;
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
