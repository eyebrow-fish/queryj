package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.querytree.util.TreeItemUtil;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

public class QueryTreeContextMenu extends ContextMenu {
    private final TreeView<String> queryTree;
    private final ArrayList<QueryTreeItem> queryTreeItems;

    private final RenameDialog renameDialog;

    public QueryTreeContextMenu(
            TreeView<String> queryTree,
            ArrayList<QueryTreeItem> queryTreeItems,
            RenameDialog renameDialog
    ) {
        super();
        this.queryTree = queryTree;
        this.queryTreeItems = queryTreeItems;
        this.renameDialog = renameDialog;

        MenuItem renameItem = new MenuItem("Rename");
        renameItem.setOnAction(this::renameTreeItem);

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(this::deleteTreeItem);

        MenuItem createGroupItem = new MenuItem("Create group");
        createGroupItem.setOnAction(this::createGroupTreeItem);

        MenuItem createQueryItem = new MenuItem("Create query");
        createQueryItem.setOnAction(this::createQueryTreeItem);

        TreeItem<String> treeItem = TreeViewUtil.currentSelection(queryTree);
        boolean hasSelect = treeItem != null;
        renameItem.setDisable(!hasSelect);
        deleteItem.setDisable(!hasSelect);
        getItems().addAll(renameItem, deleteItem);

        QueryTreeItem queryTreeItem = QueryTreeItem.findQueryTreeItem(
                queryTreeItems,
                TreeItemUtil.qualifiedNameOf(treeItem)
        );

        boolean isGroup = queryTreeItem instanceof QueryTreeItem.QueryGroup;
        if ((!hasSelect && queryTree.getRoot() == null) || (hasSelect && isGroup)) {
            getItems().add(createGroupItem);
        }
        if (hasSelect && isGroup) {
            getItems().add(createQueryItem);
        }
    }

    private void renameTreeItem(ActionEvent __) {
        TreeItem<String> treeItem = TreeViewUtil.currentSelection(queryTree);
        if (treeItem == null) return;

        renameDialog.show(treeItem, queryTreeItems);
    }

    private void deleteTreeItem(ActionEvent __) {
        TreeItem<String> treeItem = TreeViewUtil.currentSelection(queryTree);
        if (treeItem == null) return;

        String qualifiedName = TreeItemUtil.qualifiedNameOf(treeItem);
        QueryTreeItem queryTreeItem = QueryTreeItem.findQueryTreeItem(queryTreeItems, qualifiedName);
        if (queryTreeItem != null && (queryTreeItem.getParent() instanceof QueryTreeItem.QueryGroup parentGroup)) {
            parentGroup.getChildren().remove(queryTreeItem);
        }

        TreeItem<String> parent = treeItem.getParent();
        if (parent != null) {
            parent.getChildren().remove(treeItem);
        } else {
            queryTree.setRoot(null);
        }
    }

    private void createGroupTreeItem(ActionEvent __) {
        TreeItem<String> treeItem = TreeViewUtil.currentSelection(queryTree);
        TreeItem<String> newGroupItem = new TreeItem<>("New group");

        String qualifiedName = TreeItemUtil.qualifiedNameOf(treeItem);
        QueryTreeItem queryTreeItem = QueryTreeItem.findQueryTreeItem(queryTreeItems, qualifiedName);
        if (queryTreeItem instanceof QueryTreeItem.QueryGroup group) {
            QueryTreeItem.QueryGroup newGroup = new QueryTreeItem.QueryGroup("New group", group, new ArrayList<>());

            group.getChildren().add(newGroup);
            treeItem.getChildren().add(newGroupItem);
            treeItem.setExpanded(true);

            renameDialog.show(newGroupItem, queryTreeItems);
        } else if (queryTreeItem == null) {
            QueryTreeItem.QueryGroup newGroup = new QueryTreeItem.QueryGroup("New group", null, new ArrayList<>());

            queryTreeItems.add(newGroup);
            queryTree.setRoot(newGroupItem);
            renameDialog.show(newGroupItem, queryTreeItems);
        }
    }

    private void createQueryTreeItem(ActionEvent __) {
        TreeItem<String> treeItem = TreeViewUtil.currentSelection(queryTree);
        TreeItem<String> newQueryItem = new TreeItem<>("New query");

        String qualifiedName = TreeItemUtil.qualifiedNameOf(treeItem);
        QueryTreeItem queryTreeItem = QueryTreeItem.findQueryTreeItem(queryTreeItems, qualifiedName);
        if (queryTreeItem instanceof QueryTreeItem.QueryGroup group) {
            QueryTreeItem.Query newQuery = new QueryTreeItem.Query("New query", "PUT", "" ,"" ,group);

            group.getChildren().add(newQuery);
            treeItem.getChildren().add(newQueryItem);
            treeItem.setExpanded(true);

            renameDialog.show(newQueryItem, queryTreeItems);
        }
    }
}
