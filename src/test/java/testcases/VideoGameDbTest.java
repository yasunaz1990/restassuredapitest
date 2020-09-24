package testcases;

import commons.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class VideoGameDbTest extends ApiConfig {

    @Test
    public void get_all_video_games() {
        RestAssured.baseURI = gameDb_base_uri;
        Response response = RestAssured.given()
                .accept("Application/json")
                .get("/videogames");

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();
    }

    @Test
    public void regisiter_single_game() {

        String path = System.getProperty("user.dir") + "/src/test/resources/payloads/newGame.json";
        String payload = read(path).trim();

        RestAssured.baseURI = gameDb_base_uri;
        Response response = RestAssured.given()
                .contentType("application/json; charset=utf-8")
                .accept("Application/json")
                .body(payload)
                .post("/videogames");

        System.out.println(response.getStatusLine());
        System.out.println(response.statusCode());
        response.getBody().prettyPrint();
    }

    @Test
    public void delete_single_videogame() {
        RestAssured.baseURI = gameDb_base_uri;
        Response response = RestAssured.given()
                .accept("Application/json")
                .delete("videogames/11");

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();
    }


    @Test
    public void get_singe_game() {
        RestAssured.baseURI = gameDb_base_uri;
        Response response = RestAssured.given()
                .header("Accept","Application/json")
                .get("/videogames/5");

        System.out.println(response.getStatusLine());
        response.getBody().prettyPrint();
    }

    @Test
    public void update_single_game() {

        String payload = getPayload("gameinfo");

        RestAssured.baseURI = gameDb_base_uri;
        Response response = RestAssured.given()
                .contentType("Application/json")
                .header("Accept", "Application/json")
                .body(payload)
                .put("/videogames/9");

        System.out.println(response.statusCode());
        response.getBody().prettyPrint();
    }
}
