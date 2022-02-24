package fish.eyebrow.queryj.persist;

import com.google.gson.JsonObject;

public interface Writer<T> {
    JsonObject write(T object);
}
