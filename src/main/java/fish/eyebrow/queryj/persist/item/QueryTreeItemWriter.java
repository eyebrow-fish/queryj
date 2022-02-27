package fish.eyebrow.queryj.persist.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

public class QueryTreeItemWriter implements ItemWriter<QueryTreeItem> {
    @Override
    public JsonObject write(QueryTreeItem queryTreeItem) {
        if (queryTreeItem instanceof QueryTreeItem.QueryGroup group) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", group.getName());

            JsonArray children = new JsonArray();
            for (QueryTreeItem item : group.getChildren()) {
                children.add(write(item));
            }
            jsonObject.add("children", children);
            return jsonObject;
        }

        QueryTreeItem.Query query = (QueryTreeItem.Query) queryTreeItem;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", query.getName());
        jsonObject.addProperty("method", query.getMethod());
        jsonObject.addProperty("url", query.getUrl());
        jsonObject.addProperty("body", query.getBody());

        JsonObject jsonHeaders = new JsonObject();
        for (Map.Entry<String, String> headerEntry : query.getHeaders().entrySet()) {
            jsonHeaders.addProperty(headerEntry.getKey(), headerEntry.getValue());
        }
        jsonObject.add("headers", jsonHeaders);

        return jsonObject;
    }
}
