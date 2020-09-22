package testcases;


import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Steps;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
;


public class PublicContactListTest extends ApiConfig {

    @Test
    public void register_single_user() {

        String payload = "{" +
                "    \"email\": \"nijat9987@example.com\"," +
                "    \"password\": \"SuperSecretPassword123\"" +
                "}";

        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);

        Response response = spec.post("/pcl/auth/register");

        System.out.println( response.getStatusLine() );

    }


    @Test
    public void register_single_user_file_payload() {

        String path = getPayloadPath("newUser");
        String payload = read(path).trim();

        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);
        System.out.println("PYALOAD: >" + payload);

        Response response = spec.post("/pcl/auth/register");
        System.out.println( response.getStatusLine() );
    }


    @Test
    public void login_single_user() {
//        String payload = "{" +
//                "    \"email\": \"nijat9987@example.com\"," +
//                "    \"password\": \"SuperSecretPassword123\"" +
//                "}";

        String path = getPayloadPath("newUser");
        String payload = read(path).trim();

        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);

        Response response = spec.post("/pcl/auth/login");

        System.out.println(response.getStatusLine());
        String sessionToken = response.getBody().asString();
        System.out.println(response.getBody().asString());

    }


    @Test
    public void logout_single_user() {

        // 1. Login the user
        String path = getPayloadPath("newUser");
        String payload = read(path).trim();

        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json");
        spec.body(payload);
        Response response = spec.post("/pcl/auth/login");
        String sessionToken = response.getBody().asString();

        // 2. Logout the user
        RestAssured.baseURI = base_uri;
        response = RestAssured.given()
                .header("Authorization", sessionToken)
                .get("/pcl/auth/logout");

        System.out.println("Logged out a user: > " + response.getStatusLine());
    }



    @Test
    public void get_all_contacts() {

        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        Response response = spec.accept("Application/json")
                                .get("/pcl/api/contacts");
        System.out.println("Status Line> " + response.getStatusLine());
        System.out.println("Response Body> \n" + response.getBody().prettyPrint());

    }

    @Test
    public void get_perticular_contact() {
        RestAssured.baseURI = base_uri;
        RequestSpecification spec = RestAssured.given();
        Response response = spec.accept("Application/json")
                .get("/pcl/api/contacts/2");
        System.out.println("Status Line> " + response.getStatusLine());
        System.out.println("Response Body> \n" + response.getBody().asString());
    }
}
