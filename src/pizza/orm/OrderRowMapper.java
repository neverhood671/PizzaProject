package pizza.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.dao.OrderDao;
import pizza.model.Order;

import java.util.Date;
import java.util.Map;

/**
 * Created by Настя on 11.03.2017.
 */
@Service
public class OrderRowMapper extends RowMapper<Order> {

    @Autowired
    private OrderDao dao;

    @Override
    public Order getEntity(Map<String, Object> order) {
        return new Order(
                dao.convertOracleIDToUUID((String) order.get("ID")),
                (String) order.get("ORDERSTATUS"),
                (Date) order.get("CREATIONDATE"));
    }


}
