package tests;

import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();

    String validEmail = faker.internet().emailAddress();
    String validPassword = faker.internet().password(3, 15, true);
    String invalidEmail = faker.name().username() + "@";
}
