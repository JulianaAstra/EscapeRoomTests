package api;

import io.qameta.allure.Step;
import models.AuthResponseModel;
import tests.TestData;
import java.util.Objects;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

public class AccountApiSteps {
    TestData testData = new TestData();

    @Step("Авторизация пользователя")
    public AuthResponseModel authUser() {
        return await().atMost(20, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> {
                            AuthResponseModel response = given()
                                    .spec(requestSpec)
                                    .body(testData.authData)
                                    .when()
                                    .post("login")
                                    .then()
                                    .spec(responseSpec(201))
                                    .extract().as(AuthResponseModel.class);

                            return response.token() != null ? response : null;
                        },
                        Objects::nonNull);
    }
}
