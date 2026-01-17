package tests.api;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import tests.TestBase;

@Tags({@Tag("all"), @Tag("api"), @Tag("booking")})
@Feature("Бронирование квеста")
@DisplayName("API тесты на бронирование квеста")
public class BookingApiTests extends TestBase {
    // тест 1 позитивный
    // забронировать квест авторизованным пользователем
    // передать валидные данные типа вермя и кол-во людей
    // проверить бронирование квеста, 200 и тело, чтобы данные в теле совпадали с переданными

    // тесты 2-... негативные проверки ошибок 400 VALIDATION_ERROR
    // не верный тип данных в теле запроса

    // тест валидации 400 передать не тот тип даты today/tomorrow (yesterday)

    // тест с некорректным токеном 401

    // тест с не существующим айди квеста 404 Quest with id aba664c3-bdf3-4fb3-b8f3-42e007864bbf not found
}
