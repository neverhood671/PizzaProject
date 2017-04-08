package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Cook extends Entity {

    private String name;
    private String cookStatus;
    private int pizzaCount;

    public Cook() {

    }

    public Cook(String name, String cookStatus) {
        this(name, cookStatus, 0);
    }

    public Cook(String name, String cookStatus, int pizzaCount) {
        this(UUID.randomUUID(), name, cookStatus, pizzaCount);
    }

    public Cook(UUID id, String name, String cookStatus, int pizzaCount) {
        this.id = id;
        this.name = name;
        this.cookStatus = cookStatus;
        this.pizzaCount = pizzaCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookStatus() {
        return cookStatus;
    }

    public void setCookStatus(String cookStatus) {
        this.cookStatus = cookStatus;
    }

    public int getPizzaCount() {
        return pizzaCount;
    }

    public void setPizzaCount(int pizzaCount) {
        this.pizzaCount = pizzaCount;
    }
}
