package fish.eyebrow.queryj.querytree.util;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeViewUtil {
    public static <T> TreeItem<T> currentSelection(TreeView<T> treeView) {
        return treeView.getSelectionModel().getSelectedItem();
    }
}
