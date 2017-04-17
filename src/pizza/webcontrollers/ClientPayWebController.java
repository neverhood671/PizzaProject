package pizza.webcontrollers;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pizza.dao.OrderDao;
import pizza.dao.PizzaDao;
import pizza.dao.PizzaTypeDao;
import pizza.model.Order;
import pizza.model.Pizza;
import pizza.model.PizzaType;
import pizza.webcontrollers.sessionobjects.OrderedPizzaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Настя on 11.04.2017.
 */
@Controller
public class ClientPayWebController {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private PizzaTypeDao pizzaTypeDao;
    @Autowired
    private PizzaDao pizzaDao;

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public ModelAndView getClientStartPage() throws Exception {
        return new ModelAndView("pay");
    }

    @RequestMapping(value = "/pay/pay", method = RequestMethod.POST)
    public
    @ResponseBody
    String getBasket(HttpServletRequest request,
                     HttpServletResponse httpServletResponse) {
        try {
            List<OrderedPizzaType> orderedPizzaTypes =
                    (List<OrderedPizzaType>) request.getSession().getAttribute("orderedPizzaTypes");
            if (orderedPizzaTypes != null) {
                Order newOrder = createNewOrder();
                orderDao.create(newOrder);

                createNewPizza(newOrder, orderedPizzaTypes).forEach(pizzaDao::create);

                request.getSession().setAttribute("order", newOrder);

                JsonObject status = new JsonObject();
                status.addProperty("status", "SUCCESS");
                status.addProperty("orderId", newOrder.getId().toString());
                return status.toString();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return getErrorJSON().toString();
    }

    private Order createNewOrder() {
        return new Order(UUID.randomUUID(), OrderDao.IN_PROGRESS_STATUS, new java.sql.Date(System.currentTimeMillis()));
    }

    private List<Pizza> createNewPizza(Order order, List<OrderedPizzaType> orderedPizzaTypes) {
        Map<String, PizzaType> pizzaTypeMap = pizzaTypeDao.getList().stream().
                collect(Collectors.toMap(PizzaType::getName, Function.identity()));

        List<Pizza> newPizzas = new ArrayList<>();
        for (OrderedPizzaType orderedPizzaType : orderedPizzaTypes) {
            for (int i = 0; i < orderedPizzaType.pizzaCount; i++) {
                newPizzas.add(
                        new Pizza(UUID.randomUUID(),
                                orderedPizzaType.pizzaSize,
                                pizzaTypeMap.get(orderedPizzaType.pizzaName).getId(),
                                PizzaDao.OPEN_STATUS,
                                order.getId(), null));
            }
        }
        return newPizzas;
    }

    private JsonObject getErrorJSON() {
        JsonObject status = new JsonObject();
        status.addProperty("status", "ERROR");
        return status;
    }

}
