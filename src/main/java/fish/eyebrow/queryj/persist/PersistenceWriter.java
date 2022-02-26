package fish.eyebrow.queryj.persist;

import com.google.gson.JsonObject;
import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.persist.item.QueryTreeItemWriter;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;
import java.util.function.Supplier;

public record PersistenceWriter(Supplier<Optional<QueryTreeItem>> supplier) implements Runnable {
    @Override
    public void run() {
        try {
            if (Files.notExists(Persistence.SAVE_PATH)) {
                File parentFile = new File(Persistence.SAVE_PATH.toUri()).getParentFile();
                boolean createdParent = parentFile.mkdirs();
                if (!createdParent) {
                    throw new RuntimeException("Could not create " + parentFile);
                }

                Files.createFile(Persistence.SAVE_PATH);
            }

            Optional<QueryTreeItem> queryTreeItem = supplier.get();
            JsonObject jsonObject = queryTreeItem.map(new QueryTreeItemWriter()::write).orElse(new JsonObject());

            Files.writeString(Persistence.SAVE_PATH, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace(); // TODO: 24/02/22 Better exception handling.
        }
    }
}
