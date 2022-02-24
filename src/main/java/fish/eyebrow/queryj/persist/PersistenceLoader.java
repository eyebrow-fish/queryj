package fish.eyebrow.queryj.persist;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.persist.item.QueryTreeItemLoader;
import fish.eyebrow.queryj.persist.item.util.QueryTreeItemUtil;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class PersistenceLoader {
    private static PersistenceLoader instance;

    private PersistenceLoader() {}

    public static PersistenceLoader getInstance() {
        return instance != null ? instance : (instance = new PersistenceLoader());
    }

    public TreeItem<QueryTreeItem> loadQueryTree() throws IOException {
        Path filePath = Files.exists(Persistence.SAVE_PATH) ?
                        Persistence.SAVE_PATH :
                        Path.of(Objects.requireNonNull(this.getClass().getResource("default_saved")).getPath());

        QueryTreeItem queryTreeItem = new QueryTreeItemLoader().load(Files.readString(filePath));
        return QueryTreeItemUtil.makeTreeItem(queryTreeItem);
    }
}
