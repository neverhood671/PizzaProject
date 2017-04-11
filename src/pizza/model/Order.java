package pizza.model;


import java.util.Date;
import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Order extends Entity {

    private String orderStatus;
    private Date creationDate;

    public Order() {

    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Order(String orderStatus, Date creationDate) {
        this.id = UUID.randomUUID();
        this.orderStatus = orderStatus;
        this.creationDate = creationDate;
    }

    public Order(UUID id, String orderStatus, Date creationDate) {
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
