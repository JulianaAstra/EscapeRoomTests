package tests.api;

import api.AccountApiSteps;
import api.AccountRequestsSteps;
import io.qameta.allure.Feature;
import models.AuthBodyModel;
import models.AuthResponseModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;
import tests.TestData;
import util.ApiHelper;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static specs.Spec.responseSpec;
import static tests.TestData.*;
import static tests.TestData.EMAIL_EMPTY;

@Tags({@Tag("all"), @Tag("api"), @Tag("authorization_all"), @Tag("authorization_api")})
@Feature("Авторизация пользователя")
@DisplayName("API тесты на авторизацию")
public class AuthorizationApiTests extends TestBase {
    AccountApiSteps accountApiSteps = new AccountApiSteps();
    ApiHelper apiHelper = new ApiHelper();
    AccountRequestsSteps accountRequests = new AccountRequestsSteps();

    @Test
    @DisplayName("Пользователь успешно авторизуется с валидными данными")
    void checkSuccessfulAuth() {
        TestData testData = new TestData();
        AuthBodyModel validAuthData = testData.randomAuthData;

        String token = accountApiSteps.getSuccessfulAuthUserBody(validAuthData, "Авторизация пользователя")
                .token();

        accountRequests.makeCheckAuthStatus(token)
                .then()
                .spec(responseSpec(200))
                .body(matchesJsonSchemaInClasspath("schemas/auth-schema.json"));
    }

    @Test
    @DisplayName("Email пользователя в теле ответа соответствует переданному в теле запроса на авторизацию")
    void checkEmailSuccessfulAuth() {
        TestData testData = new TestData();
        AuthBodyModel validAuthData = testData.randomAuthData;

        AuthResponseModel responseBody = accountApiSteps.getSuccessfulAuthUserBody(validAuthData, "Авторизация пользователя");

        apiHelper.checkBodyValue(validAuthData.email(), responseBody.email(), "email");
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

        accountRequests.authUser(invalidAuthData)
                .then()
                .spec(responseSpec(400))
                .body("message", equalTo("Validation error: '/v1/escape-room/login'"));
    }
}
