package pizza.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pizza.model.Pizza;
import pizza.orm.PizzaRawMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Настя on 05.03.2017.
 */
@Repository
public class PizzaDao extends Dao {

    private PizzaRawMapper pizzaRowMapper;


    @Autowired
    public PizzaDao(DataSource dataSource, JdbcTemplate jdbcTemplate, PizzaRawMapper pizzaRowMapper) {
        super(dataSource, jdbcTemplate);
        this.pizzaRowMapper = pizzaRowMapper;
    }

    private final String OPEN_STATUS = "OPEN";
    private final String IN_PROGRESS_STATUS = "IN_PROGRESS";
    private final String READY_STATUS = "READY";


    private static final String CREATE_SQL =
            "INSERT INTO PIZZA(ID, PIZZA_SIZE, PIZZA_TYPE_ID, PIZZA_STATUS, ORDER_ID, COOK_ID) " +
                    "VALUES (:pizzaId, :pizzaSize, :pizzaTypeId, :pizzaStatus, :orderId, :cookId)";
    private static final String GET_ALL_SQL = "SELECT ID, PIZZA_SIZE, PIZZA_TYPE_ID, PIZZA_STATUS, ORDER_ID, COOK_ID FROM PIZZA";
    private static final String GET_SQL = "SELECT ID, PIZZA_SIZE, PIZZA_TYPE_ID, PIZZA_STATUS, ORDER_ID, COOK_ID FROM PIZZA WHERE ID = :ID";
    private static final String UPDATE_STATUS_SQL = "UPDATE PIZZA SET PIZZA_STATUS = :pizzaStatus WHERE ID = :pizzaId";
    private static final String UPDATE_COOK_SQL = "UPDATE PIZZA SET COOK_ID = :cookId WHERE ID = :pizzaId";


    public void create(String size, String pizza_type_id, String pizza_status, String order_id, String cook_id) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(UUID.randomUUID()))
                .addValue("pizzaSize", size)
                .addValue("pizzaTypeId", pizza_type_id)
                .addValue("pizzaStatus", pizza_status)
                .addValue("orderId", order_id)
                .addValue("cookId", cook_id);
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }

    public void create(Pizza pizza) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId",  convertUUIDToOracleID(pizza.getId()))
                .addValue("pizzaSize", pizza.getPizza_size())
                .addValue("pizzaTypeId", convertUUIDToOracleID(pizza.getPizza_type_id()))
                .addValue("pizzaStatus", pizza.getPizza_status())
                .addValue("orderId",  convertUUIDToOracleID(pizza.getOrder_id()))
                .addValue("cookId",  convertUUIDToOracleID(pizza.getCook_id()));
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }


    public List<Map<String, Object>> getList() {
        return getNamedParameterJdbcTemplate().queryForList(GET_ALL_SQL, new HashMap<String, Object>());
    }


    public Pizza get(UUID pizzaId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("ID", convertUUIDToOracleID(pizzaId));
        List<Pizza> pizza = getNamedParameterJdbcTemplate().query(GET_SQL, params, pizzaRowMapper);
        return pizza.isEmpty() ? null : pizza.get(0);
    }


    public void updateStatus(UUID pizzaId, String pizzaStatus) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(pizzaId))
                .addValue("pizzaStatus", pizzaStatus);
        getNamedParameterJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }


    public void updateCook(UUID pizzaId, UUID cookId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(pizzaId))
                .addValue("cookId", convertUUIDToOracleID(cookId));
        getNamedParameterJdbcTemplate().update(UPDATE_COOK_SQL, params);
    }

}
