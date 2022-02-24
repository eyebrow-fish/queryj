package fish.eyebrow.queryj.persist;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ScheduledPersistenceWriter {
    private static ScheduledPersistenceWriter instance;
    private static ScheduledExecutorService executorService;

    private ScheduledPersistenceWriter() {
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public static ScheduledPersistenceWriter getInstance() {
        return instance != null ? instance : (instance = new ScheduledPersistenceWriter());
    }

    public void startWithSupplier(Supplier<QueryTreeItem> supplier) {
        executorService.scheduleAtFixedRate(new PersistenceWriter(supplier), 0, 1, TimeUnit.SECONDS);
    }
}
