package test.java;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pizza.dao.CookDao;

/**
 * Created by Настя on 06.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"spring-config-test.xml"})
public class DboTest {

    @Autowired
    private CookDao cookDao;

    @Test
    public void testPizzaDao() {
        Assert.assertNotNull(cookDao.getCookFromLogin("James Oliver"));
    }

}
