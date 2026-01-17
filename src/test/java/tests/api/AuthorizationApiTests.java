package tests.api;

import api.AccountApiSteps;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.AuthBodyModel;
import models.AuthResponseModel;
import org.junit.jupiter.api.*;
import tests.TestBase;
import tests.TestData;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tags({@Tag("all"), @Tag("api"), @Tag("authorization_all"), @Tag("authorization_api")})
@Feature("Авторизация пользователя")
@DisplayName("API тесты на авторизацию")
public class AuthorizationApiTests extends TestBase {
    AccountApiSteps accountApiSteps = new AccountApiSteps();

    @Test
    @DisplayName("Пользователь успешно авторизуется с валидными данными")
    void checkSuccessfulAuth() {
        TestData testData = new TestData();
        AuthBodyModel validAuthData = testData.randomAuthData;

        String token = accountApiSteps.getSuccessfulAuthUserBody(validAuthData, "Авторизация пользователя")
                .token();

        Response authStatusResponse = accountApiSteps.makeCheckAuthStatus(token);

        accountApiSteps.checkSuccessfulRequest(
                authStatusResponse,
                200,
                "schemas/auth-schema.json",
                "Проверка статуса авторизации пользователя");
    }

    @Test
    @DisplayName("Email пользователя в теле ответа соответствует переданному в теле запроса на авторизацию")
    void checkEmailSuccessfulAuth() {
        TestData testData = new TestData();
        AuthBodyModel validAuthData = testData.randomAuthData;

        AuthResponseModel responseBody = accountApiSteps.getSuccessfulAuthUserBody(validAuthData, "Авторизация пользователя");

        assertEquals(validAuthData.email(), responseBody.email(), "Email в теле ответа " + responseBody.email() + " не соответствует ожидаемому " + validAuthData.email());
    }
    // проверка тела ответа при неуспешной авторизации с невалидным имейлом 401
    // проверка тела ответа при неуспешной авторизации с невалидным паролем 401
    // проверить статус авторизации с залогиненным пользователем 200 и тело
    // проверить статус авторизации с незалогиненным пользователем 401 и тело
}
