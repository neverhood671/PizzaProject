package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Order extends Entity{

    private String order_status;

    public Order() {

    }

    public Order(String order_status) {
        this.id = UUID.randomUUID();
        this.order_status = order_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
