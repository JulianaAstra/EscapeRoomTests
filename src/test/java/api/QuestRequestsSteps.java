package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.QuestBookingBodyModel;
import static io.restassured.RestAssured.given;
import static specs.Spec.requestSpec;

public class QuestRequestsSteps {
    @Step("Выполнить запрос на получение списка квестов")
    public Response makeGetQuestListRequest() {
        return given()
                .spec(requestSpec)
                .when()
                .get("quest");
    }

    @Step("Выполнить запрос на бронирование квеста {questName}")
    public Response makeBookQuestRequest(String token, QuestBookingBodyModel bookingData, String questId, String questName) {
        return given().spec(requestSpec)
                .header("X-token", token)
                .body(bookingData)
                .when()
                .post("quest/" + questId + "/booking");
    }

    @Step("Выполнить запрос на получение информации о бронировании квеста {questName}")
    public Response makeGetQuestBookingInfoRequest(String questId, String questName) {
        return given()
                .spec(requestSpec)
                .when()
                .get("quest/" + questId + "/booking");
    }

    @Step("Выполнить запрос на получение списка бронирований пользователя {userEmail}")
    public Response makeGetUserBookingsRequest(String token, String userEmail) {
        return given()
                .spec(requestSpec)
                .header("X-Token", token)
                .when()
                .get("reservation");
    }

    @Step("Выполнить запрос на удаление бронирования квеста {bookingId}")
    public Response makeDeleteBookingRequest(String token, String bookingId) {
        return given()
                .spec(requestSpec)
                .header("X-Token", token)
                .when()
                .delete("reservation/" + bookingId);
    }

    @Step("Выполнить запрос на получение информации о квесте {questId}")
    public Response makeGetQuestInfoRequest(String questId) {
        return given()
                .spec(requestSpec)
                .when()
                .get("quest/" + questId);
    }

    @Step("Выполнить запрос на бронирование квеста {questId} c пустым телом")
    public Response makeBookQuestEmptyRequest(String token, String questId) {
        return given().spec(requestSpec)
                .header("X-token", token)
                .when()
                .post("quest/" + questId + "/booking");
    }
}
