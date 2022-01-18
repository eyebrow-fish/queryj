package fish.eyebrow.queryj.querytree;

import fish.eyebrow.queryj.querypane.QueryPane;
import fish.eyebrow.queryj.querytree.util.TreeViewUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class QueryTreeController {
    @FXML
    private QueryTree queryTree;
    private TabPane queryTabPane;
    private RenameDialog renameDialog;
    private QueryTreeContextMenu queryTreeContextMenu;

    @FXML
    private void initialize() {
        renameDialog = new RenameDialog();

        queryTree.setOnMouseClicked(this::handleTreeClick);

        TreeItem<QueryTreeItem> root = new TreeItem<>(new QueryTreeItem.QueryGroup("examples"));
        root.getChildren().add(
                new TreeItem<>(new QueryTreeItem.Query("duckduckgo", "GET", "https://duckduckgo.com", ""))
        );
        queryTree.setRoot(root);
    }

    private void handleTreeClick(MouseEvent event) {
        if (queryTreeContextMenu != null) {
            queryTreeContextMenu.hide();
            queryTreeContextMenu = null;
        }

        switch (event.getButton()) {
            case PRIMARY -> {
                if (event.getClickCount() < 2) return;

                TreeItem<QueryTreeItem> treeItem = TreeViewUtil.currentSelection(queryTree);
                if (treeItem == null) return;

                if (treeItem.getValue() instanceof (QueryTreeItem.Query query)) {
                    Optional<Tab> optionalTab = queryTabPane.getTabs()
                            .stream()
                            .filter(tab -> tab.getText().equals(query.getName()))
                            .findFirst();

                    if (optionalTab.isEmpty()) {
                        queryTabPane.getTabs().add(new Tab(query.getName(), new QueryPane(query)));
                    } else {
                        queryTabPane.getSelectionModel().select(optionalTab.get());
                    }
                }
            }
            case SECONDARY -> {
                queryTreeContextMenu = new QueryTreeContextMenu(queryTree, renameDialog);
                queryTreeContextMenu.show(queryTabPane, event.getScreenX(), event.getScreenY());
            }
        }
    }

    public void setQueryTabPane(TabPane queryTabPane) {
        this.queryTabPane = queryTabPane;
    }
}
