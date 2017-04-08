package pizza.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.dao.PizzaTypeDao;
import pizza.model.PizzaType;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Настя on 02.04.2017.
 */
@Service
public class PizzaTypeRowMapper extends RowMapper<PizzaType> {

    @Autowired
    private PizzaTypeDao dao;

    @Override
    public PizzaType getEntity(Map<String, Object> pizza) throws SQLException {
        return new PizzaType(
                dao.convertOracleIDToUUID((String) pizza.get("ID")),
                (String) pizza.get("NAME"),
                ((BigDecimal) pizza.get("PRICES")).doubleValue(),
                ((BigDecimal) pizza.get("PRICEM")).doubleValue(),
                ((BigDecimal) pizza.get("PRICEL")).doubleValue());
    }

}
