package models.random;

import com.github.javafaker.Faker;
import models.Order;

import java.sql.Timestamp;

public class RandomOrder {
    private Faker faker = new Faker();

    /**
     * Метод для генерации случайного заказа
     * с помощью библиотеки Faker
     *
     * @return - случайно сгенерированный заказ
     */
    public Order getRandomOrder() {
        return new Order(
                faker.number().numberBetween(1, 100),
                faker.number().numberBetween(1, 100),
                faker.number().numberBetween(1, 100),
                new Timestamp(System.currentTimeMillis()),
                faker.lorem().sentence(),
                faker.bool().bool()
        );
    }
}
