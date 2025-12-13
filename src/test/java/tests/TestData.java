package tests;

import com.github.javafaker.Faker;
import models.AuthBodyModel;
import models.QuestModel;
import util.QuestHelper;

public class TestData {
    Faker faker = new Faker();
    static QuestHelper questHelper = new QuestHelper();

    public String validEmail = faker.internet().emailAddress();
    public String validPassword = "t1" + faker.internet().password(1, 10, true);

    public static String questName = System.getProperty("questName", "Ритуал");
    public static String personsCount = "5";
    public String userName = faker.name().firstName();
    public String userPhone = "+7(900)" + faker.numerify("###-##-##");

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

    private static final String REGISTERED_USER_EMAIL = "julianaas@yandex.ru";
    private static final String REGISTERED_USER_PASSWORD = "234password";
    public static final String REGISTERED_USER_TOKEN = "anVsaWFuYWFzQHlhbmRleC5ydQ==";

    public AuthBodyModel authData = new AuthBodyModel(validEmail, validPassword);
}