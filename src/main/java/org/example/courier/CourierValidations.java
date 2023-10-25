package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CourierValidations {
    @Step("Сравнить код, статус и тело ответа с заданными")
    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .body("ok", is(true))
        ;

    }

    @Step("Сравнить код и статус ответа с заданными. Тело ответа содержит id")

    public void loggedInSuccessfully(ValidatableResponse loginResponse) {
        loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("id", notNullValue())
        ;

    }

    @Step("Сравнить код, статус и тело ответа с заданными при запросе без логина или пароля")
    public void creationFailedWithoutFullData(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"))
        ;
    }

    @Step("Сравнить код, статус и тело ответа с заданными при запросе с повторяющимся логином")
    public void creationWithIdenticalLoginsFailed(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .body("message", is("Этот логин уже используется"))
        ;
    }

    @Step("Сравнить код, статус и тело ответа с заданными при запросе без логина или пароля")
    public void logInFailedWithoutAllData(ValidatableResponse loginResponse) {
        loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"))
        ;
    }

    @Step("Сравнить код, статус и тело ответа с заданными при запросе с несуществующими логином/паролем")
    public void logInFailedDueWrongData(ValidatableResponse loginResponse) {
        loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message", is("Учетная запись не найдена"))
        ;
    }

}
