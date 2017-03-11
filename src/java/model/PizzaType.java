package java.model;


import java.util.UUID;

/**
 * Created by Alexandra on 25.02.2017.
 */
public class PizzaType extends Entity {

    private String name;
    private double prise_s;
    private double prise_m;
    private double prise_l;

    public PizzaType(String name, double prise_s, double prise_m, double prise_l) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.prise_s = prise_s;
        this.prise_m = prise_m;
        this.prise_l = prise_l;
    }

    public PizzaType() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrise_s() {
        return prise_s;
    }

    public void setPrise_s(double prise_s) {
        this.prise_s = prise_s;
    }

    public double getPrise_m() {
        return prise_m;
    }

    public void setPrise_m(double prise_m) {
        this.prise_m = prise_m;
    }

    public double getPrise_l() {
        return prise_l;
    }

    public void setPrise_l(double prise_l) {
        this.prise_l = prise_l;
    }
}




