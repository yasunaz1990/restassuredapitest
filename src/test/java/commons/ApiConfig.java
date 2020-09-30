package commons;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.FileReader;

public class ApiConfig {

    private static String userToken = "";

    public void setUserToken(String newToken) {
        userToken = newToken;
    }

    public String getUserToken() {
        return userToken;
    }


    public String read(String filePath) {
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
