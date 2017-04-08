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
import java.sql.SQLException;
import java.util.*;


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
    private static final String GET_ALL_SQL = "SELECT ID, PIZZASIZE, PIZZATYPEID, PIZZASTATUS, " +
            "ORDERID, COOKID FROM PIZZA";
    private static final String GET_SQL = "SELECT ID, PIZZASIZE, PIZZATYPEID, PIZZASTATUS, ORDERID, COOKID FROM PIZZA WHERE ID = :ID";
    private static final String UPDATE_STATUS_SQL = "UPDATE PIZZA SET PIZZASTATUS = :pizzaStatus WHERE ID = :pizzaId";
    private static final String UPDATE_COOK_SQL = "UPDATE PIZZA SET COOKID = :cookId WHERE ID = :pizzaId";

    private static final String GET_ALL_OPEN_SQL = "SELECT PIZZA.ID, PIZZA.PIZZA_SIZE, PIZZA.PIZZATYPEID, PIZZA.PIZZASTATUS, " +
            "PIZZA.ORDERID, PIZZA.COOKID FROM PIZZA , \"ORDER\" WHERE PIZZA.PIZZASTATUS LIKE '%OPEN%' " +
            "AND PIZZA.ORDERID = \"ORDER\".ID ORDER BY  \"ORDER\".CREATIONDATE";

    private static final String GET_OPEN_PIZZA_IDS = "SELECT PIZZA.ID FROM PIZZA , \"ORDER\" WHERE PIZZA.PIZZASTATUS LIKE '%OPEN%' " +
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

    public List<Map<String, Object>> getOpenPizza() {
        return getNamedParameterJdbcTemplate().queryForList(GET_ALL_OPEN_SQL, Collections.emptyMap());
    }

    public List<Map<String, Object>> getList() {
        return getNamedParameterJdbcTemplate().queryForList(GET_ALL_SQL, Collections.emptyMap());
    }

    public List<UUID> getOpenPizzaIds() {
        List ids = getNamedParameterJdbcTemplate().queryForList(GET_OPEN_PIZZA_IDS, Collections.emptyMap());
        List<UUID> uuid = new ArrayList<UUID>();
        for (Object id : ids) {
            String s = id.toString();
            uuid.add(convertOracleIDToUUID(s.substring(4, s.length() - 1)));
        }
        return uuid;
    }

    public Map<String, Object> get(UUID pizzaId) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("ID", convertUUIDToOracleID(pizzaId));
        return getNamedParameterJdbcTemplate().queryForMap(GET_SQL, params);
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

    @Nullable
    public Pizza getPizzaForCook(UUID cookId) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("cookId", convertUUIDToOracleID(cookId))
                    .addValue("pizzaStatus", IN_PROGRESS_STATUS);
            List<Pizza> pizzas = rowMapper.getEntityList(
                    getNamedParameterJdbcTemplate().queryForList(GET_PIZZA_FOR_COOK, params));
            if (!CollectionUtils.isEmpty(pizzas)) {
                return pizzas.get(0);
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }
}
