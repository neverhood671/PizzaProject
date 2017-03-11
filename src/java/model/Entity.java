package java.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Настя on 11.03.2017.
 */
public abstract class Entity implements Serializable {

    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
