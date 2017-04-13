package pizza.dao;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import pizza.model.Cook;
import pizza.orm.CookRowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Настя on 19.03.2017.
 */
@Repository
public class CookDao extends Dao {

    public static final String FREE_STATUS = "FREE";
    public static final String BUSY_STATUS = "BUSY";

    @Autowired
    private CookRowMapper rowMapper;

    private static final String GET_FREE_COOK_IDS = "SELECT * FROM " +
            "(select id from cook WHERE COOKSTATUS LIKE '%FREE%' ORDER BY PIZZACOUNT) " +
            "WHERE ROWNUM <= :necessaryCookCount";
    private static final String UPDATE_STATUS_SQL = "UPDATE COOK SET COOKSTATUS = :cookStatus WHERE ID = :cookId";
    private static final String LOAD_COOK_FROM_LOGIN = "SELECT ID, NAME, COOKSTATUS, PIZZACOUNT FROM COOK " +
            "WHERE NAME = :login";
    private static final String FINISH_PIZZA_SQL =
            "UPDATE COOK SET COOKSTATUS = :cookStatus, PIZZACOUNT = :pizzaCount WHERE ID = :cookId";


    public CookDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    public List<UUID> getFreeCooksID(int necessaryCookCount) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("necessaryCookCount", necessaryCookCount);
        List ids = getNamedParameterJdbcTemplate().queryForList(GET_FREE_COOK_IDS, params);
        List<UUID> uuid = new ArrayList<>();
        for (Object id : ids) {
            uuid.add(convertOracleIDToUUID((String) ((Map) id).get("ID")));
        }
        return uuid;
    }

    public void updateStatus(UUID cookId, String cookStatus) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("cookId", convertUUIDToOracleID(cookId))
                .addValue("cookStatus", cookStatus);
        getNamedParameterJdbcTemplate().update(UPDATE_STATUS_SQL, params);
    }

    public void finishPizza(Cook cook) {
        int lastPizzaCount = cook.getPizzaCount();
        try {
            cook.setPizzaCount(lastPizzaCount + 1);
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("cookId", convertUUIDToOracleID(cook.getId()))
                    .addValue("cookStatus", FREE_STATUS)
                    .addValue("pizzaCount", cook.getPizzaCount());
            getNamedParameterJdbcTemplate().update(FINISH_PIZZA_SQL, params);
        } catch (Exception ex) {
            if (cook.getPizzaCount() != lastPizzaCount) {
                cook.setPizzaCount(lastPizzaCount);
            }
            throw ex;
        }
    }

    @Nullable
    public Cook getCookFromLogin(String login) {
        try {
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("login", login);
            List<Map<String, Object>> cooks = getNamedParameterJdbcTemplate().queryForList(LOAD_COOK_FROM_LOGIN, parameterSource);
            if (cooks != null && cooks.size() > 0) {
                Map<String, Object> cookProperties = cooks.get(0);
                return rowMapper.getEntity(cookProperties);
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }


}
