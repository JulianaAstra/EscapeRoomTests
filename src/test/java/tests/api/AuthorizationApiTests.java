package tests.api;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import tests.TestBase;

@Tags({@Tag("all"), @Tag("api"), @Tag("authorization")})
@Feature("Авторизация пользователя")
@DisplayName("API тесты на авторизацию")
public class AuthorizationApiTests extends TestBase {
    // тест на проверку тела ответа после успешной авторизации 200 по схеме
    // проверка тела ответа при неуспешной авторизации с невалидным имейлом 401
    // проверка тела ответа при неуспешной авторизации с невалидным паролем 401
    // проверить статус авторизации с залогиненным пользователем 200 и тело
    // проверить статус авторизации с незалогиненным пользователем 401 и тело
}
