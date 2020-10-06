package commons;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.FileReader;

public class ApiConfig {

    private  String userToken;
    private  String sessionToken;
    private  String userEamil;
    private  String userPass;


    public void setUserToken(String newToken) {
        userToken = newToken;
    }

    public String getUserToken() {
       String authoToken = "Bearer " + userToken;
        return authoToken;
    }

    public void setSessionToken(String newToken) {
        sessionToken = newToken;
    }

    public String getSessionToken() {
        String authoToken = "Bearer " + sessionToken;
        return authoToken;
    }

    // ============ PAYLOAD ====================//

    public String getNewUserPayload() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        userEamil = faker.internet().emailAddress();
        userPass = "Pass123!";

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", userEamil);
        json.put("password", userPass);
        json.put("age", 10);
        String payload = json.toString();
        return payload;
    }


    public String getCredentialPayload() {
        JSONObject json = new JSONObject();
        json.put("email", userEamil);
        json.put("password", userPass);
        String payload = json.toString();
        return payload;
    }

    public String getUpdatePayload() {
        JSONObject json = new JSONObject();
        json.put("age", 25);
        String payload = json.toString();
        return payload;
    }


    // ============ PAYLOAD FILE ====================//
    private String read(String filePath) {
        String finalText = null;
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            finalText = sb.toString();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalText;
    }

    public String getPayload(String filename) {
        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/"+filename+".json";
        String payload = read(path).trim();
        return payload;
    }

    public String extractData(String json, String query) {
        return JsonPath.read(json, query);
    }

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
    }

}
