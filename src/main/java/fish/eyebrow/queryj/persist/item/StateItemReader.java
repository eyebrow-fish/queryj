package fish.eyebrow.queryj.persist.item;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class StateItemReader implements ItemReader<StateItem> {
    @Override
    public StateItem read(String source) {
        JsonObject object = new Gson().fromJson(source, JsonObject.class);

        ArrayList<String> tabs = new ArrayList<>();
        for (JsonElement tab : object.get("tabs").getAsJsonArray()) {
            tabs.add(tab.getAsString());
        }

        return new StateItem(
                tabs,
                object.get("currentTab").getAsInt()
        );
    }
}
