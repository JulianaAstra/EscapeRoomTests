package tests.api;

import api.AccountApiSteps;
import api.AccountRequestsSteps;
import api.CheckApiSteps;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import models.AuthBodyModel;
import models.AuthResponseModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;
import tests.TestData;
import static tests.TestData.*;
import static tests.TestData.EMAIL_EMPTY;

@Tags({@Tag("all"), @Tag("api"), @Tag("authorization_all"), @Tag("authorization_api")})
@Feature("Авторизация пользователя")
@DisplayName("API тесты на авторизацию")
public class AuthorizationApiTests extends TestBase {
    AccountApiSteps accountApiSteps = new AccountApiSteps();
    CheckApiSteps checkApiSteps = new CheckApiSteps();
    AccountRequestsSteps accountRequests = new AccountRequestsSteps();

    @Test
    @DisplayName("Пользователь успешно авторизуется с валидными данными")
    void checkSuccessfulAuth() {
        TestData testData = new TestData();
        AuthBodyModel validAuthData = testData.randomAuthData;

        String token = accountApiSteps.getSuccessfulAuthUserBody(validAuthData, "Авторизация пользователя")
                .token();

        Response authStatusResponse = accountRequests.makeCheckAuthStatus(token);

        checkApiSteps.checkSuccessfulRequest(
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

        checkApiSteps.checkBodyValue(validAuthData.email(), responseBody.email(), "email");
    }

    @ValueSource(strings = {
            EMAIL_NO_DOMAIN,
            EMAIL_NO_AT,
            EMAIL_DOUBLE_AT,
            EMAIL_NO_USERNAME,
            EMAIL_SPACES,
            EMAIL_SPECIAL_CHARS,
            EMAIL_ONLY_DOMAIN,
            EMAIL_EMPTY
    })
    @ParameterizedTest(name = "email: {0}")
    @DisplayName("Регистрация с невалидным email невозможна: ")
    void registerWithInvalidEmailTest(String email) {
        TestData testData = new TestData();

        AuthBodyModel invalidAuthData = new AuthBodyModel(email, testData.validPassword);
        Response response = accountRequests.authUser(invalidAuthData);

        checkApiSteps.checkStatusCode(response,400);
        checkApiSteps.checkErrorMessage(response, "Validation error: '/v1/escape-room/login'");
    }
}
