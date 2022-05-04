import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;


public class  ReqresTests {
        public String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @BeforeClass
    public static void setBase()
    {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void validateSuccessfulRegister() throws IOException
    {
        String jsonBody = generateStringFromResource("C:\\IdeaProjects\\cassiniRestAssured\\src\\test\\resources\\register.json");
        Response response = given().
                header("Content-Type","application/json").
                body(jsonBody).
                when().
                post("/api/register").
                then().
                extract().response();
                 Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void validateSuccessfulLogin() throws IOException
    {
        String jsonBody = generateStringFromResource("C:\\IdeaProjects\\cassiniRestAssured\\src\\test\\resources\\login.json");
        Response response = given().
                header("Content-Type","application/json").
                body(jsonBody).
                when().
                post("/api/login").
                then().
                extract().response();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void implementAssertion() throws IOException
    {
        Response response = given().
                header("Content-Type","application/json").
                when().
                get("/api/unknown").
                then().
                extract().response();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue((response.getBody().jsonPath().get("data[0].id").toString().equals("1")));
        Assert.assertTrue((response.getBody().jsonPath().get("data[0].name").toString().equals("cerulean")));
        }

    }
