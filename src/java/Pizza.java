package java;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Pizza {
    private String pizzaId;
    private String size;
    private String pizzaTypeId;

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPizzaTypeId() {
        return pizzaTypeId;
    }

    public void setPizzaTypeId(String pizzaTypeId) {
        this.pizzaTypeId = pizzaTypeId;
    }

    public String getPizzaStatus() {
        return pizzaStatus;
    }

    public void setPizzaStatus(String pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    String pizzaStatus;
}
