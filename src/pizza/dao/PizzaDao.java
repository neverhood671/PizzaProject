package pizza.dao;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pizza.model.Pizza;
import pizza.orm.PizzaRowMapper;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * Created by Настя on 05.03.2017.
 */
@Repository
public class PizzaDao extends Dao {

    @Autowired
    private PizzaRowMapper rowMapper;

    @Autowired
    public PizzaDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public static final String OPEN_STATUS = "OPEN";
    public static final String IN_PROGRESS_STATUS = "IN PROGRESS";
    public static final String READY_STATUS = "READY";


    private static final String CREATE_SQL =
            "INSERT INTO PIZZA(ID, PIZZASIZE, PIZZATYPEID, PIZZASTATUS, ORDERID, COOKID) " +
                    "VALUES (:pizzaId, :pizzaSize, :pizzaTypeId, :pizzaStatus, :orderId, :cookId)";
    private static final String ASSIGN_PIZZA_SQL = "UPDATE PIZZA SET PIZZASTATUS = :pizzaStatus, COOKID = :cookId WHERE ID = :pizzaId";
    private static final String UPDATE_STATUS_SQL = "UPDATE PIZZA SET PIZZASTATUS = :pizzaStatus WHERE ID = :pizzaId";
    private static final String GET_ALL_OPEN_SQL = "SELECT PIZZA.ID, PIZZA.PIZZASIZE, PIZZA.PIZZATYPEID, PIZZA.PIZZASTATUS, " +
            "PIZZA.ORDERID, PIZZA.COOKID FROM PIZZA , \"ORDER\" WHERE PIZZA.PIZZASTATUS LIKE '%OPEN%' " +
            "AND PIZZA.ORDERID = \"ORDER\".ID ORDER BY  \"ORDER\".CREATIONDATE";
    private static final String GET_PIZZA_FOR_COOK = "SELECT  ID, PIZZASIZE, PIZZATYPEID, PIZZASTATUS, ORDERID, COOKID FROM PIZZA " +
            "WHERE COOKID = :cookId AND PIZZASTATUS = :pizzaStatus";


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
                .addValue("pizzaId", convertUUIDToOracleID(pizza.getId()))
                .addValue("pizzaSize", pizza.getPizzaSize())
                .addValue("pizzaTypeId", convertUUIDToOracleID(pizza.getPizzaTypeId()))
                .addValue("pizzaStatus", pizza.getPizzaStatus())
                .addValue("orderId", convertUUIDToOracleID(pizza.getOrderId()))
                .addValue("cookId", convertUUIDToOracleID(pizza.getCookId()));
        getNamedParameterJdbcTemplate().update(CREATE_SQL, params);
    }

    public List<Pizza> getOpenPizza() {
        return rowMapper.getEntityList(
                getNamedParameterJdbcTemplate().queryForList(GET_ALL_OPEN_SQL, Collections.emptyMap()));
    }

    public void assignPizza(UUID pizzaId, UUID cookId, String pizzaStatus) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(pizzaId))
                .addValue("cookId", convertUUIDToOracleID(cookId))
                .addValue("pizzaStatus", pizzaStatus);
        getNamedParameterJdbcTemplate().update(ASSIGN_PIZZA_SQL, params);
    }


    public void updateStatus(UUID pizzaId, String pizzaStatus) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("pizzaId", convertUUIDToOracleID(pizzaId))
                .addValue("pizzaStatus", pizzaStatus);
        getNamedParameterJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }

    @Nullable
    public Pizza getPizzaForCook(UUID cookId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("cookId", convertUUIDToOracleID(cookId))
                .addValue("pizzaStatus", IN_PROGRESS_STATUS);
        List<Pizza> pizzas = rowMapper.getEntityList(
                getNamedParameterJdbcTemplate().queryForList(GET_PIZZA_FOR_COOK, params));
        if (!CollectionUtils.isEmpty(pizzas)) {
            return pizzas.get(0);
        }
        return null;
    }
}
