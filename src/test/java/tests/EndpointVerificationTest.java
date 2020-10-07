package tests;

import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class EndpointVerificationTest extends ApiConfig {


    @Test(priority = 1)
    public void register_one_user() {

        String payload = getNewUserPayload();

        Response response = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/register");

        System.out.println("-----------TEST CASE 1 ----------------");
        response.getBody().prettyPrint();
        String token = extractData(response.getBody().asString(), "$.token");
        System.out.println("Registration token: " + token);
        setUserToken(token);
    }


    @Test(priority = 2)
    public void login_single_user() {

        String payload = getCredentialPayload();

        Response respons = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/login");

        System.out.println("-----------TEST CASE 2 ----------------");
        String sessionToken = extractData(respons.getBody().asString(), "$.token");
        setSessionToken(sessionToken);
        System.out.println("Session Token:  >> " + sessionToken);
        System.out.println(respons.getStatusLine());
        respons.getBody().prettyPrint();
    }


    @Test(priority = 3)
    public void get_logged_in_user_via_token() {

        String payload = getCredentialPayload();

        Response respons = RestAssured.given()
                .header("Authorization", getSessionToken())
                .contentType("Application/json")
                .body(payload)
                .get("/user/me");

        System.out.println("-----------TEST CASE 3 ----------------");
        System.out.println(respons.getStatusLine());
        respons.getBody().prettyPrint();
    }


    @Test(priority = 4)
    public void add_single_task() {
        String payload = "{\n" +
                "\t\"description\": \"reading book\"\n" +
                "}";

        Response respons = RestAssured.given()
                .header("Authorization", getSessionToken())
                .contentType("Application/json")
                .body(payload)
                .post("/task");

        System.out.println("-----------TEST CASE 3.5 ----------------");
        System.out.println(respons.getStatusLine());
        respons.getBody().prettyPrint();
    }


    @Test(priority = 5)
    public void logout_single_user() {

        String payload = getCredentialPayload();

        Response respons = RestAssured.given()
                .header("Authorization", getSessionToken())
                .contentType("Application/json")
                .post("/user/logout");

        System.out.println("-----------TEST CASE 4 ----------------");
        System.out.println(respons.getStatusLine());
        respons.getBody().prettyPrint();
    }


    @Test(priority = 6)
    public void update_user_profile_info() {
        //PUT update on previously extracted user ID
        System.out.println("By the way, user regist token is: " + getUserToken());
        JSONObject json = new JSONObject();
        json.put("age", 25);
        String payload = json.toString();


        Response response = RestAssured.given()
                .header("Authorization", getUserToken())
                .contentType("Application/json")
                .accept("Application/json")
                .body(payload)
                .put("/user/me");

        System.out.println("-----------TEST CASE  5 ----------------");
        response.getBody().prettyPrint();
        String responseStatusLine = response.statusLine();
        int responseStatusCode = response.statusCode();
        System.out.println(responseStatusLine);
        System.out.println(responseStatusCode);
    }


    //@Test(priority = 6)
    public void upload_user_profile_pic() {

        String picPath = System.getProperty("user.dir") + "/images/profile.png";

        Response respons = RestAssured.given()
                .header("Authorization", getUserToken())
                .contentType("multipart/form-data")
                .multiPart("avatar", picPath)
                .post("/user/me/avatar");

        System.out.println("-----------TEST CASE 6 ----------------");
        System.out.println(respons.getStatusLine());
        respons.getBody().prettyPrint();
    }



    @Test(priority = 7)
    public void delete_user() {
        Response response = RestAssured.given()
                .header("Authorization", getUserToken())
                .accept("Application/Json")
                .delete("/user/me");

        System.out.println("-----------TEST CASE 7 ----------------");
        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();
    }
}
