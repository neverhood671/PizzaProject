package java;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Cook {
    private String cookId;
    private String name;
    private String pizzaId;
    private String cookStatus;

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getCookStatus() {
        return cookStatus;
    }

    public void setCookStatus(String cookStatus) {
        this.cookStatus = cookStatus;
    }
}
