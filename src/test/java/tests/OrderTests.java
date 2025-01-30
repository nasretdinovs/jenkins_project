package tests;

import helpers.ApiMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import models.Order;
import models.random.RandomOrder;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.Constants.DELETE_ORDER;
import static constants.Constants.GET_ORDER;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;

@Epic("Swagger Petstore")
@Feature("API. Order. '/store/order'.")
public class OrderTests extends BaseTest {
    private Order order;
    private RandomOrder randomOrder = new RandomOrder();

    @Test
    @Story("Оформляем новый заказ.")
    @Description("API. Order. POST '/store/order.")
    @Owner("Ruslan Bikineev")
    @Severity(BLOCKER)
    public void testPostCreateValidOrder() {
        order = randomOrder.getRandomOrder();
        Order createOrder = ApiMethods.postOrder(order);
        Assert.assertEquals(createOrder, order);
    }

    @Test
    @Story("Получаем существующий заказ.")
    @Description("API. Order. GET '/store/order/{orderId}.")
    @Owner("Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testGetExistingOrder() {
        order = randomOrder.getRandomOrder();
        ApiMethods.postOrder(order);
        Order orderResponse = RestAssured.given()
                .when()
                .get(GET_ORDER, order.getId())
                .then()
                .statusCode(HTTP_OK)
                .extract()
                .jsonPath()
                .getObject("", Order.class);
        Assert.assertEquals(orderResponse, order);
    }

    @Test
    @Story("Удаляем существующий заказ.")
    @Description("API. Order. DELETE '/store/order/{orderId}.")
    @Owner("Ruslan Bikineev")
    @Severity(CRITICAL)
    public void testDeleteExistingOrder() {
        order = randomOrder.getRandomOrder();
        ApiMethods.postOrder(order);
        RestAssured.given()
                .when()
                .delete(DELETE_ORDER, order.getId())
                .then()
                .statusCode(HTTP_OK)
                .body("message", equalTo(order.getId().toString()));
    }
}
