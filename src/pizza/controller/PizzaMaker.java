package pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.dao.CookDao;
import pizza.dao.OrderDao;
import pizza.dao.PizzaDao;
import pizza.model.Order;
import pizza.model.Pizza;

import java.util.UUID;


/**
 * Created by Настя on 19.03.2017.
 */
@Service
public class PizzaMaker {

    private static final String OPEN_STATUS = "OPEN";
    private static final String IN_PROGRESS_STATUS = "IN_PROGRESS";
    private static final String READY_STATUS = "READY_STATUS";

    private static final String FREE_STATUS = "FREE";


    @Autowired
    private PizzaDao pizzaDao;
    @Autowired
    private CookDao cookDao;
    @Autowired
    private OrderDao orderDao;


    public void createPizza(Order order) {


    }

    public void finishPizza(Pizza pizza) {
        pizzaDao.updateStatus(pizza.getId(), READY_STATUS);
        cookDao.updateStatus(pizza.getCookId(), FREE_STATUS);
        if (isOrderReady(pizza.getOrderId())) {
            orderDao.updateStatus(pizza.getOrderId(), READY_STATUS);
        }
    }

    private boolean isOrderReady(UUID orderId) {
        return true;
    }
}
