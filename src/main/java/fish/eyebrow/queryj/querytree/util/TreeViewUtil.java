package fish.eyebrow.queryj.querytree.util;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewUtil {
    private TreeViewUtil() {
    }

    public static <T> TreeItem<T> currentSelection(TreeView<T> treeView) {
        return treeView.getSelectionModel().getSelectedItem();
    }

    public static QueryTreeItem.Query queryFromName(TreeItem<QueryTreeItem> treeItem, String name) {
        for (TreeItem<QueryTreeItem> child : treeItem.getChildren()) {
            QueryTreeItem queryTreeItem = child.getValue();
            if (queryTreeItem.getName().equals(name) && queryTreeItem instanceof QueryTreeItem.Query) {
                return (QueryTreeItem.Query) queryTreeItem;
            }

            QueryTreeItem.Query found = queryFromName(child, name);
            if (found != null) return found;
        }

        return null;
    }
}
