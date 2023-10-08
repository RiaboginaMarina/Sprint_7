package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.OrderClient;
import org.example.order.OrderValidations;
import org.junit.Test;

public class GetOrdersListTest {
    private final OrderClient client = new OrderClient();
    private final OrderValidations check = new OrderValidations();

    @Test
    @DisplayName("При получении списка заказов в теле ответа возвращается список заказов")
    public void gettingOrdersListReturnListOfOrders() {
        ValidatableResponse response = client.getOrderList();
        check.getOrderListSuccessfully(response);
    }
}
