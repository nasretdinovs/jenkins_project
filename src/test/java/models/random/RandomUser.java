package models.random;

import com.github.javafaker.Faker;
import lombok.Getter;
import models.User;

@Getter
public class RandomUser {
    private Faker faker = new Faker();

    /**
     * Метод для генерации случайного пользователя
     * с помощью библиотеки Faker
     *
     * @return - случайно сгенерированный пользователь
     */
    public User getRandomUser() {
        return new User(
                faker.number().numberBetween(1, 100),
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.phoneNumber().cellPhone(),
                faker.number().numberBetween(1, 100)
        );
    }
}
