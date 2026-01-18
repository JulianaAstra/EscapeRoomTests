package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Spec.responseSpec;

public class CheckApiSteps {
    @Step("Проверка статус кода запроса")
    public void checkStatusCode(Response response, Integer statusCode) {
        response.then()
                .spec(responseSpec(statusCode));
    }

    @Step("Проверка bodySchema тела ответа")
    public void checkAuthStatusBody(Response response, String pathToSchema) {
        response.then()
                .body(matchesJsonSchemaInClasspath(pathToSchema));
    }

    @Step("Проверка статус кода и тела ответа запроса {requestName}")
    public void checkSuccessfulRequest(Response response, Integer statusCode, String pathToSchema, String requestName) {
        checkStatusCode(response, statusCode);
        checkAuthStatusBody(response, pathToSchema);
    }

    @Step("Проверка сообщения об ошибке неуспешного запроса {requestName}")
    public void checkErrorMessage(Response response, String errorMessage) {
        response.then()
                .body("message", equalTo(errorMessage));
    }

    @Step("Проверка значения {actualValue} поля {field} в теле ответа")
    public <T> void checkBodyValue(T actualValue, T expectedValue, String field) {
        assertEquals(expectedValue, actualValue, "Значение поля " + field + " - " + actualValue + " в теле ответа не соответствует ожидаемому: " + expectedValue);
    }
}
