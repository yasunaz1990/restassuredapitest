package testcases;

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

public class PublicContactListTest {

    @Test
    public void register_single_user() throws IOException {
        // Test Data
        String endpoint = "/pcl/auth/register";
//        String payload = "{" +
//                "    \"email\": \"nijatexample11232@example.com\"," +
//                "    \"password\": \"SuperSecretPassword123\"" +
//                "}";

        String filePath = System.getProperty("user.dir") + "/src/test/resources/payloads/newUser.txt";
        String payLoad = read(filePath);

        // Test Step
        Steps.log("About to send POST register a user request");
        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json; charset=utf-8");
        spec.header("Accept", "Application/json");
        spec.body(payLoad.trim());

        Steps.logJson(payLoad.trim());

        // send the request and store the response
        Steps.log("Sending the request and received the response");
        Response response = spec.post(endpoint);

        Steps.log("Status Line: " + response.getStatusLine());
        Steps.log("Response body: ");
        Steps.logJson(response.getBody().prettyPrint());

        // Test Assertion
        Assert.assertTrue(response.statusCode() == 201);
    }

    @Test
    public void login_single_user() throws IOException {
        // Test Data
        String endpoint = "/pcl/auth/login";
        String filePath = System.getProperty("user.dir") + "/src/test/resources/payloads/newUser.txt";
        String payLoad = read(filePath);

        // Test Step
        Steps.log("About to send POST login a user request");
        RestAssured.baseURI = "https://craftplacer.trexion.com";
        RequestSpecification spec = RestAssured.given();
        spec.header("Content-Type", "Application/json; charset=utf-8");
        spec.body(payLoad.trim());

        Steps.logJson(payLoad.trim());
        // send the request and store the response
        Steps.log("Sending the request and received the response");
        Response response = spec.post(endpoint);

        Steps.log("Status Line: " + response.getStatusLine());
        Steps.log("Response body: ");
        Steps.logJson(response.getBody().prettyPrint());

        // Test Assertion
        Assert.assertTrue(response.statusCode() == 200);
    }


    public String read(String path) {
        String finalText = null;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            finalText = sb.toString();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalText;
    }


    public void pause(int second) {
        try{
            Thread.sleep(second * 1000);
        }catch (Exception e) {

        }
    }

}
