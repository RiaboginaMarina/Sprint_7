package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.Order;
import org.example.order.OrderClient;
import org.example.order.OrderValidations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    private final OrderClient client = new OrderClient();
    private final OrderValidations check = new OrderValidations();
    private final List<String> color;

    public OrderCreationTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет самоката. Тестовые данные: {0}")
    public static Object[][] getColor() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()},
        };
    }

    @Test
    @DisplayName("Создание заказа с разными вариантами цвета самоката")
    public void createOrderWithDifferentOptionsOfScooterColor() {
        var order = new Order("Gale", "Wizard", "Waterdeep, 13 apt.", "4", "+7 555 777 37 37", 3, "2023-10-10", "this crown will be mine", color);
        ValidatableResponse response = client.create(order);
        int track = check.createdSuccessfullyReturnTrack(response);
        assert track != 0;
    }
}
