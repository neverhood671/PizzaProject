package pizza.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pizza.dao.PizzaTypeDao;
import pizza.json.EntityToJson;
import pizza.orm.PizzaTypeRowMapper;

/**
 * Created by Настя on 27.03.2017.
 */
@Controller
public class CleintStartPageWebController {
    @Autowired
    private EntityToJson entityToJson;
    @Autowired
    private PizzaTypeRowMapper mapper;
    @Autowired
    private PizzaTypeDao pizzaTypeDao;

    @RequestMapping(value = "/startPage", method = RequestMethod.GET)
    public ModelAndView getClientStartPage() throws Exception {
        return new ModelAndView("client_start");
    }

    @RequestMapping(value = "/data/jsonPizzaTypeList", method = RequestMethod.GET)
    public
    @ResponseBody
    String getPizzaTypesJsonList() throws Exception {
        return entityToJson.entityListToJsonArray(mapper.getEntityList(pizzaTypeDao.getList())).toString();
    }

}
