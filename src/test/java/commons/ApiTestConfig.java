package commons;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class is parent class for all the Test Classes that contains
 * test cases. It contains commonly used functions and methods where each
 * of the test cases will be using or could use.
 */
public abstract class ApiTestConfig {

    private String token;
    private String sessionToken;
    private String name;
    private String userEmail;
    private String userPass;
    private int age = 10;


    /**
     * Extracts the registration token and sets to token global variable
     *
     * @param newToken Stirng: registration token recieved from the server
     */
    public void setToken(String newToken) {
        if(newToken == null) {
            throw new NullPointerException("token value cannot be null");
        }
        token  = newToken;
    }


    /**
     * Returns the registration token
     *
     * @return String: registration token
     */
    public String getToken() {
        return token;
    }


    /**
     * Extracts the session token and sets to the sessionToken global variable
     *
     * @param input String: session token recieved from the server
     */
    public void setSessionToken(String input){
        if(input == null) {
            throw new NullPointerException("Token cannot be empty");
        }
        sessionToken = input;
    }


    /**
     * Returns the session token
     * @return String: session token value
     */
    public String getSessionToken() {
        return sessionToken;
    }


    /**
     * Gets the auto-generated full name of the user
     *
     * @return String full-name generated
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the auto-generated eamil address of the user
     *
     * @return String email address generated
     */
    public String getUserEmail() {
        return userEmail;
    }


    /**
     *  Gets the age of the test user
     *
     * @return int age value
     */
    public int getAge() {
        return age;
    }


    /**
     * Reads the file content and coverts to string
     *
     * @param filePath String path to the target file
     * @return String: file content converted to string
     */
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


    /**
     * Giving the file that contains JSON file, it converts the JSON format
     * to the String data, and returns to the caller.
     *
     * @param filename  file name without extention
     *
     * @return String: json data converted to string
     */
    public String getPayload(String filename) {
        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/"+filename+".json";
        String payload = read(path).trim();
        return payload;
    }


    /**
     * Given JSON data, it will extract the target value
     * specified by Json Path query.
     *
     * @param json  json data
     * @param query json path query
     * @param <T>  extracted data's data type
     * @return  extracted data
     */
    public <T> T extractData(String json, String query) {
        T extracted = JsonPath.read(json, query);
        return extracted;
    }

    //======== Payload ===========//

    /**
     * Auto generates the new user's payload that contains registration
     * contentes
     *
     * @return String:  payload content for new user
     */
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


    /**
     *  Converts users credentials into JSON payload
     *
     * @return JSON payload in String format
     */
    public String getCredentialPayload() {
        JSONObject json = new JSONObject();
        json.put("email",userEmail);
        json.put("password",userPass );
        return json.toString();
    }



    @BeforeMethod
    public void setUp() {
        // Registering API server URL to the RestAssures
        // so it know where to send the request
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
    }

}

