package fish.eyebrow.queryj.persist;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.persist.item.QueryTreeItemLoader;
import fish.eyebrow.queryj.persist.item.util.QueryTreeItemUtil;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class SaveLoader {
    private static SaveLoader instance;

    private SaveLoader() {}

    public static SaveLoader getInstance() {
        return instance != null ? instance : (instance = new SaveLoader());
    }

    public TreeItem<QueryTreeItem> loadQueryTree() throws IOException {
        TreeItem<QueryTreeItem> root;

        Path savePath = Path.of("/etc/queryj/saved");
        if (Files.exists(savePath)) {
            QueryTreeItem itemRoot = new QueryTreeItemLoader().load(Files.readString(savePath));
            root = QueryTreeItemUtil.makeTreeItem(itemRoot);
        } else {
            root = simpleDefaults();
        }

        return root;
    }

    private TreeItem<QueryTreeItem> simpleDefaults() {
        TreeItem<QueryTreeItem> root = new TreeItem<>(new QueryTreeItem.QueryGroup("Query examples", new ArrayList<>()));

        root.getChildren().add(
                new TreeItem<>(new QueryTreeItem.Query("duckduckgo", "GET", "https://duckduckgo.com", "", Map.of()))
        );

        root.getChildren().add(
                new TreeItem<>(new QueryTreeItem.Query(
                        "stackoverflow - deserialize json in java",
                        "GET",
                        "https://stackoverflow.com/search?q=%5Bjava%5D+how+to+deserialize+json",
                        "",
                        Map.of()
                ))
        );
        return root;
    }
}
