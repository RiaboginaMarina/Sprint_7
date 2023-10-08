package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierValidations;
import org.junit.After;
import org.junit.Test;


public class CourierCreationTest {
    private final CourierClient client = new CourierClient();
    private final CourierValidations check = new CourierValidations();
    protected int courierId;

    @After
    public void deleteCourier() {
        client.delete(courierId);
    }

    @Test
    @DisplayName("Позитивный сценарий создания курьера")
    public void createCourier() {
        var courier = new Courier("AmelyLee", "Pass1234", "Marina");
        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
        courierId = client.getCourierId(courier);
    }

    @Test
    @DisplayName("Создание курьера без поля логин")
    public void createCourierWithoutLogin() {
        var courier = new Courier(null, "Pass1234", "Marina");
        ValidatableResponse response = client.create(courier);
        check.creationFailedWithoutFullData(response);
    }

    @Test
    @DisplayName("Создание курьера без поля пароль")
    public void createCourierWithoutPassword() {
        var courier = new Courier("AmelyLee", null, "Marina");
        ValidatableResponse response = client.create(courier);
        check.creationFailedWithoutFullData(response);
    }

    @Test
    @DisplayName("Создание двух идентичных курьеров")
    public void createTwoIdenticalCouriers() {
        var courier = new Courier("AmelyLee", "Pass1234", "Marina");
        client.create(courier);
        courierId = client.getCourierId(courier);
        ValidatableResponse response = client.create(courier);
        check.creationWithIdenticalLoginsFailed(response);
    }

    @Test
    @DisplayName("Создание двух курьеров с одинаковым логином")
    public void createTwoCouriersWithIdenticalLogin() {
        var courier = new Courier("AmelyLee", "Pass1234", "Marina");
        client.create(courier);
        courierId = client.getCourierId(courier);
        var courierNumberTwo = new Courier("AmelyLee", "Password1234", "Leonid");
        ValidatableResponse response = client.create(courierNumberTwo);
        check.creationWithIdenticalLoginsFailed(response);
    }

}
