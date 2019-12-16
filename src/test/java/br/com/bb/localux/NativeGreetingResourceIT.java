package br.com.bb.localux;

import io.quarkus.test.junit.NativeImageTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@NativeImageTest
public class NativeGreetingResourceIT extends GreetingResourceTest {
    @QuarkusTest
    public class GreetingResourceTest {

        @Test
        public void testHelloEndpoint() {
            given()
                    .when().get("/api")
                    .then()
                    .statusCode(200)
                    .body(is("oi"));
        }

    }
}