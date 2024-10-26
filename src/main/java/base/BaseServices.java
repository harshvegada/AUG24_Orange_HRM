package base;

import constants.CommonAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public abstract class BaseServices {

    public Response executeGetAPI(String endPoint) {
        Response response =
                RestAssured.given().log().all()
                .baseUri(CommonAPI.BASE_URI)
                .headers("Content-Type", "application/x-www-form-urlencoded")
//                .headers("Authorization", "Bearer " + CommonServices.getAccessToken())
                .cookies(CommonServices.cookies)
                .when()
                .get(endPoint)
                .then().extract().response();
        RestAssured.reset();
        return response;
    }

    public Response executePostAPI(String endPoint, String payload) {
        Response response = RestAssured.given()
                .baseUri(CommonAPI.BASE_URI)
                .log().all()
                .headers("Content-Type", "application/json")
//                .headers("Authorization", "Bearer " + CommonServices.getAccessToken())
                .cookies(CommonServices.cookies)
                .when()
                .body(payload)
                .post(endPoint)
                .then().extract().response();
        RestAssured.reset();
        return response;
    }


    public void executePutAPI() {
//        buildService();
    }


}
