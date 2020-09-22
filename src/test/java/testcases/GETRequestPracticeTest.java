package testcases;

import com.jayway.jsonpath.JsonPath;
import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GETRequestPracticeTest extends ApiConfig {

    @Test
    public void get_request_practice1() {
        // 1. Register a baser URI where the request will be sent
        RestAssured.baseURI = "https://reqres.in";

        // 2. Specify the request that will be send
        RequestSpecification spec = RestAssured.given();
        Response response = spec.get("/api/users/2");


        // 3. Using the rseponse object, let's extract each part
        //    of the response ( Status line, Headers, Body )

        // Status line
        System.out.println(response.statusLine());
        System.out.println(response.statusCode());

        // Headers
        Headers headers = response.headers();
        String entireHeaders = headers.toString();
        System.out.println(entireHeaders);
        String contentType = headers.getValue("Content-Type");
        String server = headers.getValue("Server");
        String date = headers.getValue("Date");
        System.out.println(contentType);
        System.out.println(server);
        System.out.println(date);

        // Body
        String payload = response.getBody().asString();
        // to extract a specific data from payload
        String emailVal = JsonPath.read(payload, "$.data.email");
        String firstNameVal = JsonPath.read(payload, "$.data.first_name");
        String lastNameVal = JsonPath.read(payload, "$.data.last_name");
        String adCompVal = JsonPath.read(payload, "$.ad.company");

        System.out.println("Email: " + emailVal);
        System.out.println("First Name: " + firstNameVal);
        System.out.println("Last Name : " + lastNameVal);
        System.out.println("Ad Compnay: " + adCompVal);
    }
}

/*
$.data.email
$.data.first_name
$.data.last_name
$.ad.company
*/