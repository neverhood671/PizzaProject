package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class PizzaType extends Entity {

    private String name;
    private double priceS;
    private double priceM;
    private double priceL;

    public PizzaType(String name, double priceS, double priceM, double priceL) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.priceS = priceS;
        this.priceM = priceM;
        this.priceL = priceL;
    }

    public PizzaType(UUID id, String name, double priceS, double priceM, double priceL) {
        this.id = id;
        this.name = name;
        this.priceS = priceS;
        this.priceM = priceM;
        this.priceL = priceL;
    }

    public PizzaType() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}




