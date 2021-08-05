package tvz.btot.zavrsni.infrastructure.utils;

import tvz.btot.zavrsni.security.utils.SecurityContextUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static tvz.btot.zavrsni.infrastructure.utils.ObjectMapperUtils.getMapper;

public class SqlQueryParams {
    private static final String DEFAULT_OBJECT_KEY = "o";

    private final Map<String, Object> procedureOutputMap;
    private final Map<String, Object> map;

    private SqlQueryParams() {
        this.map = new HashMap<>();
        this.procedureOutputMap = new HashMap<>();
        this.map.put("contextUserId", SecurityContextUtils.getUser().getId());
    }

    public static SqlQueryParams newInstance(final Object o) {
        return SqlQueryParams.newInstance(DEFAULT_OBJECT_KEY, o);
    }

    public static SqlQueryParams newInstance() {
        return new SqlQueryParams();
    }

    public static SqlQueryParams newInstance(final String key, final Object value) {
        return newInstance().param(key, value);
    }

    public SqlQueryParams param(final String key, final Object value) {
        this.map.put(key, value);
        return this;
    }

    public Object getOutputParam(final String key) {
        return getOutputParam(key, Object.class);
    }

    @SuppressWarnings("unchecked")
    public <T> T getOutputParam(final String key, final Class<T> clazz) {
        Object value = procedureOutputMap.get(key);
        if (clazz.equals(Timestamp.class)) {
            return (T) DateTimeUtils.convertDateStringToTimestamp(getOutputParam(key, String.class));
        }
        return getMapper().convertValue(value, clazz);
    }
}
