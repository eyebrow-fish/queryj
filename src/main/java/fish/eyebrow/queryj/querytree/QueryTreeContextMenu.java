package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.persist.item.QueryTreeItem;
import fish.eyebrow.queryj.querytree.renamedialog.RenameDialog;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;
import java.util.Map;

public class QueryTreeContextMenu extends ContextMenu {
    private final TreeView<QueryTreeItem> queryTree;
    private final RenameDialog renameDialog;

    public QueryTreeContextMenu(TreeView<QueryTreeItem> queryTree, RenameDialog renameDialog) {
        super();
        this.queryTree = queryTree;
        this.renameDialog = renameDialog;

        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setOnAction(this::renameTreeItem);

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(this::deleteTreeItem);

        MenuItem createGroupItem = new MenuItem("Create group");
        createGroupItem.setOnAction(this::createGroupTreeItem);

        MenuItem createQueryItem = new MenuItem("Create query");
        createQueryItem.setOnAction(this::createQueryTreeItem);

        TreeItem<QueryTreeItem> treeItem = TreeViewUtil.currentSelection(queryTree);
        boolean hasSelect = treeItem != null;
        renameItem.setDisable(!hasSelect);
        deleteItem.setDisable(!hasSelect);
        getItems().addAll(renameItem, deleteItem);

        boolean isGroup = hasSelect && treeItem.getValue() instanceof QueryTreeItem.QueryGroup;
        if ((!hasSelect && queryTree.getRoot() == null) || (isGroup)) {
            getItems().add(createGroupItem);
        }
        if (isGroup) {
            getItems().add(createQueryItem);
        }
    }

    private void renameTreeItem(ActionEvent __) {
        TreeItem<QueryTreeItem> treeItem = TreeViewUtil.currentSelection(queryTree);
        if (treeItem == null) return;

        renameDialog.show(treeItem);
    }

    private void deleteTreeItem(ActionEvent __) {
        TreeItem<QueryTreeItem> treeItem = TreeViewUtil.currentSelection(queryTree);
        if (treeItem == null) return;

        TreeItem<QueryTreeItem> parent = treeItem.getParent();
        if (parent != null) {
            ((QueryTreeItem.QueryGroup) parent.getValue()).getChildren().remove(treeItem.getValue());
            parent.getChildren().remove(treeItem);
        } else {
            queryTree.setRoot(null);
        }
    }

    private void createGroupTreeItem(ActionEvent __) {
        TreeItem<QueryTreeItem> parentTreeItem = TreeViewUtil.currentSelection(queryTree);

        QueryTreeItem.QueryGroup newGroupItem = new QueryTreeItem.QueryGroup("New group", new ArrayList<>());
        if (parentTreeItem == null) {
            TreeItem<QueryTreeItem> newGroup = new TreeItem<>(newGroupItem);

            queryTree.setRoot(newGroup);
            renameDialog.show(newGroup);
        } else if (parentTreeItem.getValue() instanceof QueryTreeItem.QueryGroup group) {
            TreeItem<QueryTreeItem> newGroup = new TreeItem<>(newGroupItem);
            parentTreeItem.getChildren().add(newGroup);
            group.getChildren().add(newGroupItem);

            renameDialog.show(newGroup);
        }
    }

    private void createQueryTreeItem(ActionEvent __) {
        TreeItem<QueryTreeItem> parentTreeItem = TreeViewUtil.currentSelection(queryTree);

        if (parentTreeItem.getValue() instanceof QueryTreeItem.QueryGroup group) {
            QueryTreeItem.Query newQueryItem = new QueryTreeItem.Query("New query", "PUT", "", "", Map.of());
            TreeItem<QueryTreeItem> newQuery = new TreeItem<>(newQueryItem);

            parentTreeItem.getChildren().add(newQuery);
            parentTreeItem.setExpanded(true);
            group.getChildren().add(newQueryItem);

            renameDialog.show(newQuery);
        }
    }
}
