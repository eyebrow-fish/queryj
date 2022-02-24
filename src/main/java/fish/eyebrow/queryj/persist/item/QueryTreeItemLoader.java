package fish.eyebrow.queryj.persist.item;

import com.google.gson.*;
import fish.eyebrow.queryj.persist.Loader;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryTreeItemLoader implements Loader<QueryTreeItem> {
    @Override
    public QueryTreeItem load(String source) {
        JsonObject element = new Gson().fromJson(source, JsonObject.class);
        return load(element);
    }

    @Override
    public QueryTreeItem load(JsonElement element) {
        JsonObject object = element.getAsJsonObject();

        if (object.has("children")) {
            JsonArray jsonChildren = object.getAsJsonArray("children");
            ArrayList<QueryTreeItem> children = new ArrayList<>();
            for (JsonElement child : jsonChildren) {
                children.add(new QueryTreeItemLoader().load(child));
            }
            String name = object.get("name").getAsString();
            return new QueryTreeItem.QueryGroup(name, children);
        } else {
            JsonObject jsonHeaders = object.get("headers").getAsJsonObject();
            Map<String, String> headers = jsonHeaders.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().getAsString()));

            return new QueryTreeItem.Query(
                    object.get("name").getAsString(),
                    object.get("method").getAsString(),
                    object.get("url").getAsString(),
                    object.get("body").getAsString(),
                    headers
            );
        }
    }
}
