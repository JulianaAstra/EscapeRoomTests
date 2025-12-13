package api;

import io.qameta.allure.Step;
import models.QuestBookingResponseModel;
import models.QuestModel;
import models.UserBookingResponseModel;
import util.QuestHelper;

import java.util.List;
import static io.restassured.RestAssured.given;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

public class QuestsApiSteps {
    @Step("Получить список квестов")
    public List <QuestModel> getQuestsList() {
        return given()
                .spec(requestSpec)
                .when()
                .get("quest")
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", QuestModel.class);
    }

    @Step("Получить информацию о бронировании квеста {questName}")
    public List<QuestBookingResponseModel> getQuestInfo(String questId, String questName) {
        return given()
                .spec(requestSpec)
                .when()
                .get("quest/" + questId + "/booking")
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", QuestBookingResponseModel.class);
    }

    @Step("Получить список бронирований пользователя")
    public List<UserBookingResponseModel> getUserBookings(String token) {
        return given()
                .spec(requestSpec)
                .header("X-Token", token)
                .when()
                .get("reservation")
                .then()
                .spec(responseSpec(200))
                .extract()
                .jsonPath()
                .getList("", UserBookingResponseModel.class);
    }

    @Step("Удалить бронирование квеста {questName} пользователя")
    public void deleteUserBooking(String token, String bookingId) {
        given()
                .spec(requestSpec)
                .header("X-Token", token)
                .when()
                .delete("reservation/" + bookingId)
                .then()
                .spec(responseSpec(204));
    }

    @Step("Проверка доступных слотов для бронирования квеста {questName}")
    public void checkTimeSlots(QuestBookingResponseModel questBookingInfo, String questName) {
        QuestHelper questHelper = new QuestHelper();

        if (!questHelper.hasAvailableTimeSlots(questBookingInfo)) {
            throw new RuntimeException("У выбранного квеста нет доступных слотов");
        }
    }
}
