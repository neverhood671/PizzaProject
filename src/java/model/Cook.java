package java.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class Cook extends Entity {

    private String name;
    private String cook_status;

    public Cook() {

    }

    public Cook(String name, String cook_status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cook_status = cook_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCook_status() {
        return cook_status;
    }

    public void setCook_status(String cook_status) {
        this.cook_status = cook_status;
    }
}
