package pizza.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.dao.CookDao;
import pizza.model.Cook;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Настя on 05.04.2017.
 */
@Service
public class CookRowMapper extends RowMapper<Cook> {

    @Autowired
    private CookDao dao;

    @Override
    public Cook getEntity(Map<String, Object> entity) throws SQLException {
        return new Cook(
                dao.convertOracleIDToUUID((String) entity.get("ID")),
                (String) entity.get("NAME"),
                (String) entity.get("COOKSTATUS"),
                ((BigDecimal) entity.get("PIZZACOUNT")).intValue());
    }

}
