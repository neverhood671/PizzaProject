package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Cook extends Entity {

    private String name;
    private String cookStatus;

    public Cook() {

    }

    public Cook(String name, String cookStatus) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cookStatus = cookStatus;
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
}
