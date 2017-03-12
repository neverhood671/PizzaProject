package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Pizza extends Entity {

    private String size;
    private UUID pizza_type_id;
    private String pizza_status;
    private UUID order_id;
    private UUID cook_id;

    public Pizza() {

    }
    public Pizza(String size, UUID pizza_type_id, String pizza_status, UUID order_id, UUID cook_id) {
        this.id = UUID.randomUUID();
        this.size = size;
        this.pizza_type_id = pizza_type_id;
        this.pizza_status = pizza_status;
        this.order_id = order_id;
        this.cook_id = cook_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public UUID getPizza_type_id() {
        return pizza_type_id;
    }

    public void setPizza_type_id(UUID pizza_type_id) {
        this.pizza_type_id = pizza_type_id;
    }

    public String getPizza_status() {
        return pizza_status;
    }

    public void setPizza_status(String pizza_status) {
        this.pizza_status = pizza_status;
    }

    public UUID getOrder_id() {
        return order_id;
    }

    public void setOrder_id(UUID order_id) {
        this.order_id = order_id;
    }

    public UUID getCook_id() {
        return cook_id;
    }

    public void setCook_id(UUID cook_id) {
        this.cook_id = cook_id;
    }
}
