package pizza.dao;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pizza.model.PizzaType;
import pizza.orm.PizzaTypeRowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Настя on 02.04.2017.
 */
@Repository
public class PizzaTypeDao extends Dao {

    private static final String GET_ALL_SQL = "SELECT ID, NAME, PRICES, PRICEM, " +
            "PRICEL FROM PIZZATYPE";

    private static final String GET_TYPE_FOR_ID = "SELECT ID, NAME, PRICES, PRICEM, PRICEL FROM PIZZATYPE " +
            "WHERE ID = :pizzaTypeId";

    @Autowired
    private PizzaTypeRowMapper rowMapper;

    @Autowired
    public PizzaTypeDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        super(dataSource, jdbcTemplate);
    }

    @Nullable
    public List<PizzaType> getList() {
        try {
            return rowMapper.getEntityList(getNamedParameterJdbcTemplate().queryForList(GET_ALL_SQL, new HashMap<>()));
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    public String getCurrentPrice(String pizzaTypeName, String size) {
        String s = getNamedParameterJdbcTemplate().queryForList(getCurrentPriceSql(size, pizzaTypeName),
                Collections.EMPTY_MAP).get(0).toString();
        return s.substring(8, s.length() - 1);
    }

    private String getCurrentPriceSql(String size, String pizzaTypeName) {
        String s = "SELECT PRICE" + size + " FROM PIZZATYPE WHERE LOWER(NAME) = '" + pizzaTypeName + "'";
        return s;
    }

    @Nullable
    public PizzaType getPizzaType(UUID pizzaTypeId) {
        try {
            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("pizzaTypeId", convertUUIDToOracleID(pizzaTypeId));
            List<PizzaType> pizzas = rowMapper.getEntityList(
                    getNamedParameterJdbcTemplate().queryForList(GET_TYPE_FOR_ID, params));
            if (!CollectionUtils.isEmpty(pizzas)) {
                return pizzas.get(0);
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }


}
