package pizza.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class PizzaType extends Entity {

    private String name;
    private double priseS;
    private double priseM;
    private double priseL;

    public PizzaType(String name, double priseS, double priseM, double priseL) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.priseS = priseS;
        this.priseM = priseM;
        this.priseL = priseL;
    }

    public PizzaType() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriseS() {
        return priseS;
    }

    public void setPriseS(double priseS) {
        this.priseS = priseS;
    }

    public double getPriseM() {
        return priseM;
    }

    public void setPriseM(double priseM) {
        this.priseM = priseM;
    }

    public double getPriseL() {
        return priseL;
    }

    public void setPriseL(double priseL) {
        this.priseL = priseL;
    }
}




