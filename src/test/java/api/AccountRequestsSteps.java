package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.AuthBodyModel;
import static io.restassured.RestAssured.given;
import static specs.Spec.requestSpec;

public class AccountRequestsSteps {
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
}
