package pizza.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import pizza.dao.PizzaDao;
import pizza.model.Pizza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Настя on 11.03.2017.
 */
@Component
public class PizzaRawMapper implements RowMapper<Pizza> {

    @Autowired
    private PizzaDao dao;

    @Override
    public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pizza(
                rs.getString("PIZZA_SIZE"),
                dao.convertOracleIDToUUID(rs.getString("PIZZA_TYPE_ID")),
                rs.getString("PIZZA_STATUS"),
                dao.convertOracleIDToUUID(rs.getString("ORDER_ID")),
                dao.convertOracleIDToUUID(rs.getString("COOK_ID")));
    }


}
