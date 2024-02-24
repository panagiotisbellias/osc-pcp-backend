package gr.pcp;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GreetingResourceTest {

    @InjectMocks
    GreetingResource greetingResource;

    @Disabled("database is needed")
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello, dear quarkus developer!"));
    }

    @Test
    void testHello() {
        Assertions.assertNull(greetingResource.hello());
    }

}