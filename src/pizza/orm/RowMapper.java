package pizza.orm;

import pizza.model.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Настя on 02.04.2017.
 */
public abstract class RowMapper<T extends Entity> {

    public abstract T getEntity(Map<String, Object> entity);

    public List<T> getEntityList(List<Map<String, Object>> entityListMap) {
        List<T> entityList = new ArrayList<>();
        for (Map<String, Object> entityMap : entityListMap) {
            entityList.add(getEntity(entityMap));
        }
        return entityList;
    }
}
