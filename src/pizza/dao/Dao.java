package pizza.dao;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.UUID;

/**
 * Created by Настя on 12.03.2017.
 */
@Repository
public abstract class Dao extends NamedParameterJdbcDaoSupport {

    @Autowired
    public Dao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        setDataSource(dataSource);
        setJdbcTemplate(jdbcTemplate);
        initTemplateConfig();
    }

    @Nullable
    public String convertUUIDToOracleID(@Nullable UUID uuid) {
        if (uuid == null)
            return null;
        return uuid.toString().replace("-", "");
    }

    @Nullable
    public UUID convertOracleIDToUUID(@Nullable String uuidStr) {
        if (StringUtils.isEmpty(uuidStr)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(uuidStr);
        stringBuilder.insert(8, "-");
        stringBuilder.insert(13, "-");
        stringBuilder.insert(18, "-");
        stringBuilder.insert(23, "-");
        return UUID.fromString(stringBuilder.toString());
    }


}
