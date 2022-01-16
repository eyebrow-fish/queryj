package fish.eyebrow.queryj.querytree.util;

import javafx.scene.control.TreeItem;

public class TreeItemUtil {
    public static String qualifiedNameOf(TreeItem<String> treeItem) {
        if (treeItem == null) return null;

        String parentPath = treeItem.getParent() != null ? qualifiedNameOf(treeItem.getParent()) + "." : "";
        return parentPath + treeItem.getValue();
    }
}
