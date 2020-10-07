package commons;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class ApiTestConfig {

    private String token;
    private String userEmail;
    private String userPass;

    public void setToken(String newToken) {
        if(newToken == null) {
            throw new NullPointerException("token value cannot be null");
        }
        token  = newToken;
    }

    public String getToken() {
        return token;
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

    public String extractData(String responseJson, String query) {
        String extractedData = JsonPath.read(responseJson, query);
        return extractedData;
    }

    //======== Payload ===========//
    public String generateNewUserPayload() {
        Faker faker = new Faker();
        String fakeName = faker.name().fullName();
        userEmail = faker.internet().emailAddress();
        userPass = "Pass234!";

        JSONObject json = new JSONObject();
        json.put("name", fakeName);
        json.put("email",userEmail);
        json.put("password",userPass );
        json.put("age", 10);

        return json.toString();
    }

    public String getCredentialPayload() {
        JSONObject json = new JSONObject();
        json.put("email",userEmail);
        json.put("password",userPass );
        return json.toString();
    }

}
