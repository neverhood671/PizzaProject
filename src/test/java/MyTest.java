package test.java;

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


    @Test
    public void myTest() {
        Locale.setDefault(Locale.ENGLISH);
        Assert.assertNotNull(dataSource);
        Assert.assertNotNull(jdbcTemplate);
        Assert.assertNotNull(pizzaDao);


//        pizzaDao.create("S", "81c0ae62328e45cd91b02b54aa50ed40",
//                "OPEN", "3ce800661c62408287d586688c545622",
//                "00a41a11e8ed41cdaa9c5d5f82a0754a");

        List<Map<String, Object>> mapList = pizzaDao.getList();

        Assert.assertTrue(mapList != null && mapList.size() > 0);

        System.out.println("Hello World!");
    }


}
