package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.AuthBodyModel;
import models.AuthResponseModel;
import java.util.Objects;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class AccountApiSteps {
    CheckApiSteps checkApiSteps = new CheckApiSteps();
    AccountRequestsSteps accountRequests = new AccountRequestsSteps();

    @Step("Завершить сеанс пользователя с проверкой статус кода")
    public void successfulLogoutUser(String token) {
        Response response = accountRequests.logoutUser(token);
        checkApiSteps.checkStatusCode(response, 204);
    }

    @Step("Получить тело ответа на успешный запрос POST /login {requestName}")
    public AuthResponseModel getSuccessfulAuthUserBody(AuthBodyModel authData, String requestName) {
        return await().atMost(20, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                            Response response = accountRequests.authUser(authData);
                            checkApiSteps.checkSuccessfulRequest(response, 201, "schemas/auth-schema.json", requestName);

                            AuthResponseModel checkedResponse = response.then()
                                    .extract()
                                    .as(AuthResponseModel.class);

                            return checkedResponse.token() != null ? checkedResponse : null;
                        },
                        Objects::nonNull);
    }
}
