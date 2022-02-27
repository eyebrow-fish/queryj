package fish.eyebrow.queryj.persist.item;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryTreeItemReader implements ItemReader<QueryTreeItem> {
    @Override
    public QueryTreeItem read(String source) {
        JsonObject element = new Gson().fromJson(source, JsonObject.class);
        return read(element);
    }

    private QueryTreeItem read(JsonElement element) {
        JsonObject object = element.getAsJsonObject();

        if (object.has("children")) {
            JsonArray jsonChildren = object.getAsJsonArray("children");
            ArrayList<QueryTreeItem> children = new ArrayList<>();
            for (JsonElement child : jsonChildren) {
                children.add(new QueryTreeItemReader().read(child));
            }
            String name = object.get("name").getAsString();
            return new QueryTreeItem.QueryGroup(name, children);
        } else if (object.has("headers")) {
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

        return null;
    }
}
