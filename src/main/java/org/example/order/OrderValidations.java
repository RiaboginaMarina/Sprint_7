package org.example.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class OrderValidations {
    @Step("Сравнить код и статус ответа с заданными. Вернуть из тела ответа значение поля track")
    public int createdSuccessfullyReturnTrack(ValidatableResponse response) {
        int track = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
        return track;
    }

    @Step("Сравнить код и статус ответа с заданными. Проверить, что в теле ответа вернулся не пустой список.")
    public void getOrderListSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders", is(not(empty())));
    }
}
