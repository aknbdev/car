package uz.sitelabs.car.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public final class JSONUtils {

    public String toJSON(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return JSONMapper.getInstance().writeValueAsString(object);
        } catch (Exception ignored) {
        }
        return null;
    }

    public <T> T fromJSON(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        ObjectMapper mapper = JSONMapper.getInstance();

        try {
            return mapper.readValue(json, clazz);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    public <T> T fromJSON(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return JSONMapper.getInstance().readValue(json, typeReference);
        } catch (Exception ignored) {
        }
        return null;
    }


}

class JSONMapper extends ObjectMapper {
    private volatile static JSONMapper instance;

    private JSONMapper() {
        if (instance != null) {
            throw new UnsupportedOperationException("This is a singleton class, so create object from its constructor is not supported!");
        }
    }

    public static JSONMapper getInstance() {
        if (instance == null) {
            synchronized (JSONMapper.class) {
                if (instance == null) {
                    instance = new JSONMapper();
                    instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                }
            }
        }
        return instance;
    }
}
