package apitest.restassured;

import apitest.testdata.PrepareRegisterUserData;
import constants.Urls;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class RestAssuredTest {
    @Test
    public void checkUserNotFoundTest() {
        RestAssured
                .given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void checkFieldsTest() {
        RestAssured
                .given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("page", instanceOf(Integer.class))
                .body("per_page", equalTo(6));

    }

    @Test
    public void checkStaticResponceTest() {
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/user.json"));
        RestAssured
                .given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("", equalTo(expectedJson.getMap("")));
    }

    @Test
    public void getWithQueryParamTest() {
        RestAssured
                .given()
                .queryParam("page", "2")
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().body();
    }

    @Test
    public void updateUserTest() {
        UpdateUserModel updateBody = PrepareRegisterUserData.getNameAndeJob();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .body(updateBody)
                .log().body()
                .when()
                .patch(Urls.BASE_URL.concat(Urls.USERS_URL))
                .then()
                .statusCode(200)
                .log().body()
                .log().status();

    }

    @Test
    public void deleteUserTest() {
        UpdateUserModel updateBody = PrepareRegisterUserData.getNameAndeJob();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .body(updateBody)
                .log().body()
                .when()
                .delete(Urls.BASE_URL.concat(Urls.USERS_URL))
                .then()
                .statusCode(204)
                .log().body()
                .log().status();

    }

    @Test
    public void registerSuccessfulUserTest() {
        RegisterUserModule userModule = PrepareRegisterUserData.getValidData();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .body(userModule)
                .log().body()
                .when()
                .post(Urls.BASE_URL.concat(Urls.REGISTER_URL))
                .then()
                .statusCode(200)
                .log().body()
                .log().status();

    }

    @Test
    public void registerOnSuccessfulUserTest() {
        RegisterFailedUserModule userModule = PrepareRegisterUserData.getOnValidData();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .body(userModule)
                .log().body()
                .when()
                .post(Urls.BASE_URL.concat(Urls.REGISTER_URL))
                .then()
                .statusCode(400)
                .log().body()
                .log().status();

    }

    @Test
    public void loginSuccessfulUserTest() {
        RegisterUserModule userModule = PrepareRegisterUserData.getLoginData();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .body(userModule)
                .log().body()
                .when()
                .post(Urls.BASE_URL.concat(Urls.LOGIN_URL))
                .then()
                .statusCode(200)
                .log().body()
                .log().status();

    }

    @Test
    public void loginOnSuccessfulUserTest() {
        RegisterFailedUserModule userModule = PrepareRegisterUserData.getOnLoginData();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .when()
                .body(userModule)
                .log().body()
                .when()
                .post(Urls.BASE_URL.concat(Urls.LOGIN_URL))
                .then()
                .statusCode(400)
                .log().body()
                .log().status();

    }

    @Test
    public void getUsersDelayedTest() {
        RestAssured
                .given()
                .when()
                .get(Urls.BASE_URL.concat(Urls.USERS_DELAYED_URL))
                .then()
                .statusCode(200)
                .log().body()
                .log().status();
    }
}
