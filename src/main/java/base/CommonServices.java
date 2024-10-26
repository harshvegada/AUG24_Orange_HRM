package base;

import constants.CommonAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommonServices extends BaseServices_Modular {

    private static String csrfToken;
    public static Map<String, String> cookies;

    private static String getAccessToken(String username, String password) {
        getCSRFToken();
        validateCredentials(username, password);

        Response response = RestAssured.given()
                .baseUri(CommonAPI.BASE_URI)
                .cookies(cookies)
                .when()
                .get("/core/getLoggedInAccountToken")
                .then()
                .extract().response();

        accessToken = response.jsonPath().getString("token.access_token");
        Assert.assertNotNull(accessToken);
        Assert.assertEquals(response.statusCode(), 200);
        return accessToken;
    }

    private static void validateCredentials(String username, String password) {
        Response response = RestAssured.given()
                .baseUri(CommonAPI.BASE_URI)
                .cookies(cookies)
                .formParam("login[_csrf_token]", csrfToken)
                .formParam("hdnUserTimeZoneOffset", 5.5)
                .formParam("txtUsername", username)
                .formParam("txtPassword", password)
                .when()
                .post("/auth/validateCredentials")
                .then()
                .extract().response();
        cookies = response.getCookies();
        Assert.assertEquals(response.statusCode(), 200);
    }

    private static void getCSRFToken() {

        Response response = RestAssured.given()
                .baseUri(CommonAPI.BASE_URI)
                .when()
                .get("/auth/login")
                .then().extract().response();

        cookies = response.getCookies();
        Document document = Jsoup.parse(response.asString());
        csrfToken = document.getElementById("login__csrf_token").attr("value");
        System.out.println("CSRF Token : " + csrfToken);
        Assert.assertNotNull(csrfToken);
        System.out.println(response.getTimeIn(TimeUnit.SECONDS));

        Assert.assertEquals(response.statusCode(), 200);
    }

    public static String generateTokenFor(String userName, String password){
        return getAccessToken(userName, password);
    }

}
