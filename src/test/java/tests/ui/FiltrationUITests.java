package tests.ui;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import tests.TestBase;

@Tags({@Tag("all"), @Tag("ui"), @Tag("filtration")})
@Feature("Фильтрация квестов")
@DisplayName("UI тесты на фильтрацию квестов на главной странице")
public class FiltrationUITests extends TestBase {
    // тест 1 добавить фильтр по сложности, чистый UI
    // тест 2 при клике на карточку теста открывается страница теста - проверить название, сложность, какие-то данные
}
