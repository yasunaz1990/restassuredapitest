package testcases;

import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import utility.Steps;

public class ContactListTest extends ApiConfig {

    @Test
    public void client_can_get_all_contacts() {
        // Preparing the request.
        // Request specification
        RestAssured.baseURI = "http://3.13.86.142:3000";
        RequestSpecification reqSpec = RestAssured.given();
        Steps.log("Registered Endpoint URI, and preparing a requst");

        // This will actually send the request
        // and gets a response back
        Steps.log("Sending a GET requst to endopoint:  /contacts");
        Response response = reqSpec.request(Method.GET, "/contacts");
        Steps.log("Recieved a response from a server");


        // This response object contains all the data sent by the server
        // we can extract
        // Status line
        // Headers
        // Body
        Steps.log("Status line: " + response.getStatusLine());

        System.out.println(response.getHeaders());
        System.out.println("\n\n");

        System.out.println("Let's see its body:");
       // response.getBody().prettyPrint();
    }


    @Test
    public void get_all_contacts_status_line() {
        // Preparing the request.
        // Request specification
        RestAssured.baseURI = "http://3.13.86.142:3000";
        RequestSpecification reqSpec = RestAssured.given();

        // This will actually send the request
        // and gets a response back
        Response response = reqSpec.request(Method.GET, "/contacts");

        // This response object contains all the data sent by the server
        // we can extract
        // Status line
        // Headers
        // Body
        System.out.println("Let's see its status line:");
        System.out.println(response.getStatusLine());
        boolean result1 = response.getStatusLine().contains("HTTP/1.1");
        boolean result2 = response.getStatusLine().contains("200");
    }


    @Test
    public void get_all_contacts_headers() {
        // Preparing the request.
        // Request specification
        RestAssured.baseURI = "http://3.13.86.142:3000";
        RequestSpecification reqSpec = RestAssured.given();

        // This will actually send the request
        // and gets a response back
        Response response = reqSpec.request(Method.GET, "/contacts");

        // This response object contains all the data sent by the server
        // we can extract
        // Status line
        // Headers
        // Body
        System.out.println("Let's see its status line:");
        System.out.println(response.headers());
        System.out.println(response.headers().get("Content-Type"));
        System.out.println(response.headers().get("Content-Type").getValue().contains("application/json"));
    }


    @Test
    public void postRequest() {

        String payload = "{" +
                "    \"email\": \"user23@example.com\"," +
                "    \"password\": \"SuperSecretPassword123\"" +
                "}";
        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        //spec.hea
        spec.header("Content-Type", "Application/json");
        spec.body(payload);
        Response response = spec.post("/pcl/api/contacts/me");
        System.out.println( response.getStatusLine() );
        response.getBody().prettyPrint();

    }
}