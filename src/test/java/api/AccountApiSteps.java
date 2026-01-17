package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.AuthBodyModel;
import models.AuthResponseModel;
import org.openqa.selenium.devtools.v136.network.model.Request;

import java.util.Objects;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

public class AccountApiSteps {

    @Step("Выполнить запрос на авторизацию пользователя")
    public Response authUser(AuthBodyModel authData) {
        return given().spec(requestSpec)
                .body(authData)
                .when()
                .post("login");
    }

    @Step("Выполнить запрос на проверку статуса авторизации пользователя")
    public Response makeCheckAuthStatus(String token) {
        return given().spec(requestSpec)
                .header("X-token", token)
                .when()
                .get("login");
    }

    @Step("Выполнить запрос на завершение сеанса пользователя")
    public Response logoutUser(String token) {
        return given().spec(requestSpec)
                .header("X-token", token)
                .when()
                .delete("logout");
    }

    @Step("Проверка статус кода запроса")
    public void checkStatusCode(Response response, Integer statusCode) {
        response.then()
                .spec(responseSpec(statusCode));
    }

    @Step("Проверка bodySchema тела ответа")
    private void checkAuthStatusBody(Response response, String pathToSchema) {
        response.then()
                .body(matchesJsonSchemaInClasspath(pathToSchema));
    }

    @Step("Завершить сеанс пользователя с проверкой статус кода")
    public AccountApiSteps successfulLogoutUser(String token) {
        Response response = logoutUser(token);
        checkStatusCode(response, 204);

        return this;
    }

    @Step("Получить тело ответа на успешный запрос POST /login {requestName}")
    public AuthResponseModel getSuccessfulAuthUserBody(AuthBodyModel authData, String requestName) {
        return await().atMost(20, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                            Response response = authUser(authData);
                            checkSuccessfulRequest(response, 201, "schemas/auth-schema.json", requestName);

                            AuthResponseModel checkedResponse = response.then()
                                    .extract()
                                    .as(AuthResponseModel.class);

                            return checkedResponse.token() != null ? checkedResponse : null;
                        },
                        Objects::nonNull);
    }

    @Step("Проверка статус кода и тела ответа запроса {requestName}")
    public AccountApiSteps checkSuccessfulRequest(Response response, Integer statusCode, String pathToSchema, String requestName) {
        checkStatusCode(response, statusCode);
        checkAuthStatusBody(response, pathToSchema);

        return this;
    }

    @Step("Проверка сообщения об ошибке неуспешного запроса {requestName}")
    public void checkErrorMessage(Response response, String errorMessage) {
         response.then()
                 .body("message", equalTo(errorMessage));
    }

    @Step("Проверка email в теле ответа успешного запроса POST /login")
    public void checkEmailInBody(String userEmail, String responseEmail) {
        assertEquals(userEmail, responseEmail, "Email в теле ответа " + responseEmail + " не соответствует ожидаемому " + userEmail);
    }
}
