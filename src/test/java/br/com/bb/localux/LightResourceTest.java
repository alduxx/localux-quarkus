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
}

