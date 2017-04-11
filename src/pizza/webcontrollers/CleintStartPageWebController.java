package pizza.webcontrollers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pizza.dao.PizzaTypeDao;
import pizza.json.EntityToJson;
import pizza.webcontrollers.sessionobjects.OrderedPizzaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Настя on 27.03.2017.
 */
@Controller
public class CleintStartPageWebController {
    @Autowired
    private EntityToJson entityToJson;
    @Autowired
    private PizzaTypeDao pizzaTypeDao;

    @RequestMapping(value = "/startPage", method = RequestMethod.GET)
    public ModelAndView getClientStartPage() throws Exception {
        return new ModelAndView("client_start");
    }

    @RequestMapping(value = "/startPage/jsonPizzaTypeList", method = RequestMethod.GET)
    public
    @ResponseBody
    String getPizzaTypesJsonList() throws Exception {
        return entityToJson.entityListToJsonArray(pizzaTypeDao.getList()).toString();
    }

    @RequestMapping(value = "/startPage/createBasket", method = RequestMethod.POST)
    public
    @ResponseBody
    String createBasket(HttpServletRequest request,
                        HttpServletResponse httpServletResponse,
                        @RequestParam(value = "pizzaList") String pizzaList) throws Exception {
        try {
            Type type = new TypeToken<List<OrderedPizzaType>>() {
            }.getType();
            List<OrderedPizzaType> orderedPizzaTypes = new Gson().fromJson(pizzaList, type);
            if (!CollectionUtils.isEmpty(orderedPizzaTypes)) {
                request.getSession().setAttribute("orderedPizzaTypes", orderedPizzaTypes);

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
