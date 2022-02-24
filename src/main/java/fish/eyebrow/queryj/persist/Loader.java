package fish.eyebrow.queryj.persist;

import com.google.gson.JsonElement;

public interface Loader<T> {
    T load(String source);
    T load(JsonElement element);
}
