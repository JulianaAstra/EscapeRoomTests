package util;

import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiHelper {
    @Step("Проверка значения {actualValue} поля {field} в теле ответа")
    public <T> void checkBodyValue(T actualValue, T expectedValue, String field) {
        assertEquals(expectedValue, actualValue, "Значение поля " + field + " - " + actualValue + " в теле ответа не соответствует ожидаемому: " + expectedValue);
    }
}
