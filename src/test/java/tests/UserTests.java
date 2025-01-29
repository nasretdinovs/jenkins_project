package tests;

import helpers.ApiMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import models.User;
import models.random.RandomUser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.Constants.DELETE_USER;
import static constants.Constants.GET_USER;
import static constants.Constants.PUT_USER;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;

@Epic("Swagger Petstore")
@Feature("API. User. '/user'.")
public class UserTests extends BaseTest {
    private User user;
    private RandomUser randomUser = new RandomUser();

    @Test
    @Story("Создаем нового пользователя.")
    @Description("API. User. POST '/user.")
    @Owner("Ruslan Bikineev")
    @Severity(BLOCKER)
    public void testPostCreateValidUser() {
        user = randomUser.getRandomUser();
        ApiMethods.postUser(user);
    }

    @Test
    @Story("Получаем существующего пользователя.")
    @Description("API. User. GET '/user/{username}.")
    @Owner("Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetExistingUser() {
        user = randomUser.getRandomUser();
        ApiMethods.postUser(user);
        User userResponse = RestAssured.given()
                .when()
                .get(GET_USER, user.getUserName())
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getObject("", User.class);
        Assert.assertEquals(userResponse, user);
    }

    @Test
    @Story("Изменяем существующего пользователя.")
    @Description("API. User. PUT '/user/{username}.")
    @Owner("Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testPutExistingUser() {
        user = randomUser.getRandomUser();
        ApiMethods.postUser(user);
        user.setFirstName(randomUser.getFaker().name().firstName());
        user.setLastName(randomUser.getFaker().name().lastName());
        RestAssured.given()
                .when()
                .body(user)
                .put(PUT_USER, user.getUserName())
                .then()
                .statusCode(HTTP_OK)
                .body("message", equalTo(user.getId().toString()));
    }

    @Test
    @Story("Удаляем существующего пользователя.")
    @Description("API. User. DELETE '/user/{username}.")
    @Owner("Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testDeleteExistingUser() {
        user = randomUser.getRandomUser();
        ApiMethods.postUser(user);
        RestAssured.given()
                .when()
                .delete(DELETE_USER, user.getUserName())
                .then()
                .statusCode(HTTP_OK)
                .body("message", equalTo(user.getUserName()));
    }
}
