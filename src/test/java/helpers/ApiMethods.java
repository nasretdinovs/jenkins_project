package helpers;

import io.restassured.RestAssured;
import models.Order;
import models.User;

import static constants.Constants.POST_ORDER;
import static constants.Constants.POST_USER;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;

public class ApiMethods {
    /**
     * Метод для создания пользователя по API
     *
     * @param user - пользователь отправляемый в теле запроса
     */
    public static void postUser(User user) {
        RestAssured.given()
                .body(user)
                .when()
                .post(POST_USER)
                .then()
                .statusCode(HTTP_OK)
                .body("message", equalTo(user.getId().toString()));
    }

    /**
     * Метод для оформления заказа по API
     *
     * @param order - заказ отправляемый в теле запроса
     * @return - созданный заказ
     */
    public static Order postOrder(Order order) {
        return RestAssured.given()
                .body(order)
                .when()
                .post(POST_ORDER)
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getObject("", Order.class);
    }
}
