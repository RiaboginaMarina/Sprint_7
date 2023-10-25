package org.example.courier;


import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

import java.util.Map;


public class CourierClient extends Client {
    public static final String COURIER_PATH = "/courier";

    @Step("Отправить POST запрос на ручку /api/v1/courier/login")

    public ValidatableResponse login(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Отправить POST запрос на ручку /api/v1/courier")

    public ValidatableResponse create(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Отправить DELETE запрос на ручку /api/v1/courier/:id ")

    public ValidatableResponse delete(int courierId) {
        return spec()
                .body(Map.of("id", String.valueOf(courierId)))
                .when()
                .delete(COURIER_PATH + "/" + courierId)
                .then().log().all();
    }

    @Step("Получение id курьера")
    public int getCourierId(Courier courier) {
        var creds = Credentials.from(courier);
        ValidatableResponse loginResponse = login(creds);
        return loginResponse.extract().path("id");
    }
}
