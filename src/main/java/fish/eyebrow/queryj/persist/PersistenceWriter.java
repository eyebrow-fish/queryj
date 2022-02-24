package fish.eyebrow.queryj.persist;

import com.google.gson.JsonObject;
import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.persist.item.QueryTreeItemWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Supplier;

public record PersistenceWriter(Supplier<QueryTreeItem> supplier) implements Runnable {
    @Override
    public void run() {
        try {
            if (Files.notExists(Persistence.SAVE_PATH)) {
                new File(Persistence.SAVE_PATH.toUri()).getParentFile().mkdirs();
                Files.createFile(Persistence.SAVE_PATH);
            }

            QueryTreeItem queryTreeItem = supplier.get();
            JsonObject jsonObject = new QueryTreeItemWriter().write(queryTreeItem);

            Files.writeString(Persistence.SAVE_PATH, jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace(); // TODO: 24/02/22 Better exception handling.
        }
    }
}
