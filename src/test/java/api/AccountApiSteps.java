package api;

import io.qameta.allure.Step;
import models.AuthBodyModel;
import models.AuthResponseModel;
import java.util.Objects;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

public class AccountApiSteps {
    @Step("Авторизация пользователя")
    public AuthResponseModel authUser(AuthBodyModel authData) {
        return await().atMost(20, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                            AuthResponseModel response = given()
                                    .spec(requestSpec)
                                    .body(authData)
                                    .when()
                                    .post("login")
                                    .then()
                                    .spec(responseSpec(201))
                                    .extract().as(AuthResponseModel.class);

                            return response.token() != null ? response : null;
                        },
                        Objects::nonNull);
    }

    @Step("Завершение сеанса пользователя")
    public void logoutUser(String token) {
        given().spec(requestSpec)
               .header("X-token", token)
               .when()
               .delete("logout")
               .then()
               .spec(responseSpec(204));
    }
}
