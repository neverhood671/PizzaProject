package java;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import pizza.dao.PizzaDao;
import pizza.model.Pizza;
import pizza.orm.PizzaRawMapper;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Настя on 12.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class MyTest {

    @Inject
    private DataSource dataSource;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    private PizzaDao pizzaDao;

    @Inject
    private PizzaRawMapper pizzaRawMapper;


    @Test
    public void myTest() {
        Locale.setDefault(Locale.ENGLISH);
        Assert.assertNotNull(dataSource);
        Assert.assertNotNull(jdbcTemplate);
        Assert.assertNotNull(pizzaDao);
        Assert.assertNotNull(pizzaRawMapper);


//        pizzaDao.create("S", "81c0ae62328e45cd91b02b54aa50ed40",
//                "OPEN", "3ce800661c62408287d586688c545622",
//                "00a41a11e8ed41cdaa9c5d5f82a0754a");

//        List<Map<String, Object>> mapList = pizzaDao.getList();
//        Assert.assertTrue(mapList != null && mapList.size() > 0);

//        Pizza pizza = pizzaDao.get(pizzaDao.convertOracleIDToUUID("f270185846864f53a370d9bd38d52181"));
//        Assert.assertNotNull(pizza);

//        Pizza pizza  = new Pizza("S", pizzaDao.convertOracleIDToUUID("81c0ae62328e45cd91b02b54aa50ed40"),
//                "OPEN", pizzaDao.convertOracleIDToUUID("3ce800661c62408287d586688c545622"),
//                pizzaDao.convertOracleIDToUUID("00a41a11e8ed41cdaa9c5d5f82a0754a"));
//        pizzaDao.create(pizza);

        //pizzaDao.updateStatus(pizzaDao.convertOracleIDToUUID("ee7898ebc01e4e6c8b1c06f4ee7f976a"), "READY");

        //00a41a11e8ed41cdaa9c5d5f82a0754a
        pizzaDao.updateCook(pizzaDao.convertOracleIDToUUID("ee7898ebc01e4e6c8b1c06f4ee7f976a"),
                pizzaDao.convertOracleIDToUUID("e32bcaf7471442c595bf4894d9286a13"));

        System.out.println("Hello World!");
    }


}


