package pizza.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import pizza.dao.CookDao;
import pizza.dao.OrderDao;
import pizza.dao.PizzaDao;
import pizza.model.Pizza;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Настя on 12.04.2017.
 */
@Controller
public class PizzaProcessor {

    @Autowired
    private PizzaDao pizzaDao;
    @Autowired
    private CookDao cookDao;
    @Autowired
    private OrderDao orderDao;

    @Scheduled(cron = "*/5 * * * * ?")
    public void processPizza() {
        assignPizza();
        finishOrders();
    }

    private void assignPizza() {
        List<Pizza> openedPizzas = pizzaDao.getOpenPizza();
        if (!CollectionUtils.isEmpty(openedPizzas)) {
            List<UUID> freeCookId = cookDao.getFreeCooksID(openedPizzas.size());
            if (!CollectionUtils.isEmpty(freeCookId)) {
                Iterator<Pizza> pizzaIterator = openedPizzas.iterator();
                for (UUID cookId : freeCookId) {
                    if (!pizzaIterator.hasNext())
                        break;
                    Pizza pizza = pizzaIterator.next();
                    pizzaDao.assignPizza(pizza.getId(), cookId, PizzaDao.IN_PROGRESS_STATUS);
                    cookDao.updateStatus(cookId, CookDao.BUSY_STATUS);
                }
            }
        }
    }

    private void finishOrders() {
        orderDao.finishOrders();
    }

}
