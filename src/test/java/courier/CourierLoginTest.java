package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierValidations;
import org.example.courier.Credentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {
    private final CourierClient client = new CourierClient();
    private final CourierValidations check = new CourierValidations();
    protected int courierId;

    @Before
    public void createCourier() {
        var courier = new Courier("AmelyLee", "Pass1234", "Marina");
        client.create(courier);
        courierId = client.getCourierId(courier);
    }

    @After
    public void deleteCourier() {
        client.delete(courierId);
    }

    @Test
    @DisplayName("Позитивный сценарий авторизации курьера")
    public void courierLoggedIn() {
        var creds = new Credentials("AmelyLee", "Pass1234");
        ValidatableResponse loginResponse = client.login(creds);
        check.loggedInSuccessfully(loginResponse);
    }

    @Test
    @DisplayName("Авторизация с неправильным паролем")
    public void courierLoggedInWithWrongPassword() {
        var creds = new Credentials("AmelyLee", "Password1234");
        ValidatableResponse loginResponse = client.login(creds);
        check.logInFailedDueWrongData(loginResponse);
    }

    @Test
    @DisplayName("Авторизация с неправильным логином")
    public void courierLoggedInWithWrongLogin() {
        var creds = new Credentials("Amely", "Pass1234");
        ValidatableResponse loginResponse = client.login(creds);
        check.logInFailedDueWrongData(loginResponse);
    }

    @Test
    @DisplayName("Авторизация без поля логин")
    public void courierLoggedInWithoutLogin() {
        var creds = new Credentials(null, "Pass1234");
        ValidatableResponse loginResponse = client.login(creds);
        check.logInFailedWithoutAllData(loginResponse);
    }

    @Test
    @DisplayName("Авторизация без поля пароль")
    public void courierLoggedInWithoutPassword() {
        var creds = new Credentials("AmelyLee", null);
        ValidatableResponse loginResponse = client.login(creds);
        check.logInFailedWithoutAllData(loginResponse);
    }

    @Test
    @DisplayName("Авторизация под несуществующим пользователем")
    public void loggedInNonExistUser() {
        var creds = new Credentials("AmelyLee123", "Pass1234");
        ValidatableResponse loginResponse = client.login(creds);
        check.logInFailedDueWrongData(loginResponse);
    }

}
