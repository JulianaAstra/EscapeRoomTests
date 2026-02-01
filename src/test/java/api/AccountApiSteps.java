package api;

import io.qameta.allure.Step;
import models.AuthBodyModel;
import models.AuthResponseModel;
import java.util.Objects;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static specs.Spec.responseSpec;

public class AccountApiSteps {
    AccountRequestsSteps accountRequests = new AccountRequestsSteps();

    @Step("Завершить сеанс пользователя с проверкой статус кода")
    public void successfulLogoutUser(String token) {
        accountRequests.logoutUser(token)
                .then()
                .spec(responseSpec(204));
    }

    @Step("Получить тело ответа на успешный запрос POST /login {requestName}")
    public AuthResponseModel getSuccessfulAuthUserBody(AuthBodyModel authData, String requestName) {
        return await().atMost(20, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                    AuthResponseModel checkedResponse = accountRequests.authUser(authData)
                            .then()
                            .spec(responseSpec(201))
                            .body(matchesJsonSchemaInClasspath("schemas/auth-schema.json"))
                            .extract()
                            .as(AuthResponseModel.class);

                            return checkedResponse.token() != null ? checkedResponse : null;
                        },
                        Objects::nonNull);
    }
}
