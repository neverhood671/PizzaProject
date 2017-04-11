package pizza.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import pizza.model.Entity;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Настя on 02.04.2017.
 */
@Service
public class EntityToJson {
    public JsonObject entityToJsonObject(Entity entity) throws IllegalAccessException {
        JsonObject jsonObject = new JsonObject();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value instanceof Boolean) {
                jsonObject.addProperty(field.getName(), (Boolean) value);
            } else if (value instanceof Number) {
                jsonObject.addProperty(field.getName(), (Number) value);
            } else if (value instanceof Character) {
                jsonObject.addProperty(field.getName(), (Character) value);
            } else {
                jsonObject.addProperty(field.getName(), field.get(entity).toString());
            }
        }
        return jsonObject;
    }

    public JsonArray entityListToJsonArray(List<? extends Entity> entities) throws IllegalAccessException {
        JsonArray jsonArray = new JsonArray();
        for (Entity entity : entities) {
            jsonArray.add(entityToJsonObject(entity));
        }
        return jsonArray;
    }

}
