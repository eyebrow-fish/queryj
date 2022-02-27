package fish.eyebrow.queryj.persist.item.util;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import javafx.scene.control.TreeItem;

public class QueryTreeItemUtil {
    private QueryTreeItemUtil() {
    }

    public static TreeItem<QueryTreeItem> makeTreeItem(QueryTreeItem queryTreeItem) {
        if (queryTreeItem instanceof QueryTreeItem.QueryGroup group) {
            TreeItem<QueryTreeItem> root = new TreeItem<>(group);
            root.getChildren().addAll(group.getChildren().stream().map(QueryTreeItemUtil::makeTreeItem).toList());
            return root;
        }

        return queryTreeItem != null ? new TreeItem<>(queryTreeItem) : null;
    }
}
