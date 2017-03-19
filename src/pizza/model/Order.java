package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Order extends Entity{

    private String orderStatus;

    public Order() {

    }

    public Order(String orderStatus) {
        this.id = UUID.randomUUID();
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
