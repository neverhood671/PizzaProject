package pizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pizza.dao.CookDao;
import pizza.dao.PizzaDao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Настя on 19.03.2017.
 */
@EnableScheduling
@Service
public class PizzaManager {

    @Autowired
    private PizzaDao pizzaDao;
    @Autowired
    private CookDao cookDao;

    @Scheduled(fixedRate = 15000)
    public void pizzaProgressRunner() throws SQLException {
        List<UUID> openPizzaIds = pizzaDao.getOpenPizzaIds();
        List<UUID> freeCookIds = cookDao.getFreeCooksID(openPizzaIds.size());
        appointCook(openPizzaIds,freeCookIds);
    }

    private void appointCook(List<UUID> pizzaList, List<UUID> freeCookIds ){
        int i = 0;
        for (UUID cookId: freeCookIds){
            pizzaDao.updateCook(pizzaList.get(i),cookId);
            cookDao.updateStatus(cookId, "BUSY");
        }
    }
}
