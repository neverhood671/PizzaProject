package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Order extends Entity {

    private String orderStatus;
    private String creationDate;

    public Order() {

    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Order(String orderStatus, String creationDate) {
        this.id = UUID.randomUUID();
        this.orderStatus = orderStatus;
        this.creationDate = creationDate;
    }

    public Order(UUID id, String orderStatus, String creationDate) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.creationDate = creationDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
