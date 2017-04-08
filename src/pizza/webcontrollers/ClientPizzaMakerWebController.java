package pizza.webcontrollers;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pizza.dao.CookDao;
import pizza.dao.PizzaDao;
import pizza.dao.PizzaTypeDao;
import pizza.model.Cook;
import pizza.model.Pizza;
import pizza.model.PizzaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Настя on 05.04.2017.
 */
@Controller
public class ClientPizzaMakerWebController {

    @Autowired
    private CookDao cookDao;
    @Autowired
    private PizzaDao pizzaDao;
    @Autowired
    private PizzaTypeDao pizzaTypeDao;

    @RequestMapping(value = "/pizzaMaker", method = RequestMethod.GET)
    public ModelAndView getClientStartPage() throws Exception {
        return new ModelAndView("pizza_maker");
    }

    @RequestMapping(value = "/pizzaMaker/getLoginStatus", method = RequestMethod.GET)
    public
    @ResponseBody
    String getLoginStatus(HttpServletRequest request,
                          HttpServletResponse httpServletResponse) throws Exception {
        Cook pizzaMaker = (Cook) request.getSession().getAttribute("cook");
        return getLoginStatus(pizzaMaker).toString();
    }

    private JsonObject getLoginStatus(Cook pizzaMaker) {
        if (pizzaMaker != null) {
            JsonObject status = new JsonObject();
            status.addProperty("status", "SUCCESS");
            status.addProperty("username", pizzaMaker.getName());
            return status;
        }
        JsonObject status = new JsonObject();
        status.addProperty("status", "LOGIN");
        return status;
    }

    @RequestMapping(value = "/pizzaMaker/doLogin", method = RequestMethod.POST)
    public
    @ResponseBody
    String doLogin(HttpServletRequest request,
                   HttpServletResponse httpServletResponse,
                   @RequestParam(value = "login", required = false) String login,
                   @RequestParam(value = "password", required = false) String password) throws Exception {
        Cook pizzaMaker = (Cook) request.getSession().getAttribute("cook");
        if (pizzaMaker != null) {
            if (ObjectUtils.nullSafeEquals(login, pizzaMaker.getName())) {
                return getLoginStatus(pizzaMaker).toString();
            } else {
                request.getSession().removeAttribute("cook");
            }
        }

        if (!StringUtils.isEmpty(login)) {
            try {
                pizzaMaker = cookDao.getCookFromLogin(login);
                if (pizzaMaker != null) {
                    request.getSession().setAttribute("cook", pizzaMaker);
                    if (pizzaDao.getPizzaForCook(pizzaMaker.getId()) == null) {
                        cookDao.updateStatus(pizzaMaker.getId(), CookDao.FREE_STATUS);
                    }
                    return getLoginStatus(pizzaMaker).toString();
                }
            } catch (Exception ignored) {
            }
        }

        return getErrorJSON().toString();
    }

    private JsonObject getErrorJSON() {
        JsonObject status = new JsonObject();
        status.addProperty("status", "ERROR");
        return status;
    }

    @RequestMapping(value = "/pizzaMaker/logout", method = RequestMethod.POST)
    public
    @ResponseBody
    String logout(HttpServletRequest request,
                  HttpServletResponse httpServletResponse) throws Exception {
        Cook pizzaMaker = (Cook) request.getSession().getAttribute("cook");
        if (pizzaMaker != null) {
            cookDao.updateStatus(pizzaMaker.getId(), CookDao.BUSY_STATUS);
            request.getSession().removeAttribute("cook");
            request.getSession().removeAttribute("inProgressPizza");
            request.getSession().removeAttribute("inProgressPizzaType");
        }
        return getLoginStatus(null).toString();
    }

    @RequestMapping(value = "/pizzaMaker/getPizza", method = RequestMethod.GET)
    public
    @ResponseBody
    String getPizza(HttpServletRequest request,
                    HttpServletResponse httpServletResponse) throws Exception {
        Cook pizzaMaker = (Cook) request.getSession().getAttribute("cook");
        Pizza inProgressPizza = (Pizza) request.getSession().getAttribute("inProgressPizza");
        if (inProgressPizza != null) {
            PizzaType inProgressPizzaType = (PizzaType) request.getSession().getAttribute("inProgressPizzaType");
            if (inProgressPizzaType != null) {

                return createGetPizzaStatus(inProgressPizza, inProgressPizzaType).toString();
            }
        }

        inProgressPizza = pizzaDao.getPizzaForCook(pizzaMaker.getId());
        if (inProgressPizza != null) {
            PizzaType inProgressPizzaType = pizzaTypeDao.getPizzaType(inProgressPizza.getPizzaTypeId());
            if (inProgressPizzaType != null) {
                request.getSession().setAttribute("inProgressPizza", inProgressPizza);
                request.getSession().setAttribute("inProgressPizzaType", inProgressPizzaType);

                return createGetPizzaStatus(inProgressPizza, inProgressPizzaType).toString();
            }
        }

        JsonObject status = new JsonObject();
        status.addProperty("status", "EMPTY");
        return status.toString();
    }

    private JsonObject createGetPizzaStatus(Pizza inProgressPizza, PizzaType inProgressPizzaType) {
        JsonObject status = new JsonObject();
        status.addProperty("status", "NEW");
        status.addProperty("pizzaName", inProgressPizzaType.getName());
        status.addProperty("pizzaNameLocale", inProgressPizzaType.getName());
        status.addProperty("pizzaSize", inProgressPizza.getPizzaSize());
        return status;
    }

    @RequestMapping(value = "/pizzaMaker/finishPizza", method = RequestMethod.GET)
    public
    @ResponseBody
    String finishPizza(HttpServletRequest request,
                       HttpServletResponse httpServletResponse) throws Exception {
        Cook pizzaMaker = (Cook) request.getSession().getAttribute("cook");
        Pizza inProgressPizza = (Pizza) request.getSession().getAttribute("inProgressPizza");
        if (pizzaMaker != null && inProgressPizza != null) {
            pizzaDao.updateStatus(inProgressPizza.getId(), PizzaDao.READY_STATUS);
            cookDao.finishPizza(pizzaMaker);

            request.getSession().removeAttribute("inProgressPizza");
            request.getSession().removeAttribute("inProgressPizzaType");

            JsonObject status = new JsonObject();
            status.addProperty("status", PizzaDao.READY_STATUS);
            return status.toString();
        }
        return getErrorJSON().toString();
    }

}
