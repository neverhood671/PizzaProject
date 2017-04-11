package pizza.webcontrollers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pizza.webcontrollers.sessionobjects.OrderedPizzaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Настя on 04.04.2017.
 */
@Controller
public class ClientBasketWebController {

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ModelAndView getClientStartPage() throws Exception {
        return new ModelAndView("basket");
    }

    @RequestMapping(value = "/basket/getBasket", method = RequestMethod.GET)
    public
    @ResponseBody
    String getBasket(HttpServletRequest request,
                     HttpServletResponse httpServletResponse) {
        try {
            List<OrderedPizzaType> orderedPizzaTypes =
                    (List<OrderedPizzaType>) request.getSession().getAttribute("orderedPizzaTypes");
            if (orderedPizzaTypes != null) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                return gson.toJson(orderedPizzaTypes);
            }
        } catch (Exception ignored) {
        }
        return getErrorJSON().toString();
    }

    @RequestMapping(value = "/basket/goToPay", method = RequestMethod.POST)
    public
    @ResponseBody
    String goToPay(HttpServletRequest request,
                   HttpServletResponse httpServletResponse) throws Exception {
        try {
            List<OrderedPizzaType> orderedPizzaTypes =
                    (List<OrderedPizzaType>) request.getSession().getAttribute("orderedPizzaTypes");
            if (!CollectionUtils.isEmpty(orderedPizzaTypes)) {
                JsonObject status = new JsonObject();
                status.addProperty("status", "SUCCESS");
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
