package pizza.webcontrollers;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pizza.dao.OrderDao;
import pizza.json.EntityToJson;
import pizza.model.Order;

import java.util.List;

/**
 * Created by Настя on 13.04.2017.
 */
@Controller
public class CleintOrdersPageWebController {

    @Autowired
    private EntityToJson entityToJson;
    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView getClientStartPage() throws Exception {
        return new ModelAndView("orders");
    }

    @RequestMapping(value = "/orders/getOrders", method = RequestMethod.GET)
    public
    @ResponseBody
    String getOrders() {
        try {
            List<Order> orders = orderDao.getNotClosedOrders();
            if (orders != null) {
                JsonObject status = new JsonObject();
                status.addProperty("status", "SUCCESS");
                status.add("orders", entityToJson.entityListToJsonArray(orders));
                return status.toString();
            }
        } catch (Exception ignored) {
        }
        return getErrorJSON().toString();
    }

    private JsonObject getErrorJSON() {
        JsonObject status = new JsonObject();
        status.addProperty("status", "ERROR");
        return status;
    }

}
