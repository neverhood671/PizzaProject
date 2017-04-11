package pizza.webcontrollers.sessionobjects;

import java.io.Serializable;

/**
 * Created by Настя on 11.04.2017.
 */
public class OrderedPizzaType implements Serializable {
    public String pizzaName;
    public String pizzaSize;
    public int pizzaCount;
}
