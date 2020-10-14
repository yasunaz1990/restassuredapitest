package commons;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.FileReader;

public class ApiTestConfig {

    private String token;
    private String sessionToken;
    private String name;
    private String userEmail;
    private String userPass;
    private int age = 10;

    public void setToken(String newToken) {
        if(newToken == null) {
            throw new NullPointerException("token value cannot be null");
        }
        token  = newToken;
    }

    public String getToken() {
        return token;
    }

    public void setSessionToken(String input){
        if(input == null) {
            throw new NullPointerException("Token cannot be empty");
        }
        sessionToken = input;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public String getName() {
        return name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getAge() {
        return age;
    }


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

    public <T> T extractData(String json, String query) {
        T extracted = JsonPath.read(json, query);
        return extracted;
    }

    //======== Payload ===========//
    public String generateNewUserPayload() {
        Faker faker = new Faker();
        name = faker.name().fullName();
        userEmail = faker.internet().emailAddress();
        userPass = "Pass234!";

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email",userEmail);
        json.put("password",userPass );
        json.put("age", age);

        return json.toString();
    }

    public String getCredentialPayload() {
        JSONObject json = new JSONObject();
        json.put("email",userEmail);
        json.put("password",userPass );
        return json.toString();
    }

    public String getBody(Response response){
        return response.getBody().asString();
    }

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
    }

}

