package fish.eyebrow.queryj.persist;

import fish.eyebrow.queryj.persist.item.ItemPersistenceWriter;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledPersistenceWriter {
    private final ScheduledExecutorService executorService;
    private final List<ItemPersistenceWriter<?>> writers;

    public ScheduledPersistenceWriter(List<ItemPersistenceWriter<?>> writers) {
        this.writers = writers;
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        executorService.scheduleAtFixedRate(this::persist, 0, 1, TimeUnit.SECONDS);
    }

    private void persist() {
        try {
            if (Files.notExists(Persistence.SAVE_DIR_PATH)) {
                boolean created = new File(Persistence.SAVE_DIR_STRING).mkdirs();
                if (!created) {
                    throw new PersistenceException("Could not create directory: " + Persistence.SAVE_DIR_STRING);
                }
            }

            for (ItemPersistenceWriter<?> writer : writers) {
                writer.write();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
