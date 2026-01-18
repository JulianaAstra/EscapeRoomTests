package tests;

import com.github.javafaker.Faker;
import models.AuthBodyModel;

public class TestData {
    Faker faker = new Faker();

    public String validEmail = faker.internet().emailAddress();
    public String validPassword = "t1" + faker.internet().password(1, 10, true);
    public AuthBodyModel randomAuthData = new AuthBodyModel(validEmail, validPassword);

    public String userName = faker.name().firstName();
    public String userPhone = "+7(900)" + faker.numerify("###-##-##");
    public String notExistingQuestId = faker.internet().uuid();

    public final static String EMAIL_NO_DOMAIN = "test@";
    public final static String EMAIL_NO_AT = "test.example.com";
    public final static String EMAIL_DOUBLE_AT = "test@@example.com";
    public final static String EMAIL_NO_USERNAME = "@example.com";
    public final static String EMAIL_SPACES = "test user@example.com";
    public final static String EMAIL_SPECIAL_CHARS = "test#user@example.com";
    public final static String EMAIL_ONLY_DOMAIN = "example.com";
    public final static String EMAIL_EMPTY = "";

    public static final String PASSWORD_TOO_SHORT = "a1";
    public static final String PASSWORD_EMPTY = "";
    public static final String PASSWORD_ONE_CHAR = "a";
    public static final String PASSWORD_16_CHARS = "a1b2c3d4e5f6g7h8";
    public static final String PASSWORD_ONLY_NUMBERS = "1234";
    public static final String PASSWORD_ONLY_LETTERS = "password";
    public static final String PASSWORD_CYRILLIC = "пароль123";
}