package pizza.orm;

import org.springframework.stereotype.Component;
import pizza.model.Order;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Настя on 11.03.2017.
 */
@Component
public class OrderRawMapper  implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getString("ORDER_STATUS")
        );
    }

}
