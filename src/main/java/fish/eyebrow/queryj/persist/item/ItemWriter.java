package fish.eyebrow.queryj.persist.item;

import com.google.gson.JsonObject;

public interface ItemWriter<T> {
    JsonObject write(T source);
}
