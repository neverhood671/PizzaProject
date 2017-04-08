package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Pizza extends Entity {

    private String pizzaSize;
    private UUID pizzaTypeId;
    private String pizzaStatus;
    private UUID orderId;
    private UUID cookId;

    public Pizza() {

    }

    public Pizza(String size, UUID pizzaTypeId, String pizzaStatus, UUID orderId, UUID cookId) {
        this.id = UUID.randomUUID();
        this.pizzaSize = size;
        this.pizzaTypeId = pizzaTypeId;
        this.pizzaStatus = pizzaStatus;
        this.orderId = orderId;
        this.cookId = cookId;
    }


    public Pizza(String size, String pizzaTypeId, String pizzaStatus, String orderId, String cookId) {
        this.id = UUID.randomUUID();
        this.pizzaSize = size;
        this.pizzaTypeId = UUID.fromString(pizzaTypeId);
        this.pizzaStatus = pizzaStatus;
        this.orderId = UUID.fromString(orderId);
        this.cookId = UUID.fromString(cookId);
    }

    public Pizza(UUID id, String size, UUID pizzaTypeId, String pizzaStatus, UUID orderId, UUID cookId) {
        this.id = id;
        this.pizzaSize = size;
        this.pizzaTypeId = pizzaTypeId;
        this.pizzaStatus = pizzaStatus;
        this.orderId = orderId;
        this.cookId = cookId;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public UUID getPizzaTypeId() {
        return pizzaTypeId;
    }

    public void setPizzaTypeId(UUID pizzaTypeId) {
        this.pizzaTypeId = pizzaTypeId;
    }

    public String getPizzaStatus() {
        return pizzaStatus;
    }

    public void setPizzaStatus(String pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getCookId() {
        return cookId;
    }

    public void setCookId(UUID cookId) {
        this.cookId = cookId;
    }
}
