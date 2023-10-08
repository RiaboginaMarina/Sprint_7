package org.example.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

public class OrderClient extends Client {
    public static final String ORDER_PATH = "/orders";

    @Step("Отправить POST запрос на ручку /api/v1/orders")
    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all()
                ;
    }

    @Step("Отправить GET запрос на ручку /api/v1/orders")
    public ValidatableResponse getOrderList() {
        return spec()
                .get(ORDER_PATH)
                .then().log().all()
                ;
    }
}
