package pizza.customer.interaction;

import pizza.model.Pizza;
import java.util.List;

/**
 * Created by Настя on 12.03.2017.
 */
public class OrderWorker {
    private List<Pizza> orderList;


    private final String OPEN_STATUS = "OPEN";
    private final String IN_PROGRESS_STATUS = "IN_PROGRESS";
    private final String READY_STATUS = "READY";

    public OrderWorker(){

    }

    public List<Pizza> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Pizza> orderList) {
        this.orderList = orderList;
    }


    public void addPizzaToOrder(Pizza pizza){

    }

    public void addPizzaToOrder(Pizza[] pizza){

    }

    public void deletePizzaFromOrder(Pizza pizza){

    }

    public void pay(){

    }





}
