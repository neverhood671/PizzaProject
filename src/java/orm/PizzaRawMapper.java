package java.orm;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.model.Pizza;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Настя on 11.03.2017.
 */
@Component
public class PizzaRawMapper implements RowMapper<Pizza> {

    @Override
    public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pizza(
                rs.getString("SIZE"),
                (UUID) rs.getObject("PIZZA_TYPE_ID"),
                rs.getString("PIZZA_STATUS"),
                (UUID) rs.getObject("ORDER_ID"),
                (UUID) rs.getObject("COOK_ID"));
    }


}
