package pizza.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pizza.model.Order;
import pizza.orm.OrderRawMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Настя on 11.03.2017.
 */
@Repository
public class OrderDao extends Dao {

    private OrderRawMapper orderRawMapper;

    private final String OPEN_STATUS = "OPEN";
    private final String IN_PROGRESS_STATUS = "IN_PROGRESS";
    private final String READY_STATUS = "READY";


    private static final String CREATE_SQL =
            "INSERT INTO ORDER VALUES (:ID, :ORDER_STATUS)";
    private static final String GET_ALL_SQL = "SELECT * FROM ORDER";
    private static final String GET_SQL = "SELECT * FROM ORDER WHERE ID = :ID";
    private static final String UPDATE_STATUS_SQL = "UPDATE Order SET STATUS = :STATUS WHERE ID = :ID";

    @Autowired
    public OrderDao(DataSource dataSource, JdbcTemplate jdbcTemplate, OrderRawMapper orderRawMapper) {
        super(dataSource, jdbcTemplate);
        this.orderRawMapper = orderRawMapper;
    }

    public void create(Order order) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", convertUUIDToOracleID(order.getId()))
                .addValue("order_status", order.getOrder_status());
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }


    public List<Order> getList() {
        return getJdbcTemplate().query(GET_ALL_SQL, orderRawMapper, new HashMap<String, Object>());
    }


    public Order get(UUID OrderId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", OrderId);
        List<Order> locations = getJdbcTemplate().query(GET_SQL, orderRawMapper, params);
        return locations.isEmpty() ? null : locations.get(0);
    }


    public void updateStatus(Order order, String status) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("order_status", order.getOrder_status());
        getNamedParameterJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }


}
