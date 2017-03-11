package java.dao;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.model.Pizza;
import java.orm.PizzaRawMapper;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * Created by Настя on 05.03.2017.
 */
@Repository
public class PizzaDao extends JdbcDaoSupport {

    @Autowired
    private OracleDataSource dataSource;
    private PizzaRawMapper pizzaRowMapper;


    public PizzaDao(OracleDataSource dataSource, PizzaRawMapper pizzaRowMapper) {
        this.dataSource = dataSource;
        this.pizzaRowMapper = pizzaRowMapper;
    }

    private final String OPEN_STATUS = "OPEN";
    private final String IN_PROGRESS_STATUS = "IN_PROGRESS";
    private final String READY_STATUS = "READY";


    private static final String CREATE_SQL =
            "INSERT INTO PIZZA VALUES (:ID, :SIZE, :PIZZA_TYPE_ID, :STATUS, :ORDER_ID, :COOK_ID)";
    private static final String GET_ALL_SQL = "SELECT * FROM PIZZA";
    private static final String GET_SQL = "SELECT * FROM PIZZA WHERE ID = :ID";
    private static final String UPDATE_STATUS_SQL = "UPDATE PIZZA SET STATUS = :STATUS WHERE ID = :ID";
    private static final String UPDATE_COOK_SQL = "UPDATE PIZZA SET COOK = :COOK WHERE ID = :ID";


    public void create(Pizza pizza) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", pizza.getId())
                .addValue("size", pizza.getSize())
                .addValue("pizza_type_id", pizza.getPizza_type_id())
                .addValue("status", pizza.getPizza_status())
                .addValue("order_id", pizza.getOrder_id())
                .addValue("cook_id", pizza.getCook_id());
        getJdbcTemplate().update(CREATE_SQL, params);
    }


    public List<Pizza> getList() {
        return getJdbcTemplate().query(GET_ALL_SQL, pizzaRowMapper, new HashMap<String, Object>());
    }


    public Pizza get(UUID pizzaId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", pizzaId);
        List<Pizza> locations = getJdbcTemplate().query(GET_SQL, pizzaRowMapper, params);
        return locations.isEmpty() ? null : locations.get(0);
    }


    public void updateStatus(Pizza pizza, String status) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", pizza.getId())
                .addValue("size", pizza.getSize())
                .addValue("pizza_type_id", pizza.getPizza_type_id())
                .addValue("status", status)
                .addValue("order_id", pizza.getOrder_id())
                .addValue("cook_id", pizza.getCook_id());
        getJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }


    public void updateCook(Pizza pizza, String cook) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", pizza.getId())
                .addValue("size", pizza.getSize())
                .addValue("pizza_type_id", pizza.getPizza_type_id())
                .addValue("status", pizza.getPizza_status())
                .addValue("order_id", pizza.getOrder_id())
                .addValue("cook_id", cook);
        getJdbcTemplate().update(UPDATE_COOK_SQL, params);
    }

}
