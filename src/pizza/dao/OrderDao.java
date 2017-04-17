package pizza.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pizza.model.Order;
import pizza.orm.OrderRowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

/**
 * Created by Настя on 11.03.2017.
 */

//to do: testing
@Repository
public class OrderDao extends Dao {

    public static final String OPEN_STATUS = "OPEN";
    public static final String IN_PROGRESS_STATUS = "IN PROGRESS";
    public static final String READY_STATUS = "READY";
    public static final String CLOSE_STATUS = "CLOSE";

    private static final String CREATE_SQL =
            "INSERT INTO \"ORDER\" VALUES (:orderId, :orderStatus, :orderCreateTime)";
    private static final String GET_NOT_CLOSED_ORDERS = "SELECT * FROM \"ORDER\" WHERE ORDERSTATUS = :inProgressStatus OR " +
            "ORDERSTATUS = :readyStatus " +
            "ORDER BY CREATIONDATE";
    private static final String FINISH_ORDERS_SQL = "UPDATE \"ORDER\" o SET o.ORDERSTATUS = :orderStatus " +
            "WHERE NOT EXISTS(SELECT p.ID FROM PIZZA p WHERE p.ORDERID = o.ID AND p.PIZZASTATUS <> :pizzaReadyStatus)";

    @Autowired
    private OrderRowMapper orderRowMapper;

    @Autowired
    public OrderDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public void create(Order order) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", convertUUIDToOracleID(order.getId()))
                .addValue("orderStatus", order.getOrderStatus())
                .addValue("orderCreateTime", order.getCreationDate());
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }

    public List<Order> getNotClosedOrders() {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("inProgressStatus", IN_PROGRESS_STATUS)
                .addValue("readyStatus", READY_STATUS);
        return orderRowMapper.getEntityList(getNamedParameterJdbcTemplate().queryForList(GET_NOT_CLOSED_ORDERS, params));
    }

    public void finishOrders() {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(UUID.randomUUID()))
                .addValue("orderStatus", READY_STATUS)
                .addValue("pizzaReadyStatus", PizzaDao.READY_STATUS);
        getNamedParameterJdbcTemplate().update(FINISH_ORDERS_SQL, params);
    }
}
