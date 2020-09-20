package testcase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class ContactListTest {

    @Test
    public void client_can_get_all_contacts() {
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
        System.out.println("\n\n");

        System.out.println("Let's see its headers:");
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

    /*
X-Powered-By=Express
Content-Type=application/json; charset=utf-8
Content-Length=1063534
ETag=W/"103a6e-a7ZZMZS6xiVmT8G8f5lz9tsrU9A"
Date=Tue, 15 Sep 2020 23:35:56 GMT
Connection=keep-alive


     */


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