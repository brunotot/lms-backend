package tvz.btot.zavrsni.infrastructure.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class JsonObject {
    private final com.google.gson.JsonObject json;

    public static JsonObjectBuilder builder() {
        return new JsonObjectBuilder();
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return this.json.entrySet();
    }

    public com.google.gson.JsonObject deepCopy() {
        return json.deepCopy();
    }

    public void add(final String property, final JsonElement jsonElement) {
        json.add(property, jsonElement);
    }

    public void add(final String property, final Number value) {
        json.addProperty(property, value);
    }

    public void add(final String property, final String value) {
        json.addProperty(property, value);
    }

    public void add(final String property, final Boolean value) {
        json.addProperty(property, value);
    }

    public void add(final String property, final Character value) {
        json.addProperty(property, value);
    }

    public JsonElement get(final String memberName) {
        return json.get(memberName);
    }

    public JsonElement getAsJsonArray(final String memberName) {
        return json.getAsJsonArray(memberName);
    }

    public JsonElement getAsJsonArray() {
        return json.getAsJsonArray();
    }

    public JsonElement getAsJson(final String memberName) {
        return json.getAsJsonObject(memberName);
    }

    public JsonElement getAsJsonObject() {
        return json.getAsJsonObject();
    }

    public JsonElement getAsJsonPrimitive(final String memberName) {
        return json.getAsJsonPrimitive(memberName);
    }

    public JsonElement getAsJsonNull() {
        return json.getAsJsonNull();
    }

    public BigDecimal getAsBigDecimal() {
        return json.getAsBigDecimal();
    }

    public BigInteger getAsBigInteger() {
        return json.getAsBigInteger();
    }

    public Boolean getAsBoolean() {
        return json.getAsBoolean();
    }

    public Byte getAsByte() {
        return json.getAsByte();
    }

    public Double getAsDouble() {
        return json.getAsDouble();
    }

    public Float getAsFloat() {
        return json.getAsFloat();
    }

    public Integer getAsInt() {
        return json.getAsInt();
    }

    public Long getAsLong() {
        return json.getAsLong();
    }

    public Number getAsNumber() {
        return json.getAsNumber();
    }

    public Short getAsShort() {
        return json.getAsShort();
    }

    public String getAsString() {
        return json.getAsString();
    }

    public Character getAsCharacter() {
        return json.getAsCharacter();
    }

    public int size() {
        return json.size();
    }

    public JsonElement remove(final String property) {
        return json.remove(property);
    }

    public boolean has(final String memberName) {
        return json.has(memberName);
    }

    public Set<String> keySet() {
        return json.keySet();
    }

    public static class JsonObjectBuilder {
        private final Gson gson = new Gson();
        private final com.google.gson.JsonObject json;

        public JsonObjectBuilder() {
            json = new com.google.gson.JsonObject();
        }

        public JsonObjectBuilder add(final String property, final Object value) {
            json.add(property, gson.toJsonTree(value));
            return this;
        }

        public JsonObjectBuilder add(final String property, final JsonElement jsonElement) {
            json.add(property, jsonElement);
            return this;
        }

        public JsonObjectBuilder add(final String property, final Number value) {
            json.addProperty(property, value);
            return this;
        }

        public JsonObjectBuilder add(final String property, final String value) {
            json.addProperty(property, value);
            return this;
        }

        public JsonObjectBuilder add(final String property, final Boolean value) {
            json.addProperty(property, value);
            return this;
        }

        public JsonObjectBuilder add(final String property, final Character value) {
            json.addProperty(property, value);
            return this;
        }

        public JsonObject build() {
            return new JsonObject(json);
        }

        public String stringify() {
            return gson.toJson(json);
        }
    }
}
