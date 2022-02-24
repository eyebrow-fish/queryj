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
            parent.getChildren().remove(treeItem);
        } else {
            queryTree.setRoot(null);
        }
    }

    private void createGroupTreeItem(ActionEvent __) {
        TreeItem<QueryTreeItem> parentTreeItem = TreeViewUtil.currentSelection(queryTree);

        if (parentTreeItem == null) {
            TreeItem<QueryTreeItem> newGroup = new TreeItem<>(new QueryTreeItem.QueryGroup("New group", new ArrayList<>()));

            queryTree.setRoot(newGroup);
            renameDialog.show(newGroup);
        } else if (parentTreeItem.getValue() instanceof QueryTreeItem.QueryGroup) {
            TreeItem<QueryTreeItem> newGroup = new TreeItem<>(new QueryTreeItem.QueryGroup("New group", new ArrayList<>()));
            parentTreeItem.getChildren().add(newGroup);

            renameDialog.show(newGroup);
        }
    }

    private void createQueryTreeItem(ActionEvent __) {
        TreeItem<QueryTreeItem> parentTreeItem = TreeViewUtil.currentSelection(queryTree);

        if (parentTreeItem.getValue() instanceof QueryTreeItem.QueryGroup) {
            TreeItem<QueryTreeItem> newQuery = new TreeItem<>(
                    new QueryTreeItem.Query("New query", "PUT", "", "", Map.of())
            );
            parentTreeItem.getChildren().add(newQuery);
            parentTreeItem.setExpanded(true);

            renameDialog.show(newQuery);
        }
    }
}
