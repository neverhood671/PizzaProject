package pizza.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pizza.model.Order;

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

    public static final String OPEN_STATUS = "OPEN";
    public static final String IN_PROGRESS_STATUS = "IN PROGRESS";
    public static final String READY_STATUS = "READY";
    public static final String CLOSE_STATUS = "CLOSE";

    private static final String CREATE_SQL =
            "INSERT INTO \"ORDER\" VALUES (:orderId, :orderStatus, :orderCreateTime)";
    private static final String GET_ALL_SQL = "SELECT * FROM \"ORDER\"";
    private static final String GET_SQL = "SELECT * FROM ORDER WHERE ID = :orderId";
    private static final String UPDATE_STATUS_SQL =
            "UPDATE \"ORDER\" SET ORDERSTATUS = :orderStatus WHERE ID = :orderId";

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

    public void create(String orderStatus) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(UUID.randomUUID()))
                .addValue("orderStatus", orderStatus);
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }


    public List<Map<String, Object>> getList() {
        return getNamedParameterJdbcTemplate().queryForList(GET_ALL_SQL, new HashMap<String, Object>());
    }

    public Map<String, Object> get(UUID orderId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", convertUUIDToOracleID(orderId));
        return getNamedParameterJdbcTemplate().queryForMap(GET_SQL, params);
    }

    public void updateStatus(Order order) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", convertUUIDToOracleID(order.getId()))
                .addValue("orderStatus", order.getOrderStatus());
        getNamedParameterJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }

    public void updateStatus(UUID orderID, String status) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("orderId", convertUUIDToOracleID(orderID))
                .addValue("orderStatus", status);
        getNamedParameterJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }


//    public String  getOrderStatus(UUID orderId){
//        get(orderId)
//    }
}
