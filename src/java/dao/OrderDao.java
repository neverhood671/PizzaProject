package java.dao;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.model.Order;
import java.orm.OrderRawMapper;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Настя on 11.03.2017.
 */
@Repository
public class OrderDao extends JdbcDaoSupport {

    @Autowired
    private OracleDataSource dataSource;
    private OrderRawMapper OrderRowMapper;


    public OrderDao(OracleDataSource dataSource, OrderRawMapper OrderRowMapper) {
        this.dataSource = dataSource;
        this.OrderRowMapper = OrderRowMapper;
    }

    private final String OPEN_STATUS = "OPEN";
    private final String IN_PROGRESS_STATUS = "IN_PROGRESS";
    private final String READY_STATUS = "READY";


    private static final String CREATE_SQL =
            "INSERT INTO ORDER VALUES (:ID, :ORDER_STATUS)";
    private static final String GET_ALL_SQL = "SELECT * FROM ORDER";
    private static final String GET_SQL = "SELECT * FROM ORDER WHERE ID = :ID";
    private static final String UPDATE_STATUS_SQL = "UPDATE Order SET STATUS = :STATUS WHERE ID = :ID";


    public void create(Order order) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("order_status", order.getOrder_status());
        getJdbcTemplate().update(CREATE_SQL, params);
    }


    public List<Order> getList() {
        return getJdbcTemplate().query(GET_ALL_SQL, OrderRowMapper, new HashMap<String, Object>());
    }


    public Order get(UUID OrderId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", OrderId);
        List<Order> locations = getJdbcTemplate().query(GET_SQL, OrderRowMapper, params);
        return locations.isEmpty() ? null : locations.get(0);
    }


    public void updateStatus(Order order, String status) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("order_status", order.getOrder_status());
        getJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }


}
