package base;

import constants.CommonAPI;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public abstract class BaseServices_Modular {

    RequestSpecBuilder requestSpecBuilder;
    static String accessToken;

    public BaseServices_Modular() {
        requestSpecBuilder = new RequestSpecBuilder();
    }

    public void setContentTypeAsURLENC() {
        setUp();
        requestSpecBuilder.setContentType(ContentType.URLENC);
    }

    public void setContentTypeAsApplicationJSON() {
        setUp();
        requestSpecBuilder.setContentType(ContentType.JSON);
    }

    private void setToken() {
        setUp();
        requestSpecBuilder.addHeader("Authorization", "Bearer " + accessToken);
    }

    public void setCookies() {
        setUp();
        requestSpecBuilder.addCookies(CommonServices.cookies);
    }

    public void setQueryParameters(Map<String, String> queryParameters) {
        setUp();
        requestSpecBuilder.addQueryParams(queryParameters);
    }

    public void setBody(Object payload) {
        setUp();
        requestSpecBuilder.setBody(payload);
    }

    public Response executeGetAPI(String endPoint) {
        setToken();
        setCookies();
        Response response = RestAssured.
                given().log().all()
                .spec(requestSpecBuilder.build())
                .when()
                .get(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }

    public Response executePostAPI(String endPoint) {
        setToken();
        setCookies();
        Response response = RestAssured.given()
                .log().all()
                .spec(requestSpecBuilder.build())
                .when()
                .post(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }

    private void setUp() {
        if (requestSpecBuilder == null) {
            requestSpecBuilder = new RequestSpecBuilder();
        }
        requestSpecBuilder.setBaseUri(CommonAPI.BASE_URI);
        requestSpecBuilder.addFilter(new AllureRestAssured());
    }

    private void tearDown() {
        requestSpecBuilder = null;
    }

    public Response executePutAPI(String endPoint) {
        setToken();
        setCookies();
        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .put(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }

    public Response executePatchAPI(String endPoint) {
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .patch(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }

    public Response executeDeleteAPI(String endPoint) {
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .delete(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }


}
