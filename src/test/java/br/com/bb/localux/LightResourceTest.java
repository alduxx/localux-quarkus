package br.com.bb.localux;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LightResourceTest {
    @Test
    public void testGetAllLightsEndpoint() {
        given()
                .when().get("/lights")
                .then()
                .statusCode(200)
                .body(containsString("Central"));
    }

    @Test
    public void testGetSingleLightEndpoint() {
        given()
                .when().get("/lights/id/14")
                .then()
                .statusCode(200)
                .body(containsString("Central"));
    }

    @Test
    public void testToggleLightEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .put("/lights?id=14")
                .then()
                .statusCode(200)
                .body(containsString("Central"));
    }


    /*
    @Test
    public void testAdd() {
        given()
                .body("{\"name\": \"Pear\", \"description\": \"Winter fruit\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "name", containsInAnyOrder("Apple", "Pineapple", "Pear"),
                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit", "Winter fruit"));

        given()
                .body("{\"name\": \"Pear\", \"description\": \"Winter fruit\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .delete("/fruits")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "name", containsInAnyOrder("Apple", "Pineapple"),
                        "description", containsInAnyOrder("Winter fruit", "Tropical fruit"));
    }
     */
}

