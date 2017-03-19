package pizza.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pizza.model.Order;
import pizza.model.Pizza;
import pizza.orm.OrderRawMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Настя on 11.03.2017.
 */

//to do: testing
@Repository
public class OrderDao extends Dao {

    private OrderRawMapper orderRawMapper;

    private static final String CREATE_SQL =
            "INSERT INTO \"ORDER\" VALUES (:orderId, :orderStatus)";
    private static final String GET_ALL_SQL = "SELECT * FROM \"ORDER\"";
    private static final String GET_SQL = "SELECT * FROM ORDER WHERE ID = :orderId";
    private static final String UPDATE_STATUS_SQL =
            "UPDATE \"ORDER\" SET ORDER_STATUS = :orderStatus WHERE ID = :orderId";

    @Autowired
    public OrderDao(DataSource dataSource, JdbcTemplate jdbcTemplate, OrderRawMapper orderRawMapper) {
        super(dataSource, jdbcTemplate);
        this.orderRawMapper = orderRawMapper;
    }

    public void create(Order order) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", convertUUIDToOracleID(order.getId()))
                .addValue("orderStatus", order.getOrderStatus());
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }

    public void create(String orderStatus) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(UUID.randomUUID()))
                .addValue("orderStatus", orderStatus);
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }


    public List<Map<String, Object>> getList() {
        return getNamedParameterJdbcTemplate().queryForList(GET_ALL_SQL, new HashMap<String, Object>());
    }

    public Order get(UUID orderId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", convertUUIDToOracleID(orderId));
        List<Order> orders = getNamedParameterJdbcTemplate().query(GET_SQL, params, orderRawMapper);
        return orders.isEmpty() ? null : orders.get(0);
    }

    public void updateStatus(Order order, String status) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", convertUUIDToOracleID(order.getId()))
                .addValue("orderStatus", order.getOrderStatus());
        getNamedParameterJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }


}
