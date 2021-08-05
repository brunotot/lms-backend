package tvz.btot.zavrsni.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import tvz.btot.zavrsni.web.dto.UserDto;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static tvz.btot.zavrsni.infrastructure.utils.ObjectMapperUtils.getMapper;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SocketPayload {
    private static final String JS_PROPERTY_PATH_SPLIT_REGEX = "\\.";
    private static final String JS_OPTIONAL_IDENTIFIER = "?";

    private LinkedHashMap<String, Object> map;
    private UserDto socketUser;

    public <T> T get(final Class<T> clazz) {
        return get(clazz, Strings.EMPTY);
    }

    public <T> T get(final Class<T> clazz, final String jsPropertyPath) {
        assert jsPropertyPath != null;
        final String _jsPropertyPath = jsPropertyPath.trim().replace(JS_OPTIONAL_IDENTIFIER, Strings.EMPTY);
        final ObjectMapper mapper = getMapper();
        List<String> keyNodesList = Optional.of(_jsPropertyPath.split(JS_PROPERTY_PATH_SPLIT_REGEX))
                .map(List::of)
                .orElse(Collections.emptyList());
        LinkedHashMap<String, Object> _payload = new LinkedHashMap<>(map);
        int i = 0;
        for (; i < keyNodesList.size() - 1; i++) {
            final String key = keyNodesList.get(i);
            _payload = mapper.convertValue(_payload.get(key), new TypeReference<>() { });
        }
        return mapper.convertValue(i == 0 && _jsPropertyPath.isEmpty() ? map : _payload.get(keyNodesList.get(i)), clazz);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public LinkedHashMap<String, Object> getMap() {
        return map;
    }
}
