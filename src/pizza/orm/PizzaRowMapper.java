package pizza.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.dao.PizzaDao;
import pizza.model.Pizza;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Настя on 11.03.2017.
 */
@Service
public class PizzaRowMapper extends RowMapper<Pizza> {

    @Autowired
    private PizzaDao dao;

    @Override
    public Pizza getEntity(Map<String, Object> pizza) throws SQLException {
        return new Pizza(
                dao.convertOracleIDToUUID((String) pizza.get("ID")),
                (String) pizza.get("PIZZASIZE"),
                dao.convertOracleIDToUUID((String) pizza.get("PIZZATYPEID")),
                (String) pizza.get("PIZZASTATUS"),
                dao.convertOracleIDToUUID((String) pizza.get("ORDERID")),
                dao.convertOracleIDToUUID((String) pizza.get("COOKID")));
    }

}
