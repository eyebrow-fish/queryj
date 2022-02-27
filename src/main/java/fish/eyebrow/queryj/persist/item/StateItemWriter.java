package fish.eyebrow.queryj.persist.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StateItemWriter implements ItemWriter<StateItem> {
    @Override
    public JsonObject write(StateItem stateItem) {
        JsonObject jsonObject = new JsonObject();

        JsonArray jsonTabs = new JsonArray();
        for (String tab : stateItem.tabs()) {
            jsonTabs.add(tab);
        }
        jsonObject.add("tabs", jsonTabs);
        jsonObject.addProperty("currentTab", stateItem.currentTab());

        return jsonObject;
    }
}
