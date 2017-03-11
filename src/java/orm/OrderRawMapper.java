package java.orm;

import java.model.Order;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Настя on 11.03.2017.
 */
public class OrderRawMapper  implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getString("ORDER_STATUS")
        );
    }

}
